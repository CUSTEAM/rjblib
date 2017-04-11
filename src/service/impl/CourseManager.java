package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import service.impl.base.BaseAccessImpl;

public class CourseManager extends BaseAccessImpl{
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
	 * 班級開課資訊
	 */
	public List getStmdBy(String departClass) {
		String sql="SELECT student_no FROM stmd WHERE depart_class LIKE '"+departClass+"%'";
		return dataFinder.sqlGet(sql);
	}
	
	/**
	 * 學號選課資訊
	 */
	public List getSeldBy(String studentNo, String sterm) {
				
		String sql="SELECT s.Dtime_Oid, d.cscode, cs1.ClassName, c.chi_name, s.student_no, cs2.className as className2,st.student_name, ds.week, ds.begin, ds.end, d.thour "+
		"FROM Seld s, Dtime_class ds, Dtime d, Csno c, Class cs1, Class cs2, stmd st "+
		"WHERE s.Dtime_oid=d.Oid AND ds.Dtime_oid=s.Dtime_oid AND s.student_no='"+studentNo+"' AND sterm='"+sterm+"' AND "+
		 "c.cscode=d.cscode AND d.depart_class=cs1.ClassNo AND st.depart_class=cs2.ClassNo AND st.student_no=s.student_no ORDER BY st.student_no";
		List list=dataFinder.sqlGet(sql);		
		
		// 2007/12/17 追加 getOtherSeld
		List otherSeld=getOtherSeld(studentNo, sterm);
		if(otherSeld.size()>0){
			list.addAll(otherSeld);
		}
		
		return list;
	}
	
	/**
	 * 取得不產生選課, 但是要查衝堂的課 5000, T0001
	 */
	public List getOtherSeld(String studentNo, String sterm){
		String departClass=sqlGetStr("SELECT depart_class FROM stmd WHERE student_no='"+studentNo+"'");
		String sql="SELECT d.Oid as Dtime_Oid, d.cscode, cs1.ClassName, c.chi_name, st.student_no, " +
		"cs2.className as className2, st.student_name, ds.week, ds.begin, " +
		"ds.end, d.thour FROM Dtime_class ds, Dtime d, Csno c, Class cs1, Class cs2, stmd st WHERE " +
		"st.student_no='"+studentNo+"' "+
		"AND d.depart_class='"+departClass+"' "+
		"AND d.sterm='"+sterm+"' "+
		// 目前2門
		"AND d.cscode IN('50000', 'T0001' )" +
		"ANd c.cscode=d.cscode AND d.depart_class=cs1.ClassNo AND st.depart_class=cs2.ClassNo AND ds.dtime_oid=d.Oid";		
		
		return dataFinder.sqlGet(sql);
	}
	
	/**
	 * 學生衝堂檢查
	 * @return 衝堂明細清單
	 */
	public List checkReSelds(String studentNo, String DtimeOid, String week, String begin, String end, String sterm){		
		
		
		return dataFinder.sqlGet("SELECT s.Oid, s.Dtime_Oid, d.cscode, cs1.ClassName, c.chi_name, cs2.className as className2, ds.week, ds.begin, ds.end, d.thour "+
				"FROM Seld s, Dtime_class ds, Dtime d, Csno c, Class cs1, Class cs2, stmd st "+
				"WHERE s.Dtime_oid=d.Oid AND ds.Dtime_oid=s.Dtime_oid AND s.student_no='"+studentNo+"' AND sterm='"+sterm+"' AND "+
				"c.cscode=d.cscode AND d.depart_class=cs1.ClassNo AND st.depart_class=cs2.ClassNo AND st.student_no=s.student_no AND " +
				"d.Oid<>"+DtimeOid+" AND ds.week='"+week+"' AND (ds.begin <="+end+" AND ds.end >="+begin+"" +")");
	}
	
	/**
	 * 檢查同時段重複選課 2007/12/17 不產生基本選課科目檢查
	 */
	public List checkReSeld(String departClass, String sterm) {
		Map map;
		// 錯誤清單
		List checkReSelds=new ArrayList();
		// 所有管轄範圍內的學生
		Object stmds[]=getStmdBy(departClass).toArray();
		for(int i=0; i<stmds.length; i++){			
			try{
				Object seld[]=getSeldBy(((Map)stmds[i]).get("student_no").toString(), sterm).toArray();				
				// 以學生選課的數目為比較次數的基本值
				for(int j=0; j<seld.length; j++){
					Object reSeld[]=checkReSelds(((Map)seld[j]).get("student_no").toString(),
												((Map)seld[j]).get("Dtime_Oid").toString(),
												((Map)seld[j]).get("week").toString(),
												((Map)seld[j]).get("begin").toString(),
												((Map)seld[j]).get("end").toString(), sterm).toArray();
					// 若查詢結果長度>0
					if(reSeld.length>0){
						// 將比對標的裝入錯誤清單
						for(int k=0; k<reSeld.length; k++){
							map = new HashMap();
							map.put("cscode",((Map)seld[j]).get("cscode"));
							map.put("chi_name",((Map)seld[j]).get("chi_name"));
							map.put("ClassName",((Map)seld[j]).get("ClassName"));
							map.put("chi_name",((Map)seld[j]).get("chi_name"));
							map.put("student_no",((Map)seld[j]).get("student_no"));
							map.put("className2",((Map)seld[j]).get("className2"));
							map.put("student_name",((Map)seld[j]).get("student_name"));
							map.put("week",((Map)seld[j]).get("week").toString());
							map.put("begin",((Map)seld[j]).get("begin").toString());
							map.put("end", ((Map)seld[j]).get("end").toString());
							map.put("box", "<img src='images/16-cube-bug.png'>");
							// 將比對對象裝入錯誤清單
							map.put("cscode2",((Map)reSeld[k]).get("cscode"));
							map.put("chi_name2",((Map)reSeld[k]).get("chi_name"));
							map.put("ClassName2",((Map)reSeld[k]).get("ClassName"));
							map.put("chi_name2",((Map)reSeld[k]).get("chi_name"));
							map.put("className22",((Map)reSeld[k]).get("className2"));
							map.put("week2",((Map)reSeld[k]).get("week").toString());
							map.put("begin2",((Map)reSeld[k]).get("begin").toString());
							map.put("end2", ((Map)reSeld[k]).get("end").toString());
							map.put("Oid", ((Map)reSeld[k]).get("Oid").toString());
							
							checkReSelds.add(map);
						}
					}
				}

			}catch(DataAccessException e){
				e.printStackTrace();
				return checkReSelds;
			}
		}
		return checkReSelds;
	}
	
	/**
	 * 尋找衝堂公用程式
	 * TODO 移至rjblib
	 * @param info 排入的節次
	 * @param cls  已存的節次
	 * @param title  名稱
	 * @return 衝突的節次
	 */
	public List checkClass(int week, int begin, int end, List<Map>cls){
		List result=new ArrayList();
		for(int i=0; i<cls.size(); i++){			
			if(week!=Integer.parseInt(cls.get(i).get("week").toString())){//不同星期排除
				continue;
			}			
			if(begin<=Integer.parseInt(cls.get(i).get("end").toString())&& end>=Integer.parseInt(cls.get(i).get("begin").toString())){					
			result.add(cls.get(i));				
			}
		}		
		return result;
	}
}
