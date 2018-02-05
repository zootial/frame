package ${serviceImplPackage};

import org.springframework.stereotype.Service;

<#if daoPackage??>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzx.common.bo.CommonBo;
import ${domainPackage}.${domainClassName};
import ${daoPackage}.${domainClassName}${daoSuffix};
<#else>
import com.zzx.common.bo.BaseBo;
</#if>
<#if servicePackage??>
import ${servicePackage}.${domainClassName}${serviceSuffix};
</#if>

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date ${.now?string("yyyy-MM-dd hh:mm:ss")}
 */
@Service
public class ${domainClassName}${serviceSuffix}${implSuffix} <#if daoPackage??>extends CommonBo<${domainClassName}, Long><#else>BaseBo</#if> <#if servicePackage??>implements ${domainClassName}${serviceSuffix}</#if> {
    
	<#if daoPackage??>
	@Autowired
    private ${domainClassName}${daoSuffix} repo;
    
	@Override
	protected JpaRepository<${domainClassName}, Long> getJpaRepository() {
	   return this.repo;
	}
	</#if>
	
}
