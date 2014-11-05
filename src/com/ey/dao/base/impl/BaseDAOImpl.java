package com.ey.dao.base.impl;

import com.ey.dao.UserDAO;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.common.dbid.DbidGenerator;

import java.io.Serializable;  
import java.lang.reflect.Field;
import java.util.List;  
  
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;  
   
    
public class BaseDAOImpl implements BaseDAO{  
  
    private SessionFactory sessionFactory;  
  
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
  
    @Autowired  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
  
    public Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
  
    public Serializable save(Object o) {
    	//this.getDbId(o);
    	return this.getCurrentSession().save(o);  
    }  
  
    public void delete(Object o) {  
        this.getCurrentSession().delete(o); 
    }  
  
    public void update(Object o) {  
        this.getCurrentSession().update(o);
    }  
  
    public void saveOrUpdate(Object o) {  
        this.getCurrentSession().saveOrUpdate(o);  
    }  
    public List find(String hql) {  
        return this.getCurrentSession().createQuery(hql).list();  
    }  
  
    public List find(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.list();  
    }  
  
    public List find(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.list();  
    }  
  
    public List find(String hql, Object[] param, Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }  
  
    public List find(String hql, List<Object> param, Integer page, Integer rows) {  
        if (page == null || page < 1) {  
            page = 1;  
        }  
        if (rows == null || rows < 1) {  
            rows = 10;  
        }  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();  
    }  
  
    public Object get(Class c, Serializable id) {  
        return this.getCurrentSession().get(c, id);  
    }  
  
    public Object get(String hql, Object[] param) {  
        List l = this.find(hql, param);  
        if (l != null && l.size() > 0) {  
            return l.get(0);  
        } else {  
            return null;  
        }  
    }  
  
    public Object get(String hql, List<Object> param) {  
        List l = this.find(hql, param);  
        if (l != null && l.size() > 0) {  
            return l.get(0);  
        } else {  
            return null;  
        }  
    }  
  
    public Long count(String hql) {  
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();  
    }  
  
    public Long count(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return (Long) q.uniqueResult();  
    }  
  
    public Long count(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return (Long) q.uniqueResult();  
    }  
  
    public Integer executeHql(String hql) {  
        return this.getCurrentSession().createQuery(hql).executeUpdate();  
    }  
  
    public Integer executeHql(String hql, Object[] param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        }  
        return q.executeUpdate();  
    }  
  
    public Integer executeHql(String hql, List<Object> param) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        return q.executeUpdate();  
    }  
  private void getDbId(Object o){
	  try {
		  BeanUtils.setProperty(o, "id", DbidGenerator.getDbidGenerator().getNextId());
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
}  
