package service.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


	/**
	 * 攔截使用者動作，驗證cookie & session有效
	 * @author John
	 */
	public class UnitmoduleInterceptor implements Interceptor {
	    public void destroy() {}
	    public void init() {}	   
	    public String intercept(ActionInvocation invocation) throws Exception {  
	    	//AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:../applicationContext.xml");
	    	//DataFinder df =(DataFinder)context.getBean("DataFinder");
	        //context.registerShutdownHook();	    	
	    	//登入與登出功能不作攔截
	    	if(invocation.getAction().getClass().getName().indexOf("Log")>=0){	    		
	        	return invocation.invoke();
	        }	    	
	        HttpServletRequest request=ServletActionContext.getRequest();	        
	        List<Map>s=(List<Map>)request.getServletContext().getAttribute("sysrule");
	    	String path=request.getRequestURI();	    	
	    	//path=path.substring(path.indexOf("tw")+2, path.length());
	    	if(request.getQueryString()!=null)path+="?"+request.getQueryString();
	    	//System.out.println(path);
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
					if(s.get(j).get("path").equals(path)){
						//System.out.println("work1");
						if(s.get(j).get("unit_id").equals(unit[i])){
							//System.out.println("work2");
							return invocation.invoke();
						}
					}
				}				
			}
			//return invocation.invoke();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.sendRedirect("/eis/Logout");//轉送至eis
    		return null;
			//context.close();
	    	//登入與登出功能不作攔截
	    	/*if(invocation.getAction().getClass().getName().indexOf("Log")>=0){	    		
	        	return invocation.invoke();
	        }	        
	    	//確認是否有選單及權限session，若有則驗證為線上使用者
	    	if(session!=null){
	    		if(session.getAttribute("userid")!=null){//使用者為本專案線上使用者
	    			return invocation.invoke();
	    		}
	    	}	    	
	    	//確認是否有尚未失效的cookie-name=userid，若有則為其他系統使用者跳轉
	    	ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());	    	
    		AccountManager am=(AccountManager) context.getBean("AccountManager");
	    	if(am.loginJumpUser(request)){
	    		return invocation.invoke();
	    	}else{
	    		//沒有session也沒有cookie者轉往登入頁
	    		HttpServletResponse response=ServletActionContext.getResponse();
	    		response.sendRedirect("Logout");//轉送至eis
	    		return null; 
	    	}
	    	*/
	    	
	    	//response.sendRedirect("Logout");//轉送至eis
    		//return null; 
	    	
	    	 
	    	//System.out.println(request.getServletPath());			
			//System.out.println("full url:"+request.getRequestURL());
	    	//System.out.println("server url:"+request.getServletPath());
	    	
	    	
			/*
			System.out.println("-------session--------");
			Enumeration<String>enums = request.getSession().getAttributeNames();
			String name;
			while(enums.hasMoreElements() ){
				name=enums.nextElement();
				System.out.println( name+":"+request.getSession().getAttribute(name) );				
			}
			
			
			System.out.println("-------cookie--------");
			System.out.println("List cookies:");
			Cookie[] c=request.getCookies();
			for(int i=0; i<c.length; i++){
				System.out.println(c[i].getName()+":"+c[i].getValue());
			}
			
			
			
			List<Map>m=(List<Map>) request.getServletContext().getAttribute("sysmenu");
	    	List<Map>f;
	    	List<Map>r;
	    	for(int i=0; i<m.size(); i++){
	    		System.out.println(m.get(i).get("name"));
	    		//f=(List<Map>) m.get(i).get("menu");
	    		r=(List<Map>) m.get(i).get("rule");
	    		
	    		for(int j=0; j<r.size(); j++){
	    			System.out.println(r.get(j));
	    		}
	    	}
			
			
	    	
	    	*/
	    	
	    	//return invocation.invoke();
	    }
	} 	