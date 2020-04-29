package com.yjy.test03_reflect.other;

import java.sql.Timestamp;

public class AuditEntity extends VersionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7655450094190788933L;

	// ������
	private String createUser;
	// ����ʱ��
	private Timestamp createTime;
	// ��������
	private String lastUpdateUser;
	// ������ʱ��
	private Timestamp lastUpdateTime;
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
