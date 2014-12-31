package com.ey.service;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.QueryBillBO;
import com.ey.dao.entity.UserBase;

public interface JfService {
	public Object getObjectById(Class c,Serializable id) throws RuntimeException;

	public void prePareParams(ModelAndView mav, UserBase currentUser,
			String settingId, Integer type,boolean loadArea,ServletContext servletContext) throws RuntimeException;

	public List<QueryBillBO> getTotalRecords(Long userId,Integer year,
			String startMonth, String endMonth)throws RuntimeException;

	public List<QueryBillBO> getDetails(Long userId, Integer year,
			Integer month);
}
