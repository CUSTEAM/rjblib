package service.interceptor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import model.SysLog;
import model.SysLogSecurity;
import service.impl.DataFinder;


	/**
	 * 攔截使用者動作，驗證cookie & session有效
	 * @author John
	 */
	public class UnitmoduleInterceptor implements Interceptor {
	    public void destroy() {}
	    public void init() {}	   
	    public String intercept(ActionInvocation invocation) throws Exception {  	    	
	    	//登入與登出功能不作攔截
	    	if(invocation.getAction().getClass().getName().indexOf("Log")>=0){	    		
	        	return invocation.invoke();
	        }	    	
	        HttpServletRequest request=ServletActionContext.getRequest();	        
	        List<Map<String,String>>s=(List<Map<String,String>>)request.getServletContext().getAttribute("sysrule");  	
	        String path=request.getRequestURI();	
	        //若有帶參數時要切參數出來判斷
	    	if(request.getQueryString()!=null)path+="?"+request.getQueryString();
	    	Cookie c[]=request.getCookies();
	    	String unit[] = null;
			for(int i=0; i<c.length; i++){		
				if(c[i].getName().equals("unit"))
				unit=c[i].getValue().split(",");
			}				
			if(unit!=null)
			for(int i=0; i<unit.length; i++){				
				for(int j=0; j<s.size(); j++){
					//System.out.println(unit[i]+":"+s.get(j).get("unit_id"));	
					if(path.indexOf(s.get(j).get("path").toString())>-1){						
						if(s.get(j).get("unit_id").equals(unit[i])){
							//權限相符執行
							return invocation.invoke();
						}
					}
				}				
			}
			
			//權限不符記錄並跳出
			HttpSession session=ServletActionContext.getRequest().getSession();
			HttpServletResponse response=ServletActionContext.getResponse();
			AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:../applicationContext.xml");
			DataFinder df =(DataFinder)context.getBean("DataFinder");
            context.registerShutdownHook(); 
            SysLogSecurity log=new SysLogSecurity();
			log.setAction(invocation.getAction().getClass().getName());
			log.setUserid(String.valueOf(session.getAttribute("userid")));
			log.setAgent(request.getHeader("User-Agent"));
			log.setRemoteaddress(request.getHeader("X-FORWARDED-FOR"));
			log.setIpaddress(request.getRemoteAddr());
			log.setNote(getHeader(request));
			log.setUpTime(new Timestamp(new Date().getTime()));
    		df.update(log);			
			context.close();
			response.sendRedirect("/ssos/Status511");//轉送至eis
    		return null;
	    }
	    
	    private String getHeader(HttpServletRequest request) {
	        //Map<String, String> result = new HashMap<>();
			StringBuilder sb=new StringBuilder();
	        Enumeration headerNames = request.getHeaderNames();
	        String key;
	        while (headerNames.hasMoreElements()) {
	            key = (String) headerNames.nextElement();            
	            sb.append(request.getHeader(key)+": "+request.getHeader(key)+"\n");
	        }
	        sb.append("remote-host: "+request.getRemoteHost());
	        return sb.toString();
	    }
}