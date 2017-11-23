package com.zzx.common.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

@Configuration

@EnableJpaRepositories(repositoryBaseClass = CommonDaoImpl.class)

@ConditionalOnProperty(name = "datasource.rw", havingValue = "true")
@ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class RwDataSourceAutoConfig {

	protected transient Log logger = LogFactory.getLog(getClass());
	private Class<? extends DataSource> dataSourceClass;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource(Environment env) {
		DataSource master = masterDataSource(env);

		Map<Object, Object> targetDataSources = new LinkedHashMap<Object, Object>();
		targetDataSources.put(DataSourceContextHolder.DataSourceType.MASTER.getCode(), master);
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.slave.");
		String dsPrefixs = propertyResolver.getProperty("names");

		final List<String> slaves = new ArrayList<String>();
		slaves.addAll(StringUtils.commaDelimitedListToSet(dsPrefixs));
		for (String dsPrefix : slaves) {
			Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
			MutablePropertyValues properties = new MutablePropertyValues(dsMap);
			DataSource ds = BeanUtils.instantiate(dataSourceClass);
			new RelaxedDataBinder(ds).withAlias("url", "jdbcUrl").withAlias("username", "user").bind(properties);
			targetDataSources.put(dsPrefix, ds);
		}
		AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
			private AtomicInteger count = new AtomicInteger(0);
			private final int slavesNumber = slaves.size();

			@Override
			protected Object determineCurrentLookupKey() {
				if (DataSourceContextHolder.isChoiceSlave() && slavesNumber > 0) {
					int number = count.getAndAdd(1);
					int idx = number % slavesNumber;
					DataSourceContextHolder.setDataSourceKey(slaves.get(idx));
					return DataSourceContextHolder.getDataSourceKey();
				}
				return DataSourceContextHolder.DataSourceType.MASTER.getCode();
			}

		};

		proxy.setDefaultTargetDataSource(master);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	protected DataSource masterDataSource(Environment env) {
		String className = env.getProperty("datasource.type");
		if (className != null) {
			try {
				dataSourceClass = (Class<? extends DataSource>) ClassUtils.forName(className, null);
			} catch (Exception e) {
				logger.warn(String.format("DataSource type '%s' not found", className));
			}
		}
		if (dataSourceClass == null) {
			dataSourceClass = DataSourceBuilder.create().findType();
		}

		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "datasource.");
		Map<String, Object> dsMap = propertyResolver.getSubProperties("master.");
		MutablePropertyValues properties = new MutablePropertyValues(dsMap);

		DataSource ds = BeanUtils.instantiate(dataSourceClass);
		new RelaxedDataBinder(ds).withAlias("url", "jdbcUrl").withAlias("username", "user").bind(properties);

		return ds;
	}

	@Bean
	@ConditionalOnBean(name = "dataSource")
	public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource routingDataSouce,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		@SuppressWarnings("serial")
		JpaTransactionManager transactionManager = new JpaTransactionManager() {

			@Override
			protected void doBegin(Object transaction, TransactionDefinition definition) {
				if (definition.isReadOnly()) {
					DataSourceContextHolder.markSlave();
				} else {
					DataSourceContextHolder.markMaster();
				}
				super.logger.info("jpa-transaction:begin->now dataSourceType is <<"
						+ DataSourceContextHolder.getDataSourceType() + ">>");
				super.doBegin(transaction, definition);
			}

			@Override
			protected void doCommit(DefaultTransactionStatus status) {
				super.logger.info("jpa-transaction:commit->now dataSource is ["
						+ DataSourceContextHolder.getDataSourceKey() + "]");
				super.doCommit(status);
			}

		};
		TransactionManagerCustomizers customizers = transactionManagerCustomizers.getIfAvailable();
		if (customizers != null) {
			customizers.customize(transactionManager);
		}
		transactionManager.setDataSource(routingDataSouce);
		return transactionManager;
	}

}
