package com.ey.dao.cmd;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ey.consts.SystemConst;
import com.ey.entity.PropertyImpl;

/**
 * @author 
 */
public class AcquireDbidBlockCmd implements Command<Long> {

  private static final long serialVersionUID = 1L;

  long blocksize;

  public AcquireDbidBlockCmd(long blocksize) {
    this.blocksize = blocksize;
  }

  public Long execute(Session session) throws Exception {
    PropertyImpl property = (PropertyImpl) session.createCriteria(PropertyImpl.class)
      .add(Restrictions.eq("key", SystemConst.NEXT_DBID_KEY))
      .uniqueResult();
    long nextId = Long.parseLong(property.getValue());
    property.setValue(Long.toString(nextId + blocksize));

    session.flush();

    return nextId;
  }
}