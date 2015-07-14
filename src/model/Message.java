package model;

/**
 * 頁面對話
 * @author John
 * @param error 訊息(紅)
 * @param warning 訊息(橘)
 * @param info 訊息(藍)
 * @param success 訊息(綠)
 * @param msg 訊息(黑)
 */
public class Message{
	
	private String error;
	private String warning;
	private String info;
	private String success;
	private String msg;
	
	public Message(String error, String warning, String info, String success, String msg){			
		this.error=error;
		this.warning=warning;
		this.info=info;
		this.success=success;
		this.msg=msg;
	}
	
	public Message(){
		
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	/**
	 * 追加錯誤訊息(紅)
	 * @param error
	 */
	public void addError(String error){
		this.error=this.error+error;
	}
	
	/**
	 * 追加文字訊息(黑)
	 * @param msg
	 */
	public void addMsg(String msg){
		this.msg=this.msg+msg;
	}
	
	/**
	 * 增加警告資訊(橘)
	 * @param warning
	 */
	public void addWarning(String warning){
		this.warning=this.warning+warning;
	}
	
	/**
	 * 增加資訊(藍)
	 * @param info
	 */
	public void addInfo(String info){
		this.info=this.info+info;
	}
	
	/**
	 * 增加成功資訊(綠)
	 * @param success
	 */
	public void addSuccess(String success){
		this.success=this.success+success;
	}
	
}
