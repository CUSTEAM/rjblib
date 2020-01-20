package model;

import java.util.Date;

/**
 * EnrollStmd entity. @author MyEclipse Persistence Tools
 */

public class EnrollStmd implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String idno;
	private String studentName;
	private String sex;
	private Date birthday;
	private Short gradyear;
	private String graduStatus;
	private String currPost;
	private String currAddr;
	private String schlName;
	private String gradDept;
	private String parentName;
	private String parentPhone;
	private String telephone;
	private String cellPhone;
	private String permPost;
	private String permAddr;
	private String email;
	private String geocode;
	private String liner;
	private Integer distance;
	private Integer duration;
	private String jobinf;
	private String dis;
	private String low;
	private String departClass;
	private String studentNo;

	// Constructors

	/** default constructor */
	public EnrollStmd() {
	}

	/** minimal constructor */
	public EnrollStmd(Date birthday) {
		this.birthday = birthday;
	}

	/** full constructor */
	public EnrollStmd(String idno, String studentName, String sex, Date birthday, Short gradyear, String graduStatus,
			String currPost, String currAddr, String schlName, String gradDept, String parentName, String parentPhone,
			String telephone, String cellPhone, String permPost, String permAddr, String email, String geocode,
			String liner, Integer distance, Integer duration, String jobinf, String dis, String low, String departClass,
			String studentNo) {
		this.idno = idno;
		this.studentName = studentName;
		this.sex = sex;
		this.birthday = birthday;
		this.gradyear = gradyear;
		this.graduStatus = graduStatus;
		this.currPost = currPost;
		this.currAddr = currAddr;
		this.schlName = schlName;
		this.gradDept = gradDept;
		this.parentName = parentName;
		this.parentPhone = parentPhone;
		this.telephone = telephone;
		this.cellPhone = cellPhone;
		this.permPost = permPost;
		this.permAddr = permAddr;
		this.email = email;
		this.geocode = geocode;
		this.liner = liner;
		this.distance = distance;
		this.duration = duration;
		this.jobinf = jobinf;
		this.dis = dis;
		this.low = low;
		this.departClass = departClass;
		this.studentNo = studentNo;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Short getGradyear() {
		return this.gradyear;
	}

	public void setGradyear(Short gradyear) {
		this.gradyear = gradyear;
	}

	public String getGraduStatus() {
		return this.graduStatus;
	}

	public void setGraduStatus(String graduStatus) {
		this.graduStatus = graduStatus;
	}

	public String getCurrPost() {
		return this.currPost;
	}

	public void setCurrPost(String currPost) {
		this.currPost = currPost;
	}

	public String getCurrAddr() {
		return this.currAddr;
	}

	public void setCurrAddr(String currAddr) {
		this.currAddr = currAddr;
	}

	public String getSchlName() {
		return this.schlName;
	}

	public void setSchlName(String schlName) {
		this.schlName = schlName;
	}

	public String getGradDept() {
		return this.gradDept;
	}

	public void setGradDept(String gradDept) {
		this.gradDept = gradDept;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhone() {
		return this.parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPermPost() {
		return this.permPost;
	}

	public void setPermPost(String permPost) {
		this.permPost = permPost;
	}

	public String getPermAddr() {
		return this.permAddr;
	}

	public void setPermAddr(String permAddr) {
		this.permAddr = permAddr;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGeocode() {
		return this.geocode;
	}

	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}

	public String getLiner() {
		return this.liner;
	}

	public void setLiner(String liner) {
		this.liner = liner;
	}

	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getJobinf() {
		return this.jobinf;
	}

	public void setJobinf(String jobinf) {
		this.jobinf = jobinf;
	}

	public String getDis() {
		return this.dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	public String getLow() {
		return this.low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getDepartClass() {
		return this.departClass;
	}

	public void setDepartClass(String departClass) {
		this.departClass = departClass;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

}