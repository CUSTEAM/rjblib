package model;

import java.util.Date;

public class POSTALMails {
	
	private Integer Oid;
	private String category;
	private String username;
	private String fromr;
	private String unit;
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	private Date recDate;
	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getFromr() {
		return fromr;
	}

	public void setFromr(String fromr) {
		this.fromr = fromr;
	}
	private String froma;
	private String from_zip;
	private String signer;
	private Date signdate;
	private String stor;
	private String note;
	private Date rejectd;
	private String lastediter;
	private String no;
	//private Date lastedit;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public POSTALMails() {}
	
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFroma() {
		return froma;
	}
	public void setFroma(String froma) {
		this.froma = froma;
	}
	public String getFrom_zip() {
		return from_zip;
	}
	public void setFrom_zip(String from_zip) {
		this.from_zip = from_zip;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	public Date getSigndate() {
		return signdate;
	}
	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}
	public String getStor() {
		return stor;
	}

	public void setStor(String stor) {
		this.stor = stor;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getRejectd() {
		return rejectd;
	}
	public void setRejectd(Date rejectd) {
		this.rejectd = rejectd;
	}
	public String getLastediter() {
		return lastediter;
	}
	public void setLastediter(String lastediter) {
		this.lastediter = lastediter;
	}
	
	

}
