package service.impl;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import service.impl.base.BaseAccessImpl;

/**
 * 資料查詢高階方法
 * @author John
 *
 */
public class DataFinder extends BaseAccessImpl{
	
	/**
	 * 依部制找尋開學日期列表
	 * @param daynite 部制，可以為空
	 * @return
	 */
	public List Week_ALL_daynite_LIST(String daynite){		
		return sqlGet("SELECT * FROM week_EX WHERE daynite LIKE'"+daynite+"%'");
	}
	
	/**
	 * 單一部制開學日期
	 * @param daynite
	 * @return
	 */
	public String Week_ALL_daynite_STRING(String daynite){		
		return sqlGetStr("SELECT * FROM week_EX WHERE daynite='"+daynite+"' LIMIT 1");
	}
	
	/**
	 * 測試是否為正式機
	 * TODO 轉到IO
	 * @return
	 */
	public boolean testOnlineServer(){
		try {			
			Enumeration e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();			
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				Enumeration e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = (InetAddress) e2.nextElement();
					if (ia instanceof Inet6Address)
						continue; // IPv6 address
					// System.out.print(ia.getHostAddress());
					//if (e2.hasMoreElements()) {
						// System.out.print(", ");
					//}
					if(sqlGetInt("SELECT COUNT(*)FROM  Parameter WHERE name='host' AND value LIKE'%"+ia.getHostAddress()+"%'")>0){
						return true;					
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return false;
		
	}
	
	/**
	 * 取及格標
	 * @param ClassNo
	 * @return
	 */
	public int getPassLine(String ClassNo){
		int pa;
		try{
			if(sqlGetStr("SELECT SchNo FROM Class WHERE ClassNo='"+ClassNo+"'").equals("M")){pa=70;}else{pa=60;}
		}catch(Exception e){
			pa=60;
		}			
		return pa;
	}
	
	/**
	 * 獲取訪問的ip地址
	 * 獲取請求主機IP地址,如果通過代理進來，則透過防火牆獲取真實IP地址  
	 * @param request
	 * @return
	 */
    public void saveWwpassHist(HttpServletRequest request, String userid, String action, String note) {
        
        String ip = request.getHeader("X-Forwarded-For");
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        System.out.println("INSERT INTO SYS_LOG(action,cname,note,whois)VALUES('"+request.getRequestURI()+"', '"+userid+"', '511','"+ip+"')");
        exSql("INSERT INTO SYS_LOG(action,cname,note,whois)VALUES('"+request.getRequestURI()+"', '"+userid+"', '511','"+ip+"')");
    }

}
