package model;

import java.sql.Timestamp;

/**
 * Enroll entity. @author MyEclipse Persistence Tools
 */

public class Enroll implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Short schoolYear;
	private Timestamp signBegin;
	private Timestamp signEnd;
	private Timestamp openScore;
	private Timestamp openMatch;
	private String enrollName;
	private Short regFee;
	private Byte subsel;
	private String note;
	private String brochure;
	private String envelope;

	// Constructors

	/** default constructor */
	public Enroll() {
	}

	/** full constructor */
	public Enroll(Short schoolYear, Timestamp signBegin, Timestamp signEnd, Timestamp openScore, Timestamp openMatch,
			String enrollName, Short regFee, Byte subsel, String note, String brochure, String envelope) {
		this.schoolYear = schoolYear;
		this.signBegin = signBegin;
		this.signEnd = signEnd;
		this.openScore = openScore;
		this.openMatch = openMatch;
		this.enrollName = enrollName;
		this.regFee = regFee;
		this.subsel = subsel;
		this.note = note;
		this.brochure = brochure;
		this.envelope = envelope;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Short getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(Short schoolYear) {
		this.schoolYear = schoolYear;
	}

	public Timestamp getSignBegin() {
		return this.signBegin;
	}

	public void setSignBegin(Timestamp signBegin) {
		this.signBegin = signBegin;
	}

	public Timestamp getSignEnd() {
		return this.signEnd;
	}

	public void setSignEnd(Timestamp signEnd) {
		this.signEnd = signEnd;
	}

	public Timestamp getOpenScore() {
		return this.openScore;
	}

	public void setOpenScore(Timestamp openScore) {
		this.openScore = openScore;
	}

	public Timestamp getOpenMatch() {
		return this.openMatch;
	}

	public void setOpenMatch(Timestamp openMatch) {
		this.openMatch = openMatch;
	}

	public String getEnrollName() {
		return this.enrollName;
	}

	public void setEnrollName(String enrollName) {
		this.enrollName = enrollName;
	}

	public Short getRegFee() {
		return this.regFee;
	}

	public void setRegFee(Short regFee) {
		this.regFee = regFee;
	}

	public Byte getSubsel() {
		return this.subsel;
	}

	public void setSubsel(Byte subsel) {
		this.subsel = subsel;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBrochure() {
		return this.brochure;
	}

	public void setBrochure(String brochure) {
		this.brochure = brochure;
	}

	public String getEnvelope() {
		return this.envelope;
	}

	public void setEnvelope(String envelope) {
		this.envelope = envelope;
	}

}