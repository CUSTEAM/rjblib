package model;

/**
 * EnrollDept entity. @author MyEclipse Persistence Tools
 */

public class EnrollDept implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollOid;
	private String campusNo;
	private String schoolNo;
	private String deptNo;
	private String deptName;
	private Short quota;

	// Constructors

	/** default constructor */
	public EnrollDept() {
	}

	/** full constructor */
	public EnrollDept(Integer enrollOid, String campusNo, String schoolNo, String deptNo, String deptName,
			Short quota) {
		this.enrollOid = enrollOid;
		this.campusNo = campusNo;
		this.schoolNo = schoolNo;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.quota = quota;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getEnrollOid() {
		return this.enrollOid;
	}

	public void setEnrollOid(Integer enrollOid) {
		this.enrollOid = enrollOid;
	}

	public String getCampusNo() {
		return this.campusNo;
	}

	public void setCampusNo(String campusNo) {
		this.campusNo = campusNo;
	}

	public String getSchoolNo() {
		return this.schoolNo;
	}

	public void setSchoolNo(String schoolNo) {
		this.schoolNo = schoolNo;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Short getQuota() {
		return this.quota;
	}

	public void setQuota(Short quota) {
		this.quota = quota;
	}

}