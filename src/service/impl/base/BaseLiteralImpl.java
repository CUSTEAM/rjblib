package service.impl.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseLiteralImpl {
	
	/**
	 * 電子郵件格式驗證
	 * @param address
	 * @return
	 */
	public boolean validateEmail(String address){		
		String regex="[a-zA-Z][\\w_]+@\\w+(\\.\\w+)+";  
		Pattern p=Pattern.compile(regex);  
		Matcher m=p.matcher(address);		
		return m.matches();   
	}
	
	/**
	 * 轉換day of week中星期日為0的問題
	 * @param w
	 * @return
	 */
	public int paresJavaDayOfWeek(int w){
		if(w==0){
			return 7;
		}else{
			return w;
		}
	}
	
	/**
	 * 星期轉換
	 * @param w
	 * @param front 選擇性調整前置「星期」、「週」或者不指定
	 * @return
	 */
	public String getWeekOfDay(int w, String front){
		if(front==null)front="";
		switch (w){
			case 1: return front+"日";
			case 2: return front+"一";
			case 3: return front+"二";
			case 4: return front+"三";
			case 5: return front+"四";
			case 6: return front+"五";
			case 7: return front+"六";		
			default: return front+"囧";
		}
	}
	
	/**
	 * 星期轉換
	 * @param w
	 * @param front 選擇性調整前置「星期」、「週」或者不指定
	 * @return
	 */
	public String getWeekOfDay4Zh(int w, String front){
		if(front==null)front="";
		switch (w){
			case 7: return front+"日";
			case 1: return front+"一";
			case 2: return front+"二";
			case 3: return front+"三";
			case 4: return front+"四";
			case 5: return front+"五";
			case 6: return front+"六";		
			default: return front+"囧";
		}
	}
	
	/**
	 * 轉換為XML採用的符號
	 * @param str
	 * @return
	 */
	public String replaceXMLSymbol(String str){
		try{
			str=str.replaceAll("&", "&amp;");
			str=str.replaceAll("<", "&lt;");
			str=str.replaceAll(">", "&gt;");
			str=str.replaceAll("\"", "&quot;");
			str=str.replaceAll("", "\\n");
			//str=str.replaceAll(",", "&cedil;");
			str=str.replaceAll("'", "&apos;");
		}catch(NullPointerException e){
			return str;
		}
		
		return str;
	}
	
	/**
	 * 長字串按符號分析為字串
	 * @param members
	 * @return
	 */
	public String analyse(String longStr){
		//String members1=new String(longStr);
		longStr=longStr.replaceAll("[a-zA-Z\\s\\p{Punct}‘’]*", "");//正規表示法替換字母及標點
		longStr=longStr.replaceAll("、", "");//刪除, 標點(正規表示不支援中文標點)
		longStr=longStr.replaceAll("，", "");//刪除, 標點
		longStr=longStr.replaceAll("；", "");//刪除, 標點		
		return  longStr;
	}
	
	/**
	 * 轉換生日為數字
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public String getBirthday(String str) throws ParseException{		
		if(str.length()==6){
			str="00"+str;
		}else{
			str="0"+str;
		}		
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
		Calendar c=Calendar.getInstance();
		Date date=sf.parse(str);
		c.setTime(date);
		c.add(Calendar.YEAR, 1911);
		
		sf=new SimpleDateFormat("yyyy-MM-dd");		
		return sf.format(c.getTime());
	}
	
	/**
	 * 將生日yyyy-MM-dd轉為民國yyMMdd
	 * @param str
	 * @return
	 * @throws ParseException 
	 */
	public String getBirthdayNum(String str) throws ParseException{
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(sf.parse(str));
		c.add(Calendar.YEAR, -1911);
		sf=new SimpleDateFormat("yyyyMMdd");		
		return String.valueOf(Integer.parseInt(sf.format(c.getTime())));
		
	}
}
