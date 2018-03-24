package com.zzx.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CommonDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonDao<T, ID> {

	private final EntityManager entityManager;
//	private final JpaEntityInformation<T, ID> entityInformation;

	public CommonDaoImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.entityManager = em;
//		this.entityInformation = entityInformation;
	}
	
//	@Override
//	@Transactional
//	public <S extends T> S save(S entity) {
//		boolean isPo = entity instanceof PO;
//		if (this.entityInformation.isNew(entity)) {
//			if(isPo) {
//				PO po = (PO)entity;
//				po.setCreationDate(new Date());
//			}
//			this.entityManager.persist(entity);
//			return entity;
//		} else {
//			if(isPo) {
//				PO po = (PO)entity;
//				po.setUpdateDate(new Date());
//			}
//			return this.entityManager.merge(entity);
//		}
//	}

	@Transactional(readOnly = true)
	@Override
	public Page<T> find(T example, Pageable pageable) {
		return findAll(byAuto(entityManager, example), pageable);
	}

	static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {
		@SuppressWarnings("unchecked")
		final Class<T> type = (Class<T>) example.getClass();
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				EntityType<T> entity = entityManager.getMetamodel().entity(type);

				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
					Object attrValue = getValue(example, attr);
					if (attrValue == null) {
						continue;
					}
					if (attr.getJavaType() == String.class) {
						if (!StringUtils.isEmpty(attrValue)) {
							predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),
									'%' + attrValue.toString() + '%'));
						}
					} else {
						predicates.add(
								cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
					}
				}
				return predicates.isEmpty() ? cb.conjunction()
						: cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

			private Object getValue(T example, Attribute<T, ?> attr) {
				return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
			}

			private <E> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}

		};
	}
}
