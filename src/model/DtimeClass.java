package model;

import java.util.Date;

/**
 * DtimeClass entity. @author MyEclipse Persistence Tools
 */

public class DtimeClass implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Integer dtimeOid;
	private Integer week;
	private String begin;
	private String end;
	private String place;
	private Date beginDate;
	private Date endDate;

	// Constructors

	/** default constructor */
	public DtimeClass() {
	}

	/** minimal constructor */
	public DtimeClass(Integer dtimeOid, Integer week, String begin, String end) {
		this.dtimeOid = dtimeOid;
		this.week = week;
		this.begin = begin;
		this.end = end;
	}

	/** full constructor */
	public DtimeClass(Integer dtimeOid, Integer week, String begin, String end, String place, Date beginDate,
			Date endDate) {
		this.dtimeOid = dtimeOid;
		this.week = week;
		this.begin = begin;
		this.end = end;
		this.place = place;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getDtimeOid() {
		return this.dtimeOid;
	}

	public void setDtimeOid(Integer dtimeOid) {
		this.dtimeOid = dtimeOid;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getBegin() {
		return this.begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}