package model;

/**
 * AqQuestion generated by MyEclipse Persistence Tools
 */

public class AqQuestion implements java.io.Serializable {

	// Fields

	private Integer oid;

	private String uid;

	private String question;

	// Constructors

	/** default constructor */
	public AqQuestion() {
	}

	/** full constructor */
	public AqQuestion(String uid, String question) {
		this.uid = uid;
		this.question = question;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}