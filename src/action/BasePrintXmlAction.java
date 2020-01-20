package action;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 應付政府文件標準格式續階實施計畫
 * 暫時替代方案
 * @author John
 */
public class BasePrintXmlAction extends BaseAction{
	
	public void xml2ods(HttpServletResponse response, HttpServletRequest request, Date date) {		
		String doctype="msf";
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies!=null) {
				for(int i=0;i<cookies.length;i++){
					if(cookies[i].getName().equals("doctype")){
						doctype=cookies[i].getValue();
					}
		        }        			
			}
			
			if(!doctype.equals("msf")) {
				response.setContentType("application/vnd.oasis.opendocument.spreadsheet; charset=UTF-8");
		        response.setHeader("Content-disposition","attachment;filename="+new Date().getTime()+".ods");
			}else {
				response.setContentType("application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");		
			}
		}catch(Exception e) {
			//任何鳥問題都只給MS報表
			response.setContentType("application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");
		}		
	}
}