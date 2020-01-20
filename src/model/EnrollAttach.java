package model;

/**
 * EnrollAttach entity. @author MyEclipse Persistence Tools
 */

public class EnrollAttach implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollOid;
	private String attachName;
	private String online;
	private String note;

	// Constructors

	/** default constructor */
	public EnrollAttach() {
	}

	/** full constructor */
	public EnrollAttach(Integer enrollOid, String attachName, String online, String note) {
		this.enrollOid = enrollOid;
		this.attachName = attachName;
		this.online = online;
		this.note = note;
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

	public String getAttachName() {
		return this.attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getOnline() {
		return this.online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}