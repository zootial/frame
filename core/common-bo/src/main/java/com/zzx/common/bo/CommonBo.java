package com.zzx.common.bo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

public abstract class CommonBo<T, ID extends Serializable> extends BaseBo implements InitializingBean {

	private JpaRepository<T, ID> repo;

	protected abstract JpaRepository<T, ID> getJpaRepository();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		JpaRepository<T, ID> repo = getJpaRepository();
		Assert.notNull(repo, "Need JpaRepository's instance!");
		this.repo = repo;
	}

	public Page<T> findAll(Pageable pageable) {
		return this.repo.findAll(pageable);
	}

	public <S extends T> S findOne(Example<S> example) {
		return this.repo.findOne(example);
	}

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return this.repo.findAll(example, pageable);
	}
	
	public <S extends T> long count(Example<S> example) {
		return this.repo.count(example);
	}
	
	public <S extends T> boolean exists(Example<S> example) {
		return this.repo.exists(example);
	}
	
	public <S extends T> S save(S entity) {
		try {
			return this.repo.save(entity);
		} finally {
			this.repo.flush();
		}
	}
	
	public T findOne(ID id) {
		return this.repo.findOne(id);
	}

	public boolean exists(ID id) {
		return this.repo.exists(id);
	}
	
	public long count() {
		return this.repo.count();
	}
	
	public void delete(ID id) {
		this.repo.delete(id);
	}
	
	public void delete(T entity) {
		this.repo.delete(entity);
	}
	
	public void delete(Iterable<? extends T> entities) {
		this.repo.delete(entities);
	}

	public void deleteAll() {
		this.repo.deleteAll();
	}
	
	public List<T> findAll() {
		return this.repo.findAll();
	}
	
	public List<T> findAll(Sort sort) {
		return this.repo.findAll(sort);
	}

	public List<T> findAll(Iterable<ID> ids) {
		return this.repo.findAll(ids);
	}
	
	public <S extends T> List<S> save(Iterable<S> entities) {
		try {
			return this.repo.save(entities);
		} finally {
			this.repo.flush();
		}
	}
	
//	public void flush() {
//		
//	}
//	
//	public <S extends T> S saveAndFlush(S entity) {
//		return null;
//	}
//	
	public void deleteInBatch(Iterable<T> entities) {
		this.repo.deleteInBatch(entities);
	}
	
	public void deleteAllInBatch() {
		this.repo.deleteAllInBatch();
	}
//
//	
//	public T getOne(ID id) {
//		return null;
//	}
	
	public <S extends T> List<S> findAll(Example<S> example) {
		return this.repo.findAll(example);
	}
	
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return this.repo.findAll(example, sort);
	}
	
}
