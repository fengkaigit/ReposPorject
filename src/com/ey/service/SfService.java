package com.ey.service;

import java.io.Serializable;

import com.ey.forms.JfForm;

public interface SfService {

	void saveBill(JfForm form);

	Object getObjectById(Class c,Serializable id);

}
