package com.ey.service;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.ServletContext;

import com.ey.dao.entity.UserBase;
import com.ey.forms.JfForm;

public interface SfService {

	void saveBill(JfForm form,UserBase currentUser,ServletContext servletContext) throws RuntimeException;

	Object getObjectById(Class c,Serializable id) throws RuntimeException;
	
	public void saveSetting(JfForm form, Date date) throws RuntimeException;

}
