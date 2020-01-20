package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 帳號相關應用
 * @author John
 *
 */
public class AccountManager{
	
	DataFinder dataFinder;
	DataUpdater dataUpdater;
	
	public DataFinder getDataFinder() {
		return dataFinder;
	}

	public void setDataFinder(DataFinder dataFinder) {
		this.dataFinder = dataFinder;
	}

	public DataUpdater getDataUpdater() {
		return dataUpdater;
	}

	public void setDataUpdater(DataUpdater dataUpdater) {
		this.dataUpdater = dataUpdater;
	}

	/**
	 * 跳轉使用者登入
	 * @param userid
	 * @param session
	 */
	public boolean loginJumpUser(HttpServletRequest request){
		System.out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath());
		Cookie c[]=request.getCookies();
		if(c!=null){
    		String userid;
    		for(int i=0; i<c.length; i++){
	    		if(c[i].getName().equals("userid")){
	    			userid=c[i].getValue();	    			
	    			//教職員
	    			
	    			Map user=dataFinder.sqlGetMap("SELECT e.idno as username, e.Oid, w.priority FROM wwpass w, empl e WHERE w.username=e.idno AND w.sessionid='"+userid+"'");
	    			if(user==null){//學生
	    				user=dataFinder.sqlGetMap("SELECT e.student_no as username, e.Oid, w.priority FROM wwpass w, stmd e WHERE w.username=e.student_no AND w.sessionid='"+userid+"'");
	    			}
	    			
	    			if(user==null){
	    				//已在其他地方重複登入
	    	    		return false;
	    	    	}else{
	    	    		//確定為跳轉使用者
	    	    		request.getSession(true).setAttribute("userid", user.get("username"));//<-----	 
	    	    		request.getSession(true).setAttribute("userOid", user.get("Oid"));//<-----	 
	    	    		System.out.println("已記錄userid: "+request.getSession().getAttribute("userid"));
	    	    		return true;
	    	    	}	    			
	    		}
	    	}
		}
		return false;
    }
	
	public void delUseridCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("userid", "deleted");
		cookie.setMaxAge(0); // 暫1小時有效	    	
    	cookie.setDomain(".cust.edu.tw");
    	cookie.setPath("/");
    	response.addCookie(cookie);	
	}
	
	/**
	 * 自登入表單登入
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginCheck(HttpServletRequest request, HttpServletResponse response, String username, String password){
		request.getSession().invalidate();
		delUseridCookie(response);
		Map user=dataFinder.sqlGetMap("SELECT username, priority FROM wwpass WHERE username='"+username+"' AND password='"+password+"'");
		if(user!=null){			
			//驗證成功			
			request.getSession().setAttribute("userid", username);//<---------------------- 以userid為這個專案底下的驗證			
			Cookie cookie = new Cookie("userid", request.getSession().getId()+username.hashCode());	    	
			cookie.setMaxAge(60*60); // 暫1小時有效	    	
	    	cookie.setDomain(".cust.edu.tw");
	    	cookie.setPath("/");
	    	response.addCookie(cookie);	    	
	    	//寫sessionidId	    	
	    	dataFinder.exSql("UPDATE wwpass SET sessionid='"+
	    	request.getSession().getId()+username.hashCode()+
	    	"' WHERE username='"+username+"'");
	    	
			return true;
		}else{			
			return false;
		}
	}
	
	
	
	/**
	 * 登出使用者
	 * @param request
	 * @param response
	 */
	public void logOut(HttpServletRequest request, HttpServletResponse response){
		delUseridCookie(response);
		HttpSession session=request.getSession(false);
		session.invalidate();
	}
	
	
	
	
	
	/**
	 * 建立個人特殊選單
	 */
	public Map getSpecMenu(String username){
		Map map=new HashMap();
		map.put("Label", "指定額外功能");		
		map.put("smenu", dataFinder.sqlGet("SELECT m.* FROM User_Module um, Module m WHERE " +
				"m.ParentOid=0 AND um.ModuleOid=m.Oid AND um.idno='"+username+"'"));
		return map;
	}
	
	/**
	 * 以長字串分析職員電子郵件信箱
	 * @param empl
	 * @return
	 */
	public List analyseEmpl(String empls){
		List list=dataFinder.sqlGet("SELECT e.idno, e.cname, e.Email FROM empl e WHERE e.Email IS NOT NULL AND e.Email!=''");//只以教職員為範本
		List tmp=new ArrayList();
		for(int i=0; i<list.size(); i++){			
			if(empls.indexOf(((Map)list.get(i)).get("cname").toString())>=0){//分析出這個人了				
				tmp.add(list.get(i));
				empls=empls.replaceAll(((Map)list.get(i)).get("cname").toString(),"");//刪除已分析字元
			}
		}
		list=null;
		return tmp;
	}
	
	/**
	 * 以empl Oid取idno
	 * @param emplOid
	 * @return
	 */
	public String getEmplIdno(String Oid){		
		return dataFinder.sqlGetStr("SELECT idno FROM empl WHERE Oid="+Oid);
	}
	
	/**
	 * 以empl idno取Oid
	 * @param emplOid
	 * @return
	 */
	public String getEmplOid(String idno){		
		return dataFinder.sqlGetStr("SELECT Oid FROM empl WHERE idno='"+idno+"'");
	}
}
