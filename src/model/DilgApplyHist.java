package model;

import java.util.Date;

public class DilgApplyHist {
	
	private Integer Oid;
	private Integer Dilg_app_oid;
	private String auditor;
	private Date date;
	
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public Integer getDilg_app_oid() {
		return Dilg_app_oid;
	}
	public void setDilg_app_oid(Integer dilg_app_oid) {
		Dilg_app_oid = dilg_app_oid;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
