package com.ey.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.PaymentBillBO;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.UserBase;

public interface JfService {
	public Object getObjectById(Class c,Serializable id) throws RuntimeException;

	public void prePareParams(ModelAndView mav, UserBase currentUser,
			String settingId, Integer type,boolean loadArea) throws RuntimeException;

	public List<PaymentBillBO> getTotalRecords(Long userId,Integer year,
			String startMonth, String endMonth)throws RuntimeException;

	public List<PaymentBillBO> getDetails(Long userId, Integer year,
			Integer month);
}
