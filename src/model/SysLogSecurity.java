package model;

import java.sql.Timestamp;

/**
 * SysLogSecurity entity. @author MyEclipse Persistence Tools
 */

public class SysLogSecurity implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String action;
	private Timestamp upTime;
	private String userid;
	private String agent;
	private String ipaddress;
	private String remoteaddress;
	private String note;

	// Constructors

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/** default constructor */
	public SysLogSecurity() {
	}

	/** minimal constructor */
	public SysLogSecurity(Timestamp upTime) {
		this.upTime = upTime;
	}

	/** full constructor */
	public SysLogSecurity(String action, Timestamp upTime, String userid, String agent, String ipaddress,
			String remoteaddress) {
		this.action = action;
		this.upTime = upTime;
		this.userid = userid;
		this.agent = agent;
		this.ipaddress = ipaddress;
		this.remoteaddress = remoteaddress;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getUpTime() {
		return this.upTime;
	}

	public void setUpTime(Timestamp upTime) {
		this.upTime = upTime;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getRemoteaddress() {
		return this.remoteaddress;
	}

	public void setRemoteaddress(String remoteaddress) {
		this.remoteaddress = remoteaddress;
	}

}