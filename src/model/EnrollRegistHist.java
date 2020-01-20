package model;

import java.sql.Timestamp;

/**
 * EnrollRegistHist entity. @author MyEclipse Persistence Tools
 */

public class EnrollRegistHist implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollRegistOid;
	private String note;
	private String userid;
	private Timestamp edate;

	// Constructors

	/** default constructor */
	public EnrollRegistHist() {
	}

	/** full constructor */
	public EnrollRegistHist(Integer enrollRegistOid, String note, String userid, Timestamp edate) {
		this.enrollRegistOid = enrollRegistOid;
		this.note = note;
		this.userid = userid;
		this.edate = edate;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getEnrollRegistOid() {
		return this.enrollRegistOid;
	}

	public void setEnrollRegistOid(Integer enrollRegistOid) {
		this.enrollRegistOid = enrollRegistOid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Timestamp getEdate() {
		return this.edate;
	}

	public void setEdate(Timestamp edate) {
		this.edate = edate;
	}

}