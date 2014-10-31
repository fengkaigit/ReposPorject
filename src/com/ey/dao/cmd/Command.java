package com.ey.dao.cmd;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;


public interface Command<T> extends Serializable {
  
  T execute(Session session) throws Exception;
}
