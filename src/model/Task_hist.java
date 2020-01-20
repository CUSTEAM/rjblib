package model;

import java.util.Date;

public class Task_hist {
	
	Integer Oid, Task_oid, Task_hist_oid;
	String empl, note, open, feedback;
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	Date sdate, edate;
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public Integer getTask_oid() {
		return Task_oid;
	}
	public void setTask_oid(Integer task_oid) {
		Task_oid = task_oid;
	}
	public Integer getTask_hist_oid() {
		return Task_hist_oid;
	}
	public void setTask_hist_oid(Integer task_hist_oid) {
		Task_hist_oid = task_hist_oid;
	}
	public String getEmpl() {
		return empl;
	}
	public void setEmpl(String empl) {
		this.empl = empl;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
}
