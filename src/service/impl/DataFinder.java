package service.impl;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

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

}
