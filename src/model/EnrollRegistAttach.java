package model;

/**
 * EnrollRegistAttach entity. @author MyEclipse Persistence Tools
 */

public class EnrollRegistAttach implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer enrollRegistOid;
	private Integer enrollAttachOid;
	private String path;
	private String status;

	// Constructors

	/** default constructor */
	public EnrollRegistAttach() {
	}

	/** full constructor */
	public EnrollRegistAttach(Integer enrollRegistOid, Integer enrollAttachOid, String path, String status) {
		this.enrollRegistOid = enrollRegistOid;
		this.enrollAttachOid = enrollAttachOid;
		this.path = path;
		this.status = status;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getEnrollRegistOid() {
		return this.enrollRegistOid;
	}

	public void setEnrollRegistOid(Integer enrollRegistOid) {
		this.enrollRegistOid = enrollRegistOid;
	}

	public Integer getEnrollAttachOid() {
		return this.enrollAttachOid;
	}

	public void setEnrollAttachOid(Integer enrollAttachOid) {
		this.enrollAttachOid = enrollAttachOid;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}