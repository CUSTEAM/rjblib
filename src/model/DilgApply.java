package model;

import java.util.Date;

public class DilgApply {
	
	
	private Integer Oid;
	private String student_no;
	private String reason;
	private String note;
	private String abs;
	private String auditor;
	private String result;
	private String realLevel;
	private String defaultLevel;
	private String reply;
	private Date cr_date;
	
	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getRealLevel() {
		return realLevel;
	}

	public void setRealLevel(String realLevel) {
		this.realLevel = realLevel;
	}

	public String getDefaultLevel() {
		return defaultLevel;
	}

	public void setDefaultLevel(String defaultLevel) {
		this.defaultLevel = defaultLevel;
	}

	public DilgApply(){
		
	}
	
	public Integer getOid() {
		return Oid;
	}

	public void setOid(Integer oid) {
		Oid = oid;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
