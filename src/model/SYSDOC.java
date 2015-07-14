package model;

import java.util.Date;

/**
 * @author John
 *
 */
public class SYSDOC {
	
	public SYSDOC(){
		
	}
	
	public SYSDOC(Integer oid, Integer sys, String title, String question,
			String send_unit, String sender, String note, String reply,
			String editor, String tester, String review, String review_final,
			String scope, String type, Date c_date, Date e_date) {
		super();
		Oid = oid;
		this.sys = sys;
		this.title = title;
		this.question = question;
		this.send_unit = send_unit;
		this.sender = sender;
		this.note = note;
		this.reply = reply;
		this.editor = editor;
		this.tester = tester;
		this.review = review;
		this.review_final = review_final;
		this.scope = scope;
		this.type = type;
		this.c_date = c_date;
		this.e_date = e_date;
	}
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public Integer getSys() {
		return sys;
	}
	public void setSys(Integer sys) {
		this.sys = sys;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSend_unit() {
		return send_unit;
	}
	public void setSend_unit(String send_unit) {
		this.send_unit = send_unit;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getTester() {
		return tester;
	}
	public void setTester(String tester) {
		this.tester = tester;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReview_final() {
		return review_final;
	}
	public void setReview_final(String review_final) {
		this.review_final = review_final;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	public Date getE_date() {
		return e_date;
	}
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	private Integer Oid;
	private Integer sys;
	private String title;
	private String question;
	private String send_unit;
	private String note;
	private String reply;
	private String editor;
	private String sender;
	private String tester;
	private String review;
	private String review_final;
	private String scope;
	private String type;	
	private Date c_date;
	private Date e_date;
	
	
	
	
	
	

}
