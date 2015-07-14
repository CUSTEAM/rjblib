package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * C3P0
 * @author JOHN
 */
public class HibernateDAO extends HibernateDaoSupport {
	
    protected final Log log = LogFactory.getLog(getClass());
    
    /**
     * @see com.neoasia.dao.DAO#saveObject(java.lang.Object)
     */
    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
        getHibernateTemplate().flush();
    }

    /**
     * @see com.neoasia.dao.DAO#getObject(java.lang.Class, java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }
        return o;
    }

    /**
     * @see com.neoasia.dao.DAO#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     * @see com.neoasia.dao.DAO#removeObject(java.lang.Class, java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }
    
    public void removeObject(Serializable po) {
        getHibernateTemplate().delete(po);
    }
    
    public void reload(Serializable po) {
    	getHibernateTemplate().refresh(po);
    }
    
	public List submitQuery(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	
	
	
}
