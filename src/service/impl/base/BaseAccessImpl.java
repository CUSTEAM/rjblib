package service.impl.base;

import java.util.List;
import java.util.Map;

import dao.HibernateDAO;
import dao.JdbcDAO;
//import service.DefaultManager;

/**
 * 存取資料基本實現
 * @author JOHN
 */
public class BaseAccessImpl {

	private JdbcDAO jdbcDao;

	private HibernateDAO hibernateDAO;	

	public JdbcDAO getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDAO jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	public HibernateDAO getHibernateDAO() {
		return hibernateDAO;
	}

	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	/**
	 * 主要連線 C3P0 SQL得 整個查詢列表
	 * 
	 * @param sql
	 * @return List of Map
	 */
	public List sqlGet(String sql) {
		return jdbcDao.StandardSqlQuery(sql);
	}

	/**
	 * C3P0 SQL執行
	 */
	public void exSql(String sql) {
		jdbcDao.exQuery(sql);
	}

	/**
	 * C3P0 SQL得 1個數字
	 */
	public Integer sqlGetInt(String sql) {
		return jdbcDao.sqlGetInt(sql);
	}

	/**
	 * C3P0 SQL得 1筆記錄
	 */
	public Map sqlGetMap(String sql) {
		try {
			return jdbcDao.sqlGetMap(sql);
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public String sqlGetStr(String sql) {
		return jdbcDao.sqlGetString(sql);
	}

	/**
	 * C3P0 HQL得 1個列表
	 */
	public List hqlGetListBy(String hql) {
		return hibernateDAO.submitQuery(hql);
	}

	/**
	 * C3P0 更新 1個po
	 */
	public void update(Object po) {
		hibernateDAO.saveObject(po);
	}
}
