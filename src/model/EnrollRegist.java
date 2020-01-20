package model;

/**
 * EnrollRegist entity. @author MyEclipse Persistence Tools
 */

public class EnrollRegist implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollOid;
	private String idno;
	private Short score1;
	private Short score2;
	private Short score3;
	private Short score;
	private String no;

	// Constructors

	/** default constructor */
	public EnrollRegist() {
	}

	/** full constructor */
	public EnrollRegist(Integer enrollOid, String idno, Short score1, Short score2, Short score3, Short score,
			String no) {
		this.enrollOid = enrollOid;
		this.idno = idno;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		this.score = score;
		this.no = no;
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

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Short getScore1() {
		return this.score1;
	}

	public void setScore1(Short score1) {
		this.score1 = score1;
	}

	public Short getScore2() {
		return this.score2;
	}

	public void setScore2(Short score2) {
		this.score2 = score2;
	}

	public Short getScore3() {
		return this.score3;
	}

	public void setScore3(Short score3) {
		this.score3 = score3;
	}

	public Short getScore() {
		return this.score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}