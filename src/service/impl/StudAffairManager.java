package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.impl.base.BaseAccessImpl;
import sun.rmi.runtime.Log;
import sun.security.util.Debug;
import model.Message;

/**
 * 帳號相關應用
 * @author John
 *
 */
public class StudAffairManager{
	
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
	 * 依日期查詢缺課人數
	 * @param Dtime_oid
	 * @param date
	 * @return
	 */
	public List Dilg_due_count(String Dtime_oid, String date){		
		return dataFinder.sqlGet("SELECT cls, COUNT(*)FROM Dilg WHERE date='"+date+"' AND Dtime_oid="+Dtime_oid+" GROUP BY cls");
	}
	
	/**
	 * 依課程查詢選課人數
	 * @param Dtime_oid
	 * @return
	 */
	public int Seld_count(String Dtime_oid){
		return dataFinder.sqlGetInt("SELECT COUNT(*)FROM Seld WHERE status IS NULL AND Dtime_oid="+Dtime_oid);
	}
	
	/**
	 * 依日期查詢當日應到人數
	 * @param Dtime_oid
	 * @param date
	 * @return
	 */
	public int DilgLog_date_due(String Dtime_oid, String date){		
		return dataFinder.sqlGetInt("SELECT due FROM DilgLog WHERE date='"+date+"' AND Dtime_oid="+Dtime_oid);
	}
	
	/**
	 * 依日期查詢課程各節次出勤狀態
	 * @param Dtime_oid
	 * @param date
	 * @return
	 */
	public List Dilg_info(String Dtime_oid, String date){
		return dataFinder.sqlGet("SELECT cls, COUNT(*) as cnt FROM Dilg WHERE date='"+date+"' AND Dtime_oid="+Dtime_oid+" GROUP BY cls");
	}
	
	/**
	 * 是否為一門課的任課教師
	 * @return
	 */
	public boolean Dtime_techid(String Dtime_oid, String techid){
		if(dataFinder.sqlGetInt("SELECT COUNT(*)FROM Dtime WHERE techid='"+techid+"' AND Oid="+Dtime_oid)>0){			
			return true;
		}
		return false;
	}
	
	/**
	 * 取授課學生 - 單一科目學生
	 * @param Oid
	 * @param date
	 * @param week
	 * @return
	 */
	public List Dilg_student_date(String Oid, String date, String week){		
		
		List list=dataFinder.sqlGet("SELECT j.total_score, s.status, st.student_no, st.student_name,(SELECT COUNT(*)FROM Dilg di, Dilg_rules dr WHERE di.abs=dr.id AND " +
		"dr.exam='1'AND di.student_no=st.student_no AND di.Dtime_oid=d.Oid) as dilg_period, s.score1, s.score2, s.score FROM " +
		"Dtime d, stmd st LEFT OUTER JOIN Just j ON st.student_no=j.student_no, Seld s WHERE st.student_no=s.student_no AND s.Dtime_oid=d.Oid AND d.Oid="+Oid+" ORDER BY st.student_no");		
		Object dc[]=Dtime_class(Oid, week);		
		List dg;
		Map m;		
		for(int i=0; i<list.size(); i++){
			dg=new ArrayList();
			for(int j=0; j<dc.length; j++){
				m=new HashMap();
				m.put("cls", dc[j]);
				try{
					m.put("abs", dataFinder.sqlGetStr("SELECT abs FROM Dilg WHERE student_no='"+((Map)list.get(i)).get("student_no")+"' AND cls='"+dc[j]+"' AND date='"+date+"'"));
				}catch(Exception e){
					m.put("abs", "");
				}				
				dg.add(m);
			}			
			((Map)list.get(i)).put("dilgs", dg);			
		}		
		return list;
	}
	
