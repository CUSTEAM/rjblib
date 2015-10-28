package service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import service.impl.base.BaseAccessImpl;

/**
 * 資料更新高階方法
 * @author John
 *
 */
public class DataUpdater extends BaseAccessImpl{
	
	public void Week_ADD(Map week){
		
		
	}
	
	public void Week_DELETE(String daynite){
		
	}
	
	public void Week_UPDATE_wdate(String wdate, String daynite){
		exSql("UPDATE week_EX SET wdate='"+wdate+"' WHERE daynite='"+daynite+"'");
		
	}
	
	/**
	 * 動作記錄
	 * @param action 程式名稱
	 * @param user 使用者
	 * @param note 註記
	 */
	public void saveLog(String action, String user, String note){
		
		exSql("INSERT INTO SYS_LOG(action,cname,note)VALUES('"+action+"','"+user+"','"+note+"')");
	}
	
	public void updateUnit(ServletContext servletContext){
		
		System.out.println("載入各單位為List of Map型態");
		List<Map>allUnit=sqlGet("SELECT id,name FROM CODE_CAMPUS");
		List<Map>tmp;		
		for(int i=0; i<allUnit.size(); i++){
			tmp=sqlGet("SELECT id,name FROM CODE_UNIT WHERE pid='0' AND campus='"+allUnit.get(i).get("id")+"'");			
			for(int j=0; j<tmp.size(); j++){				
				tmp.get(j).put("sub_unit", sqlGet("SELECT id,name FROM CODE_UNIT WHERE pid='"+tmp.get(j).get("id")+"'"));
			}			
			allUnit.get(i).put("unit", tmp);
		}		
		System.out.println("載入單位(List)allUnit="+allUnit.size());
		servletContext.setAttribute("allUnit", allUnit);
		System.out.println("--------------------");
	}
	
	public void saveUserLog(){
		
	}

}
