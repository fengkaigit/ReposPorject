package com.ey.dao.base.impl;


import com.ey.dao.base.BaseDAO;
import com.ey.dao.common.dbid.DbidGenerator;

import java.io.Serializable;  
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;  
import java.util.Map;
  
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;  
import org.hibernate.SQLQuery;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;  
   
    
public class BaseDAOImpl implements BaseDAO{  
  
    private SessionFactory sessionFactory;  
    protected final Log log = LogFactory.getLog(getClass());
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
    
    public List find(String hql, Map parameters) {  
    	Query query = this.getCurrentSession().createQuery(hql);
		setQueryParameters(query, parameters);
		return query.list();
    }
  
    public List find(String hql, Object[] param, Integer page, Integer rows) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.length > 0) {  
            for (int i = 0; i < param.length; i++) {  
                q.setParameter(i, param[i]);  
            }  
        } 
        if(page!=null&&page>0&&rows!=null&&rows>0){
        	q.setFirstResult((page - 1) * rows).setMaxResults(rows);
        }
        return q.list();  
    }  
  
    public List find(String hql, List<Object> param, Integer page, Integer rows) {  
        Query q = this.getCurrentSession().createQuery(hql);  
        if (param != null && param.size() > 0) {  
            for (int i = 0; i < param.size(); i++) {  
                q.setParameter(i, param.get(i));  
            }  
        }  
        if(page!=null&&page>0&&rows!=null&&rows>0){
        	q.setFirstResult((page - 1) * rows).setMaxResults(rows);
        }
        return q.list();  
    }  
    
    public List find(String hql,Integer page,Integer rows) {  
        Query q = this.getCurrentSession().createQuery(hql); 
        if(page!=null&&page>0&&rows!=null&&rows>0){
        	q.setFirstResult((page - 1) * rows).setMaxResults(rows);
        }
        return q.list();  
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
  public Object getDbId(Object o){
	  try {
		  BeanUtils.setProperty(o, "id", DbidGenerator.getDbidGenerator().getNextId());
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return o;
  }
  private void setQueryParameters(Query query, Map parameters) {
		if (parameters != null) {
			for (Iterator i = parameters.entrySet().iterator(); i.hasNext();) {
				Map.Entry entry = (Map.Entry) i.next();
				String paramName = (String) entry.getKey();
				Object paramValue = entry.getValue();
				if (paramValue instanceof Collection) {
					query.setParameterList(paramName, (Collection) paramValue);
				} else if (paramValue instanceof Object[]) {
					query.setParameterList(paramName, (Object[]) paramValue);
				} else {
					query.setParameter(paramName, paramValue);
				}
			}
		}
	}

	private void setQueryParameters(Query query, Object[] values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
	}
	
	protected List List(String sql,Object[] values) {
		return list(sql, null, null, values, 0, 0);
	}
	protected List List(String sql, Object[][] scalaries,Class beanCls,Object[] values) {
		return list(sql, null,scalaries,beanCls,values,0,0);
	}
	protected List List(String sql, Object[][] scalaries,Class beanCls,Object[] values,int pageNo, int pageSize) {
		return list(sql, null,scalaries,beanCls,values,(pageNo - 1) * pageSize,pageSize);
	}
	protected List List(String sql, Object[][] scalaries,Object[] values) {
		return list(sql, null, scalaries, values, 0,0);
	}
	protected List List(String sql, Object[][] scalaries,
			Object[] values, int pageNo, int pageSize) {
		return list(sql, null, scalaries, values, (pageNo - 1) * pageSize,
				pageSize);
	}
	protected List List(String sql, Object[][] entities, Object[][] scalaries,
			Object[] values, int pageNo, int pageSize) {
		return list(sql, entities, scalaries, values, (pageNo - 1) * pageSize,
				pageSize);
	}
	private List list(final String sql, final Object[][] entities,
			final Object[][] scalaries, final Object[] values,
			final int firstResult, final int maxResults) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setCacheable(false);
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				query.addEntity(entities[i][0].toString(),
						(Class) entities[i][1]);
			}
		}
		if (scalaries != null) {
			for (int i = 0; i < scalaries.length; i++) {
				query.addScalar(scalaries[i][0].toString(),
						(Type) scalaries[i][1]);
			}
		}
		setQueryParameters(query, values);
		if (firstResult > 0) {
			log.debug("firstResult:" + firstResult);
			query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			log.debug("maxResults:" + maxResults);
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
	private List list(final String sql, final Object[][] entities, final Object[][] scalaries, final Class cls, final Object[] values,
			final int firstResult, final int maxResults) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		query.setCacheable(false);
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				query.addEntity(entities[i][0].toString(),
						(Class) entities[i][1]);
			}
		}
		if (scalaries != null) {
			for (int i = 0; i < scalaries.length; i++) {
				query.addScalar(scalaries[i][0].toString(),
						(Type) scalaries[i][1]);
			}
		}
		query.setResultTransformer(Transformers.aliasToBean(cls));
		setQueryParameters(query, values);
		if (firstResult > 0) {
			log.debug("firstResult:" + firstResult);
			query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			log.debug("maxResults:" + maxResults);
			query.setMaxResults(maxResults);
		}
		return query.list();
	}
}  
