package dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author JOHN
 */
public class JdbcDAO extends JdbcDaoSupport {

	/**
	 * 通用查詢
	 * @param sql 標準SQL
	 * @return SQL查詢結果
	 */
	public List StandardSqlQuery(String sql) {
		return getJdbcTemplate().queryForList(sql);		
	}
	
	/**
	 * 通用執行
	 * @param sql 標準SQL
	 */
	public void exQuery(String sql){		
		getJdbcTemplate().execute(sql);
	}
	
	/**
	 * 通用查詢數值
	 * @param sql 一句標準SQL
	 * @return 1個數值
	 */
	public Integer sqlGetInt(String sql){		
		return getJdbcTemplate().queryForInt(sql);
	}	
	
	/**
	 * 通用查詢1筆資料
	 * @param sql
	 * @return 1筆map型態的資料
	 */
	public Map sqlGetMap(String sql){		
		return getJdbcTemplate().queryForMap(sql+" LIMIT 1");
	}
	
	/**
	 * 通用查詢1個字串
	 * @param sql
	 * @return java.lang.String
	 */
	public String sqlGetString(String sql){
		return (String)getJdbcTemplate().queryForObject(sql+" LIMIT 1", java.lang.String.class);
	}
}
