package service.listener.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.impl.DataFinder;

public class TaskListener extends TimerTask {
    private ServletContextEvent event = null;
    
    public TaskListener(ServletContextEvent event){
        this.event = event;
    }
    
    public void run(){

    	//context.removeAttribute("app_name");
    	//ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());	    	
		//DataFinder dm=(DataFinder) context.getBean("DataFinder");
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:../applicationContext.xml");
    	DataFinder dm =(DataFinder)context.getBean("DataFinder");
        context.registerShutdownHook();		
		ServletContext servletContext = event.getServletContext();
		
		List<Map>tmp;
		List<Map>c, d;
    	//系統日曆
		//原始名稱為String型態
		//date_XXX為Date型態
		System.out.println("--------------------");
		System.out.println("載入日曆沿用(原始名稱)為String型態, 冠上date_(原始名稱)為Date型態");
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		c=dm.sqlGet("SELECT *, DATE_FORMAT(cdate,'%Y-%m-%d %H:%i')as date FROM SYS_CALENDAR WHERE name IS NOT NULL");
		for(int i=0; i<c.size(); i++){
			System.out.println("建立"+c.get(i).get("note")+":"+c.get(i).get("name")+"="+c.get(i).get("date"));
			servletContext.setAttribute(c.get(i).get("name").toString(), c.get(i).get("date"));
			try {
				servletContext.setAttribute("date_"+c.get(i).get("name"), sf.parse(c.get(i).get("date").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("--------------------");
		
		//週次
		System.out.println("載入週次為Integer型態, 根據school_term_begin推算");
		try{
			Calendar cal1=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			cal1.setTime(sf.parse(servletContext.getAttribute("school_term_begin").toString()));
			cal2.setTime(new Date());
			int w=0;
			while(cal1.getTimeInMillis()<cal2.getTimeInMillis()){
				cal1.add(Calendar.DAY_OF_YEAR, 7);
				w++;
			}
			servletContext.setAttribute("sweek", w);	
			System.out.println("載入週次: "+w);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		System.out.println("--------------------");
		
		
		//FTP主機
		System.out.println("載入FTP服務為Map型態, 內容參閱SYS_HOST資料表");
		c=dm.sqlGet("SELECT * FROM SYS_HOST");
		for(int i=0; i<c.size(); i++){
			System.out.println("建立"+c.get(i).get("note")+"FTP服務(Map):"+c.get(i).get("useid"));
			servletContext.setAttribute(c.get(i).get("useid").toString(), c.get(i));
		}
		System.out.println("--------------------");
		
		//學年學期
		System.out.println("載入學年學期為String型態, TODO: 轉換Table至SYS..");
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
		//TODO 網路選課程式更新時重新設計擋修
		System.out.println("載入擋修規則");
		System.out.println("建立擋修規則(List)dtimeBlock");	
		tmp=dm.sqlGet("SELECT Dtime_oid FROM Dtime_block GROUP BY Dtime_oid");
		for(int i=0; i<tmp.size(); i++){
			tmp.get(i).put("cscodes", dm.sqlGet("SELECT cscode FROM Dtime_block WHERE Dtime_oid="+tmp.get(i).get("Dtime_oid")));
		}
		servletContext.setAttribute("dtimeBlock", tmp);
		System.out.println("--------------------");
		
		//階層式選單
		System.out.println("載入程式清單(有階層)");
		System.out.println("建立程式清單(List)sysmenu");
		tmp=dm.sqlGet("SELECT * FROM SYS_MODULE WHERE parent=0 ORDER BY seq");//第0層
		for(int i=0; i<tmp.size(); i++){
			c=dm.sqlGet("SELECT * FROM SYS_MODULE WHERE parent="+tmp.get(i).get("Oid")+" ORDER BY seq");//第1層			
			for(int j=0; j<c.size(); j++){
				d=dm.sqlGet("SELECT * FROM SYS_MODULE WHERE parent="+c.get(j).get("Oid")+" ORDER BY seq");
				for(int k=0; k<d.size(); k++){
					d.get(k).put("menu", dm.sqlGet("SELECT * FROM SYS_MODULE WHERE parent="+d.get(k).get("Oid")+" ORDER BY seq"));
				}
				c.get(j).put("menu", d);//第2層				
			}
			tmp.get(i).put("menu", c);
			tmp.get(i).put("rule", dm.sqlGet("SELECT * FROM SYS_MODULE_UNIT WHERE module_oid="+tmp.get(i).get("Oid")));
		}		
		servletContext.setAttribute("sysmenu", tmp);
		System.out.println("--------------------");
		
		//選單
		System.out.println("載入使用權限");
		System.out.println("建立使用權限(List)sysrule");				
		servletContext.setAttribute("sysrule", dm.sqlGet("SELECT u.unit_id, m.path FROM SYS_MODULE_UNIT u, SYS_MODULE m WHERE u.module_oid=m.base AND m.path IS NOT NULL"));
		System.out.println("--------------------");
		
		
		
		
		//節次時間
		System.out.println("載入節次時間對應");
		System.out.println("建立節次時間對應(List)dtimestamp, 周一至五參考台北四技，六日參考台北二技");		
		tmp=new ArrayList();
		Map m;
		for(int i=1; i<=7; i++){
			m=new HashMap();
			m.put("week", i);
			if(i<6){
				tmp.addAll(dm.sqlGet("SELECT d.DSvalue, d.DSreal, d.DSbegin, d.DSend FROM Dtimestamp d WHERE "
						+ "d.DSweek='"+i+"' AND d.Cidno='1'  GROUP BY d.DSreal ORDER BY d.DSreal"));
			}else{
				tmp.addAll(dm.sqlGet("SELECT d.DSvalue, d.DSreal, d.DSbegin, d.DSend FROM Dtimestamp d WHERE d.DSweek='"+i+"' AND d.Cidno='1' AND d.Sidno='72'GROUP BY d.DSreal ORDER BY d.DSreal"));
				
			}			
		}
		servletContext.setAttribute("dtimestamp", tmp);
		System.out.println("--------------------");
		context.close();
    }

}