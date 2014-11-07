/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.ey.dao.cmd;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.ey.consts.SystemConst;
import com.ey.dao.entity.PropertyImpl;


/**
 * @author 
 */
public class InitializePropertiesCmd implements Command<Long> {

  private static final long serialVersionUID = 1L;
  
  long blocksize;
  
  public InitializePropertiesCmd(long blocksize) {
    this.blocksize = blocksize;
  }

  public Long execute(Session session) throws Exception {
    
    Long nextId = initializeNextId(session);
    //initializeSchemaVersion(session);
    
    session.flush();

    return nextId;
  }

  void initializeSchemaVersion(Session session) {
    PropertyImpl property = (PropertyImpl) session.createQuery(
        "select property " +
        "from "+PropertyImpl.class.getName()+" as property " +
        "where property.key = '"+SystemConst.DB_VERSION_KEY+"'"
    ).uniqueResult();
    if (property==null) {
      //log.info("version of jbpm db schema: none");
      
    } else {
      String dbSchemaVersion = property.getValue();
      //log.debug("version of jbpm db schema: "+dbSchemaVersion);
      //if (!ProcessEngineImpl.JBPM_LIBRARY_VERSION.equals(dbSchemaVersion)) {
        //throw new JbpmException("jBPM runtime version "+ProcessEngineImpl.JBPM_LIBRARY_VERSION+" doesn't match with DB schema, which is version ");
      //}
    }
  }

  private Long initializeNextId(Session session) {
    PropertyImpl property = (PropertyImpl) session.createQuery(
        "select property " +
        "from "+PropertyImpl.class.getName()+" as property " +
        "where property.key = '"+SystemConst.NEXT_DBID_KEY+"'"
    ).uniqueResult();
    
    Long nextId;
    if (property==null) {
      nextId = getMaxDbid(session);
      nextId++;
      property = new PropertyImpl(SystemConst.NEXT_DBID_KEY, Long.toString(nextId+blocksize));
      session.save(property);
      
    } else {
      String nextIdText = property.getValue();
      nextId = new Long(nextIdText);
      property.setValue(Long.toString(nextId.longValue()+blocksize));
      session.update(property);
    }
    
    return nextId;
  }

  private Long getMaxDbid(Session session) {
    Long nextId;
    nextId = 0L;
    
    List<String> persistedTypes = new ArrayList<String>();
    //persistedTypes.add(DeploymentImpl.class.getName());
    
    
    for (String persistedType: persistedTypes) {
      try {
        Long maxDbid = (Long) session.createQuery(
                "select max(object.id) " +
                "from "+persistedType+" as object"
            ).uniqueResult();
        
        if ( (maxDbid!=null)
             && (maxDbid.longValue()>nextId) 
           ) {
          nextId = maxDbid.longValue();
        }
      } catch (Exception e) {
        //log.info("couldn't get max dbid for "+persistedType);
      }
    }
    return nextId;
  }
}