	/**
	 * 取授課學生 - 一科目多教師
	 * @param Oid
	 * @param date
	 * @param week
	 * @param id
	 * @return
	 */
	public List Dilg_student_date_mulTeacher(String Oid, String date, String week, String id){	
		
		List<Map>list=dataFinder.sqlGet("SELECT j.total_score, s.status, st.student_no, st.student_name,(SELECT COUNT(*)FROM Dilg di, Dilg_rules dr WHERE di.abs=dr.id AND " +
		"dr.exam='1'AND di.student_no=st.student_no AND di.Dtime_oid=d.Oid) as dilg_period, s.score1, s.score2, s.score FROM " +
		"Dtime d, stmd st LEFT OUTER JOIN Just j ON st.student_no=j.student_no, Seld s WHERE st.student_no=s.student_no AND " +
		"s.Dtime_oid=d.Oid AND d.Oid="+Oid+" AND s.Dtime_teacher=(SELECT Oid FROM Dtime_teacher WHERE " +
		"Dtime_oid="+Oid+" AND teach_id='"+id+"')ORDER BY st.student_no");	
		
		Object dc[]=Dtime_class(Oid, week);		
		List dg;
		Map m;		
		for(int i=0; i<list.size(); i++){
			dg=new ArrayList();
			for(int j=0; j<dc.length; j++){
				m=new HashMap();
				m.put("cls", dc[j]);
				try{
					m.put("abs", dataFinder.sqlGetStr("SELECT abs FROM Dilg WHERE student_no='"+list.get(i).get("student_no")+"' AND cls='"+dc[j]+"' AND date='"+date+"'"));
				}catch(Exception e){
					m.put("abs", "");
				}				
				dg.add(m);
			}			
			list.get(i).put("dilgs", dg);			
		}		
		return list;
	}
	
	public Object[] Dtime_class(String Dtime_oid, String week){
		List<Map>list=dataFinder.sqlGet("SELECT begin, end FROM Dtime_class WHERE week='"+week+"' AND Dtime_oid="+Dtime_oid);
		
		int begin, end;
		ArrayList str = new ArrayList();
		
		for(int i=0; i<list.size(); i++){
			begin=Integer.parseInt(list.get(i).get("begin").toString());
			end=Integer.parseInt(list.get(i).get("end").toString());
			for(int j=begin; j<=end; j++){
				str.add(j);
			}
		}		
		return str.toArray();
	}
	
	/**
	 * 出席率圖表
	 * @param techid
	 * @param begin
	 * @param end
	 * @param term
	 * @return
	 */
	public Map Dilg_pro_techid(String techid, Date begin, String term){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
		Calendar c=Calendar.getInstance();
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c.setTime(begin);
		c1.setTime(begin);
		
		List<Map>list=dataFinder.sqlGet("SELECT ((SELeCT COUNT(*)FROM stmd, Seld WHERE stmd.student_no=Seld.student_no AND Seld.Dtime_oid=d.Oid)*d.thour) as cnt, d.Oid, cl.ClassName, cs.chi_name FROM Dtime d, " +
		"Csno cs, Class cl WHERE d.cscode=cs.cscode AND d.depart_class=cl.ClassNo AND d.techid='"+techid+"' AND d.Sterm='"+term+"'");		
		
		
		float cnt;
		float wdilg;
		
		StringBuilder sb=new StringBuilder("['週次',");
		for(int i=0; i<list.size(); i++){
			sb.append("'"+((Map)list.get(i)).get("ClassName")+"-"+((Map)list.get(i)).get("chi_name")+"',");
		}
		sb.delete(sb.length()-1, sb.length());				
		sb.append("],");		
		Map map=new HashMap();		
		map.put("dtimes", sb);		
		String sba[]=new String[19];
		
		
		for(int i=1; i<19; i++){
			c1.add(Calendar.DAY_OF_YEAR, 7);
			c2.setTime(c1.getTime());
			c2.add(Calendar.DAY_OF_YEAR, -1);
			sb=new StringBuilder("['第"+i+"週',");			
			for(int k=0; k<list.size(); k++){
				try{
					cnt=Float.parseFloat(list.get(k).get("cnt").toString());					
					wdilg=Float.parseFloat(dataFinder.sqlGetStr("SELECT COUNT(*)FROM Dilg WHERE date>='" +
					sf.format(c.getTime())+"'AND date<='"+sf.format(c2.getTime())+"' AND Dtime_oid="+list.get(k).get("Oid")));					
					sb.append(100-((wdilg/cnt)*100)+",");	
				}catch(Exception e){
					sb.append(100+",");	
				}							
			}
			
			sb.delete(sb.length()-1, sb.length());				
			sb.append("]");
			//System.out.println(sb);
			if(i<18){sb.append(",");}
			sba[i]=sb.toString();
			map.put("dilgs", sba);		
			
			c.add(Calendar.DAY_OF_YEAR, 7);
			
		}	
		return map;
	}
	
