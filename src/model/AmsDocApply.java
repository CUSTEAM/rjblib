package model;

import java.util.Date;

public class AmsDocApply {
	public AmsDocApply(){}
	private Integer Oid, totalDay, totalHour, totalMinute, teachPeriod, allow;
	private String idno, docType, sn, reason, askLeaveType, agent, status, memo, sent, chief, creator;
	private Date startDate, endDate, createDate, processDate, verify;
	public AmsDocApply(Integer oid, Integer totalDay, Integer totalHour, Integer totalMinute, Integer teachPeriod,
			Integer allow, String idno, String docType, String sn, String reason, String askLeaveType, String agent,
			String status, String memo, String sent, String chief, String creator, Date startDate, Date endDate,
			Date createDate, Date processDate, Date verify) {
		super();
		Oid = oid;
		this.totalDay = totalDay;
		this.totalHour = totalHour;
		this.totalMinute = totalMinute;
		this.teachPeriod = teachPeriod;
		this.allow = allow;
		this.idno = idno;
		this.docType = docType;
		this.sn = sn;
		this.reason = reason;
		this.askLeaveType = askLeaveType;
		this.agent = agent;
		this.status = status;
		this.memo = memo;
		this.sent = sent;
		this.chief = chief;
		this.creator = creator;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.processDate = processDate;
		this.verify = verify;
	}
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public Integer getTotalDay() {
		return totalDay;
	}
	public void setTotalDay(Integer totalDay) {
		this.totalDay = totalDay;
	}
	public Integer getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(Integer totalHour) {
		this.totalHour = totalHour;
	}
	public Integer getTotalMinute() {
		return totalMinute;
	}
	public void setTotalMinute(Integer totalMinute) {
		this.totalMinute = totalMinute;
	}
	public Integer getTeachPeriod() {
		return teachPeriod;
	}
	public void setTeachPeriod(Integer teachPeriod) {
		this.teachPeriod = teachPeriod;
	}
	public Integer getAllow() {
		return allow;
	}
	public void setAllow(Integer allow) {
		this.allow = allow;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAskLeaveType() {
		return askLeaveType;
	}
	public void setAskLeaveType(String askLeaveType) {
		this.askLeaveType = askLeaveType;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	public String getChief() {
		return chief;
	}
	public void setChief(String chief) {
		this.chief = chief;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public Date getVerify() {
		return verify;
	}
	public void setVerify(Date verify) {
		this.verify = verify;
	}
	

	

}
