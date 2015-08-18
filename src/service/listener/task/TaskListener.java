package service.listener.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.impl.DataFinder;

public class TaskListener extends TimerTask {
    private ServletContextEvent event = null;
    
    public TaskListener(ServletContextEvent event){
        this.event = event;
    }
    
    public void run(){

    	//context.removeAttribute("app_name");
    	ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());	    	
		DataFinder dm=(DataFinder) context.getBean("DataFinder");
		ServletContext servletContext = event.getServletContext();
		
		List<Map>tmp;
    	
    	//系統日曆
		//原始名稱為String型態
		//date_XXX為Date型態
		System.out.println("--------------------");
		System.out.println("載入日曆沿用(原始名稱)為String型態, 冠上date_(原始名稱)為Date型態");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Map<String,String>>c=dm.sqlGet("SELECT *, DATE_FORMAT(cdate,'%Y-%m-%d %H:%i')as date FROM SYS_CALENDAR WHERE name IS NOT NULL");
		for(int i=0; i<c.size(); i++){
			System.out.println("建立"+c.get(i).get("note")+":"+c.get(i).get("name")+"="+c.get(i).get("date"));
			servletContext.setAttribute(c.get(i).get("name"), c.get(i).get("date"));
			try {
				servletContext.setAttribute("date_"+c.get(i).get("name"), sf.parse(c.get(i).get("date").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println("--------------------");
		
		//FTP主機
		System.out.println("載入FTP服務為Map型態, 內容參閱SYS_HOST資料表");
		c=dm.sqlGet("SELECT * FROM SYS_HOST");
		for(int i=0; i<c.size(); i++){
			System.out.println("建立"+c.get(i).get("note")+"FTP服務(Map):"+c.get(i).get("useid"));
			servletContext.setAttribute(c.get(i).get("useid"), c.get(i));
		}
		System.out.println("--------------------");
		
		//學年學期
		System.out.println("載入學年學期為String型態, TODO 轉換Table至SYS..");
		String school_year=dm.sqlGetStr("SELECT Value FROM Parameter WHERE Name='School_year'");
		String school_term=dm.sqlGetStr("SELECT Value FROM Parameter WHERE Name='School_term'");
		servletContext.setAttribute("school_year", school_year);		
		servletContext.setAttribute("school_term", school_term);
		System.out.println("現在學年(String)school_year="+school_year);
		System.out.println("現在學期(String)school_term="+school_term);
		System.out.println("--------------------");
		
		//假期
		System.out.println("載入假期為List of Map型態");
		tmp=dm.sqlGet("SELECT Date, Date as date, Name FROM AMS_Holiday WHERE Date>='"+servletContext.getAttribute("school_term_begin")+"' AND Date<='"+
		servletContext.getAttribute("school_term_end")+"'");
		System.out.println("建立休假日期(List)holiday="+tmp.size());
		servletContext.setAttribute("holiday", tmp);
		System.out.println("--------------------");
		
		//系統
		System.out.println("載入系統列表為List of Map型態");
		tmp=dm.sqlGet("SELECT * FROM SYS WHERE parent=0");		
		for(int i=0; i<tmp.size(); i++){
			tmp.get(i).put("sub", dm.sqlGet("SELECT * FROM SYS WHERE parent="+tmp.get(i).get("Oid")));
		}		
		System.out.println("載入系統(List)allUnit="+tmp.size());
		servletContext.setAttribute("allsys", tmp);
		System.out.println("--------------------");
		
		//單位
		System.out.println("載入各單位為List of Map型態");
		List<Map>allUnit=dm.sqlGet("SELECT id,name FROM CODE_CAMPUS");
		
		for(int i=0; i<allUnit.size(); i++){
			tmp=dm.sqlGet("SELECT id,name FROM CODE_UNIT WHERE pid='0' AND campus='"+allUnit.get(i).get("id")+"'");			
			for(int j=0; j<tmp.size(); j++){				
				tmp.get(j).put("sub_unit", dm.sqlGet("SELECT id,name FROM CODE_UNIT WHERE pid='"+tmp.get(j).get("id")+"'"));
			}			
			allUnit.get(i).put("unit", tmp);
		}		
		System.out.println("載入單位(List)allUnit="+allUnit.size());
		servletContext.setAttribute("allUnit", allUnit);
		System.out.println("--------------------");
		
		//院
		System.out.println("載入院與部制(List)college");
		tmp=dm.sqlGet("SELECT id as idno, id,name FROM CODE_COLLEGE");
		for(int i=0; i<tmp.size(); i++){
			tmp.get(i).put("dept", dm.sqlGet("SELECT id as idno, id,name,sname FROM CODE_DEPT WHERE college='"+tmp.get(i).get("id")+"'"));
		}
		servletContext.setAttribute("college", tmp);
		System.out.println("--------------------");
		
		//校區部制學制		
		System.out.println("建立校區(List)allCampus");	
		servletContext.setAttribute("allCampus", dm.sqlGet("SELECT *, id as idno FROM CODE_CAMPUS"));
		System.out.println("建立日夜補(List)allSchoolType");	
		servletContext.setAttribute("allSchoolType", dm.sqlGet("SELECT *, type as idno FROM CODE_SCHOOL_TYPE"));
		System.out.println("建立學制(List)allSchool");	
		servletContext.setAttribute("allSchool", dm.sqlGet("SELECT *, id as idno FROM CODE_SCHOOL"));		
		System.out.println("建立科系(List)allDept");
		servletContext.setAttribute("allDept", dm.sqlGet("SELECT *, id as idno FROM CODE_DEPT"));		
		System.out.println("--------------------");
		
		//判斷是否為Debug模式
		System.out.println("載入判斷debug模式");
		if(dm.testOnlineServer()){
			servletContext.setAttribute("isServer", "1");
			System.out.println("是否為發佈主機(String)isServer=1");	
		}else{
			servletContext.setAttribute("isServer", "0");
			System.out.println("是否為發佈主機(String)isServer=0");
		}
		System.out.println("--------------------");
		
		//各項代碼
		System.out.println("載入各種代碼");
		System.out.println("建立獎懲代碼(List)allDesd");	
		servletContext.setAttribute("allDesd", dm.sqlGet("SELECT * FROM CODE_DESD ORDER BY id DESC"));
		
		tmp=dm.sqlGet("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema='CIS'AND table_name LIKE 'CODE%'");
		for(int i=0; i<tmp.size(); i++){
			System.out.println("建立"+tmp.get(i).get("table_name"));
			servletContext.setAttribute(tmp.get(i).get("table_name").toString(), dm.sqlGet("SELECT * FROM "+tmp.get(i).get("table_name")));
		}
		
		
		System.out.println("--------------------");
    	
		//擋修規則
		System.out.println("載入擋修規則");
		System.out.println("建立擋修規則(List)dtimeBlock");	
		tmp=dm.sqlGet("SELECT Dtime_oid FROM Dtime_block GROUP BY Dtime_oid");
		for(int i=0; i<tmp.size(); i++){
			tmp.get(i).put("cscodes", dm.sqlGet("SELECT cscode FROM Dtime_block WHERE Dtime_oid="+tmp.get(i).get("Dtime_oid")));
		}
		servletContext.setAttribute("dtimeBlock", tmp);
		System.out.println("--------------------");
		
		//權限
		System.out.println("載入程式使用權限");
		System.out.println("建立程式使用權限(List)sysmodules");
		tmp=dm.sqlGet("SELECT * FROM SYS_MODULE m");
		for(int i=0; i<tmp.size(); i++){
			tmp.get(i).put("units", dm.sqlGet("SELECT unit_id FROM SYS_MODULE_UNIT WHERE module_oid="+tmp.get(i).get("Oid")));
		}
    }

}