	/**
	 * 取得1位學生的各種缺課
	 * @param student_no
	 * @return
	 */
	public Map StudentDilg(String student_no){
		return dataFinder.sqlGetMap("SELECT (SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='1' AND " +
				"Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs1, (SELECT COUNT(*)FROM Dilg LEFT OUTER JOIN Dilg_apply ON Dilg.Dilg_app_oid=Dilg_apply.Oid " +
				"WHERE Dilg.abs!='5' AND Dilg.abs='2' AND(Dilg_apply.result='2'||Dilg_apply.result IS NULL) AND Dilg.student_no=s.student_no)as abs2, " +
				"(SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='3' AND Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs3, " +
				"(SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='4' AND Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs4, " +
				"(SELECT COUNT(*)FROM Dilg WHERE Dilg.abs='5' AND Dilg.student_no=s.student_no)as abs5, (SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='6' " +
				"AND Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs6, (SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='7' AND " +
				"Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs7, (SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='8' AND " +
				"Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs8, (SELECT COUNT(*)FROM Dilg, Dilg_apply WHERE Dilg_apply.result='1' AND Dilg.abs='9' AND " +
				"Dilg.Dilg_app_oid=Dilg_apply.Oid AND Dilg.student_no=s.student_no)as abs9 FROM stmd s WHERE s.student_no='"+student_no+"'");
	}
	
	/**
	 * 學生課表資訊
	 * @param stdNo
	 * @param term
	 * @return
	 */
	public List getCsTable(String stdNo, String term){		
		return dataFinder.sqlGet("SELECT d.Oid as dOid, d.thour, d.credit, " +
		"d.Oid as dtOid, d.techid, e.cname, c.cscode, c.chi_name,dc.* FROM stmd st, " +
		"Seld s, (Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno), Dtime_class dc, " +
		"Csno c WHERE st.student_no=s.student_no AND s.Dtime_oid=d.Oid AND " +
		"c.cscode=d.cscode AND d.Oid=dc.Dtime_oid AND d.Sterm='"+term+"' AND " +
		"s.student_no='"+stdNo+"' ORDER BY CAST(dc.begin AS UNSIGNED)");
	}
	
	/**
	 * 取學期開始
	 * @return
	 */
	public String school_term_begin(){
		return dataFinder.sqlGetStr("SELECT cdate FROM SYS_CALENDAR WHERE s_group='SchoolTerm' AND s_name='begin'");
	}
	
	/**
	 * 取學期結束
	 * @return
	 */
	public String school_term_end(){
		return dataFinder.sqlGetStr("SELECT cdate FROM SYS_CALENDAR WHERE s_group='SchoolTerm' AND s_name='end'");
	}
	
	/**
	 * 取點名開始
	 * @return
	 */
	public String RollCall_begin(){
		return dataFinder.sqlGetStr("SELECT cdate FROM SYS_CALENDAR WHERE s_group='RollCall' AND s_name='begin'");
	}
	
	/**
	 * 取點名結束
	 * @return
	 */
	public String RollCall_end(){
		return dataFinder.sqlGetStr("SELECT cdate FROM SYS_CALENDAR WHERE s_group='RollCall' AND s_name='end'");
	}
	
	public List dilguneed(String begin, String end){
		return dataFinder.sqlGet("SELECT date FROM AMS_Holiday WHERE date>='"+begin+"' AND date<='"+end+"'");
	}
	
	/**
	 * 現在學期
	 * @return
	 */
	public String school_term(){
		return dataFinder.sqlGetStr("SELECT Value FROM Parameter WHERE Name='School_term'");
	}
	
	/**
	 * 現在學年
	 * @return
	 */
	public String school_year(){
		return dataFinder.sqlGetStr("SELECT Value FROM Parameter WHERE Name='School_year'");
	}
	
	public boolean Dilg_uneed(List<Map>dilguneed, String date){		
		//不䵞名日期
		for(int j=0; j<dilguneed.size(); j++){
			if(date.equals(     dilguneed.get(j).get("date").toString()    )){	
				return false;
			}
		}		
		return true;
	}
	
