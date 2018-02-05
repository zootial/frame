package com.zzx.common.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PO {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "create_date", updatable = false)
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "update_date", insertable = false)
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
