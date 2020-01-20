package model;

import java.sql.Timestamp;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String action;
	private Timestamp upTime;
	private String cname;
	private String note;
	private String whois;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(Timestamp upTime) {
		this.upTime = upTime;
	}

	/** full constructor */
	public SysLog(String action, Timestamp upTime, String cname, String note, String whois) {
		this.action = action;
		this.upTime = upTime;
		this.cname = cname;
		this.note = note;
		this.whois = whois;
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

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWhois() {
		return this.whois;
	}

	public void setWhois(String whois) {
		this.whois = whois;
	}

}