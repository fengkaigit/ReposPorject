package com.ey.service;

import java.io.Serializable;

import com.ey.forms.JfForm;

public interface CnfService {

	void saveBill(JfForm form) throws RuntimeException;

	Object getObjectById(Class c,Serializable id) throws RuntimeException;

}