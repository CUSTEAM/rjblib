package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Dipost entity. @author MyEclipse Persistence Tools
 */

public class Dipost implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String studentNo;
	private String officeNo;
	private String acctNo;
	private Integer money;
	private String kind;
	private String modifier;
	private Timestamp lastModified;
	private String schoolYear;
	private String schoolTerm;
	private Integer occurMonth;
	private Date insuranceBegin;
	private Date insuranceEnd;
	private String dis;
	private Integer hirerLabor;
	private Integer hirerHealth;
	private Integer hirerHealthTwo;
	private Integer hirerRetire;
	private Integer selfLabor;
	private Integer selfHealth;
	private Integer selfHealthTwo;
	private Integer pubmoney;
	private Integer payables;
	private Integer originEdu;
	private Integer originMst;
	private Integer originSelf;
	private Integer originOther;
	private Short hours;
	private String jobResearch;
	private String jobTeach;
	private String jobService;
	private String payStudy;
	private String payLabor;
	private String payService;
	private String moneyWork;
	private String moneyLife;
	private String unit;
	private Integer rcAio;

	// Constructors

	/** default constructor */
	public Dipost() {
	}

	/** minimal constructor */
	public Dipost(String studentNo, Timestamp lastModified) {
		this.studentNo = studentNo;
		this.lastModified = lastModified;
	}

	/** full constructor */
	public Dipost(String studentNo, String officeNo, String acctNo, Integer money, String kind, String modifier,
			Timestamp lastModified, String schoolYear, String schoolTerm, Integer occurMonth, Date insuranceBegin,
			Date insuranceEnd, String dis, Integer hirerLabor, Integer hirerHealth, Integer hirerHealthTwo,
			Integer hirerRetire, Integer selfLabor, Integer selfHealth, Integer selfHealthTwo, Integer pubmoney,
			Integer payables, Integer originEdu, Integer originMst, Integer originSelf, Integer originOther, Short hours,
			String jobResearch, String jobTeach, String jobService, String payStudy, String payLabor, String payService,
			String moneyWork, String moneyLife, String unit, Integer rcAio) {
		this.studentNo = studentNo;
		this.officeNo = officeNo;
		this.acctNo = acctNo;
		this.money = money;
		this.kind = kind;
		this.modifier = modifier;
		this.lastModified = lastModified;
		this.schoolYear = schoolYear;
		this.schoolTerm = schoolTerm;
		this.occurMonth = occurMonth;
		this.insuranceBegin = insuranceBegin;
		this.insuranceEnd = insuranceEnd;
		this.dis = dis;
		this.hirerLabor = hirerLabor;
		this.hirerHealth = hirerHealth;
		this.hirerHealthTwo = hirerHealthTwo;
		this.hirerRetire = hirerRetire;
		this.selfLabor = selfLabor;
		this.selfHealth = selfHealth;
		this.selfHealthTwo = selfHealthTwo;
		this.pubmoney = pubmoney;
		this.payables = payables;
		this.originEdu = originEdu;
		this.originMst = originMst;
		this.originSelf = originSelf;
		this.originOther = originOther;
		this.hours = hours;
		this.jobResearch = jobResearch;
		this.jobTeach = jobTeach;
		this.jobService = jobService;
		this.payStudy = payStudy;
		this.payLabor = payLabor;
		this.payService = payService;
		this.moneyWork = moneyWork;
		this.moneyLife = moneyLife;
		this.unit = unit;
		this.rcAio = rcAio;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getOfficeNo() {
		return this.officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Timestamp getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSchoolTerm() {
		return this.schoolTerm;
	}

	public void setSchoolTerm(String schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	public Integer getOccurMonth() {
		return this.occurMonth;
	}

	public void setOccurMonth(Integer occurMonth) {
		this.occurMonth = occurMonth;
	}

	public Date getInsuranceBegin() {
		return this.insuranceBegin;
	}

	public void setInsuranceBegin(Date insuranceBegin) {
		this.insuranceBegin = insuranceBegin;
	}

	public Date getInsuranceEnd() {
		return this.insuranceEnd;
	}

	public void setInsuranceEnd(Date insuranceEnd) {
		this.insuranceEnd = insuranceEnd;
	}

	public String getDis() {
		return this.dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	public Integer getHirerLabor() {
		return this.hirerLabor;
	}

	public void setHirerLabor(Integer hirerLabor) {
		this.hirerLabor = hirerLabor;
	}

	public Integer getHirerHealth() {
		return this.hirerHealth;
	}

	public void setHirerHealth(Integer hirerHealth) {
		this.hirerHealth = hirerHealth;
	}

	public Integer getHirerHealthTwo() {
		return this.hirerHealthTwo;
	}

	public void setHirerHealthTwo(Integer hirerHealthTwo) {
		this.hirerHealthTwo = hirerHealthTwo;
	}

	public Integer getHirerRetire() {
		return this.hirerRetire;
	}

	public void setHirerRetire(Integer hirerRetire) {
		this.hirerRetire = hirerRetire;
	}

	public Integer getSelfLabor() {
		return this.selfLabor;
	}

	public void setSelfLabor(Integer selfLabor) {
		this.selfLabor = selfLabor;
	}

	public Integer getSelfHealth() {
		return this.selfHealth;
	}

	public void setSelfHealth(Integer selfHealth) {
		this.selfHealth = selfHealth;
	}

	public Integer getSelfHealthTwo() {
		return this.selfHealthTwo;
	}

	public void setSelfHealthTwo(Integer selfHealthTwo) {
		this.selfHealthTwo = selfHealthTwo;
	}

	public Integer getPubmoney() {
		return this.pubmoney;
	}

	public void setPubmoney(Integer pubmoney) {
		this.pubmoney = pubmoney;
	}

	public Integer getPayables() {
		return this.payables;
	}

	public void setPayables(Integer payables) {
		this.payables = payables;
	}

	public Integer getOriginEdu() {
		return this.originEdu;
	}

	public void setOriginEdu(Integer originEdu) {
		this.originEdu = originEdu;
	}

	public Integer getOriginMst() {
		return this.originMst;
	}

	public void setOriginMst(Integer originMst) {
		this.originMst = originMst;
	}

	public Integer getOriginSelf() {
		return this.originSelf;
	}

	public void setOriginSelf(Integer originSelf) {
		this.originSelf = originSelf;
	}

	public Integer getOriginOther() {
		return this.originOther;
	}

	public void setOriginOther(Integer originOther) {
		this.originOther = originOther;
	}

	public Short getHours() {
		return this.hours;
	}

	public void setHours(Short hours) {
		this.hours = hours;
	}

	public String getJobResearch() {
		return this.jobResearch;
	}

	public void setJobResearch(String jobResearch) {
		this.jobResearch = jobResearch;
	}

	public String getJobTeach() {
		return this.jobTeach;
	}

	public void setJobTeach(String jobTeach) {
		this.jobTeach = jobTeach;
	}

	public String getJobService() {
		return this.jobService;
	}

	public void setJobService(String jobService) {
		this.jobService = jobService;
	}

	public String getPayStudy() {
		return this.payStudy;
	}

	public void setPayStudy(String payStudy) {
		this.payStudy = payStudy;
	}

	public String getPayLabor() {
		return this.payLabor;
	}

	public void setPayLabor(String payLabor) {
		this.payLabor = payLabor;
	}

	public String getPayService() {
		return this.payService;
	}

	public void setPayService(String payService) {
		this.payService = payService;
	}

	public String getMoneyWork() {
		return this.moneyWork;
	}

	public void setMoneyWork(String moneyWork) {
		this.moneyWork = moneyWork;
	}

	public String getMoneyLife() {
		return this.moneyLife;
	}

	public void setMoneyLife(String moneyLife) {
		this.moneyLife = moneyLife;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getRcAio() {
		return this.rcAio;
	}

	public void setRcAio(Integer rcAio) {
		this.rcAio = rcAio;
	}

}