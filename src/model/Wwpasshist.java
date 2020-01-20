package model;

import java.sql.Timestamp;

/**
 * Wwpasshist entity. @author MyEclipse Persistence Tools
 */

public class Wwpasshist implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String username;
	private Timestamp uptime;
	private String remoteaddress;
	private String ipaddress;
	private String useragent;
	private String loginmsg;

	// Constructors

	/** default constructor */
	public Wwpasshist() {
	}

	/** minimal constructor */
	public Wwpasshist(Timestamp uptime) {
		this.uptime = uptime;
	}

	/** full constructor */
	public Wwpasshist(String username, Timestamp uptime, String remoteaddress, String ipaddress, String useragent,
			String loginmsg) {
		this.username = username;
		this.uptime = uptime;
		this.remoteaddress = remoteaddress;
		this.ipaddress = ipaddress;
		this.useragent = useragent;
		this.loginmsg = loginmsg;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public String getRemoteaddress() {
		return this.remoteaddress;
	}

	public void setRemoteaddress(String remoteaddress) {
		this.remoteaddress = remoteaddress;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getUseragent() {
		return this.useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public String getLoginmsg() {
		return this.loginmsg;
	}

	public void setLoginmsg(String loginmsg) {
		this.loginmsg = loginmsg;
	}

}