	/**
	 * 學生各科目缺課資訊
	 * @param student_no
	 * @param absType
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List getCsDilgDetail(String student_no, String absType, String beginDate, String endDate){
		
		StringBuilder sb=new StringBuilder("SELECT date FROM Dilg WHERE abs LIKE'"+absType+"%' AND student_no='"+student_no+"'");
		
		if(!beginDate.equals("")){sb.append("AND date>='"+beginDate+"'");}		
		if(!endDate.equals("")){sb.append("AND date<='"+endDate+"'");}		
		
		sb.append("GROUP BY date DESC");
		
		List<Map>list=getDataFinder().sqlGet(sb.toString());
		
		for(int i=0; i<list.size(); i++){			
			list.get(i).put("dilgs", 
			getDataFinder().sqlGet("SELECT e.cname, da.result, s.student_name, dr.name, dg.*, c.chi_name FROM " +
			"(Dilg dg LEFT OUTER JOIN Dilg_apply da ON dg.Dilg_app_oid=da.Oid)LEFT OUTER JOIN empl e ON e.idno=da.auditor, Dtime d, Csno c, stmd s," +
			"Dilg_rules dr WHERE s.student_no=dg.student_no AND dr.id=dg.abs AND c.cscode=d.cscode AND " +
			"d.Oid=dg.Dtime_oid AND dg.abs LIKE'"+absType+"%' AND dg.student_no='"+
			student_no+"' AND dg.date='"+list.get(i).get("date")+"'"));
		}
		
		return list;
		
	}
	
	public List getDilgDetail(String student_no, String term){		
		/*list=getDataFinder().sqlGet("SELECT e.cname, d.Oid, c.chi_name, d.opt, d.credit, d.thour, " +
		"(SELECT COUNT(*)FROM Dilg WHERE Dilg.student_no='"+student_no+"' " +
		"AND Dilg.Dtime_oid=d.Oid AND abs NOT IN(SELECT id FROM Dilg_rules WHERE exam='0'))as dilg_period FROM " +
		"Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Csno c, Seld s WHERE d.cscode=c.cscode AND " +
		"s.Dtime_oid=d.Oid AND s.student_no='"+student_no+"' AND " +
		"d.Sterm='"+term+"'");*/ 		
		List<Map>list=getDataFinder().sqlGet("SELECT e.cname, d.Oid, c.chi_name, d.opt, d.credit, d.thour, s.status, IFNULL(s.elearn_dilg, 0)as elearn_dilg," +
		"(SELECT COUNT(*)FROM Dilg WHERE Dilg.student_no='"+student_no+"' AND Dilg.Dtime_oid=d.Oid AND abs NOT IN" +
		"(SELECT id FROM Dilg_rules WHERE exam='0'))as dilg_period FROM Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno, Csno c, Seld s WHERE " +
		"d.cscode=c.cscode AND s.Dtime_oid=d.Oid AND s.student_no='"+student_no+"' AND d.Sterm='"+term+"'");	
				
		for(int i=0; i<list.size(); i++){
			list.get(i).put("dilgs", getDataFinder().sqlGet("SELECT da.result, d.*, dr.name FROM Dilg d LEFT OUTER JOIN Dilg_apply da ON d.Dilg_app_oid=da.Oid, Dilg_rules dr WHERE d.student_no='"+
			student_no+"'AND dr.id=d.abs AND d.Dtime_oid="+list.get(i).get("Oid")));			
		}
		return list;
	}
	
	/**
	 * 取扣考
	 * @param student_no
	 * @return
	 */
	public List getFailStd(String student_no){		
		return getDataFinder().sqlGet("SELECT c.chi_name FROM Seld s,Dtime d,Csno c WHERE s.status='1' AND " +
		"s.Dtime_oid=d.Oid AND c.cscode=d.cscode AND s.student_no='"+student_no+"'");
	}
	
	/**
	 * 取歷次修改
	 * @param student_no
	 * @return
	 */
	public List getDilgRecord(String student_no){		
		return getDataFinder().sqlGet("SELECT d.*,e.cname FROM Dilg_hist d LEFT OUTER JOIN empl e ON e.idno=d.editor WHERE d.student_no='"+student_no+"'");
	}
	
	/**
	 * 取學生本學期初節與末節
	 * @param student_no
	 * @return
	 */
	public Map getEndAtStart(String student_no){
		Map map=new HashMap();
		List<Map>list=getDataFinder().sqlGet("SELECT cls FROM Dilg WHERE " +
		"student_no='"+student_no+"' GROUP BY cls ORDER BY CAST(cls AS DECIMAL(2,0))");
		
		if(list.size()<1){
			map.put("begin", 1);
			map.put("end", 14);
			return map;
		}else{
			map.put("begin", list.get(0).get("cls"));
			map.put("end", list.get(list.size()-1).get("cls"));
			return map;
		}
		
	}
	
	/**
	 * 保留備份資料
	 * @param Oid
	 * @param act
	 */
	public void saveRecord(String Oid, String userid, String act){		
		Map dilg=getDataFinder().sqlGetMap("SELECT d.student_no,dr.name as dilgName,c.chi_name,d.date,d.cls FROM Dilg d,Dtime dt, Csno c,Dilg_rules dr " +
		"WHERE dr.id=d.abs AND d.Dtime_oid=dt.Oid AND c.cscode=dt.cscode AND d.Oid="+Oid);
		if(dilg!=null)
		getDataFinder().exSql("INSERT INTO Dilg_hist(student_no,editor,comment)VALUES('"+dilg.get("student_no")+"','"+
		userid+"','"+dilg.get("date")+"第"+dilg.get("cls")+"節"+dilg.get("dilgName")+":"+act+"');");
	}

}
