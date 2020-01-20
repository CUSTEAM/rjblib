package ajax;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import action.BaseAction;
 
/**
 * 證錄線上人數
 * TODO 即時訊息、即時警告、即時...
 * @author John
 */
public class onlineCheck extends BaseAction{
	
	private Map onlineInfo;	
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public String execute() {
		Date date=new Date();
		Map map=new HashMap();
		map.put("userid", getSession().getAttribute("userid"));		
		this.setOnlineInfo(map);
		//System.out.println(sf.format(new Date()));
		df.exSql("UPDATE wwpass SET online='"+sf.format(date)+"'WHERE username='"+getSession().getAttribute("userid")+"'");		
        return SUCCESS;
    }


	public Map getOnlineInfo() {
		return onlineInfo;
	}


	public void setOnlineInfo(Map onlineInfo) {
		this.onlineInfo = onlineInfo;
	}	
	
	
}