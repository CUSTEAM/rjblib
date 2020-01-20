package model;

/**
 * EnrollRegistDept entity. @author MyEclipse Persistence Tools
 */

public class EnrollRegistDept implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollDeptOid;
	private String idno;
	private Byte choice;
	private Short rank;

	// Constructors

	/** default constructor */
	public EnrollRegistDept() {
	}

	/** full constructor */
	public EnrollRegistDept(Integer enrollDeptOid, String idno, Byte choice, Short rank) {
		this.enrollDeptOid = enrollDeptOid;
		this.idno = idno;
		this.choice = choice;
		this.rank = rank;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getEnrollDeptOid() {
		return this.enrollDeptOid;
	}

	public void setEnrollDeptOid(Integer enrollDeptOid) {
		this.enrollDeptOid = enrollDeptOid;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Byte getChoice() {
		return this.choice;
	}

	public void setChoice(Byte choice) {
		this.choice = choice;
	}

	public Short getRank() {
		return this.rank;
	}

	public void setRank(Short rank) {
		this.rank = rank;
	}

}