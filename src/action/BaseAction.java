package action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import model.Message;
import service.impl.AccountManager;
import service.impl.CourseManager;
import service.impl.DataFinder;
import service.impl.DataUpdater;
import service.impl.StudAffairManager;
import service.impl.base.BaseAccessImpl;
import service.impl.base.BaseIOImpl;
import service.impl.base.BaseLiteralImpl;
import service.impl.base.BaseMathImpl;

/**
 * action
 * @author John *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware {	
	
	private static final long serialVersionUID = 1L;

	ServletContext application = ServletActionContext.getRequest().getSession().getServletContext();	
	
	protected ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;	
	
	//protected final Log log = LogFactory.getLog(getClass());	
	
	/**
	 * 初始ServletRequestAware
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 初始ServletResponseAware
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}	
	
	/**
	 * 初始SessionAware
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * spring application context
	 */
	public void setServletContext(ServletContext application) {
		this.application = application;
	}
	
	/**
	 * 取得欲使用的beanManager
	 * @param name
	 * @return
	 */
	protected Object get(String name) {
		return applicationContext.getBean(name);
	}
	
	/**
	 * 獲取原始session
	 * @return
	 */
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
    }
	
	/**
	 * 原生request
	 * @return
	 */
    public static HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }
    
    /**
     * 原生reponse
     * @return
     */
    public static HttpServletResponse getReponse(){
        return ServletActionContext.getResponse();
    }
    
    /**
     * 取 Server Context
     */
    public ServletContext getContext(){    	
    	return request.getServletContext();
    }
    
    /**
     * 基本訊息
	 * errorMsg   紅
	 * warningMsg 橘
	 * infoMsg    藍
	 * successMsg 綠
     */
    public void savMessage(Message msg){
    	getRequest().setAttribute("msg", msg);
    }    
    
    /**
     * 數學
     * @return
     */    
    public BaseMathImpl bm = (BaseMathImpl) get("BaseMathImpl");
    
    /**
     * 文字
     * @return
     */    
    public BaseLiteralImpl bl = (BaseLiteralImpl) get("BaseLiteralImpl");
    
    /**
     * 輸入/出
     * @return
     */
    public BaseIOImpl bio = (BaseIOImpl) get("BaseIOImpl");
    
    /**
     * 資料存取
     * @return
     */
    public BaseAccessImpl dm = (BaseAccessImpl) get("DataManager");    
    
    /**
     * 資料查詢
     * @return
     */
    public DataFinder df = (DataFinder) get("DataFinder");
    
    /**
     * 資料修改
     * @return
     */    
    public DataUpdater du = (DataUpdater) get("DataUpdater");
    
    /**
     * 帳號
     * @return
     */
    public AccountManager am = (AccountManager) get("AccountManager");
    
    /**
     * 課務
     * @return
     */
    public CourseManager cm = (CourseManager) get("CourseManager");
    
    /**
     * 學務
     * @return
     */
    public StudAffairManager sam = (StudAffairManager) get("StudAffairManager");    
    
}
