package com.ey.service;
import com.ey.dao.entity.UserBase;

public interface BasePoundageService {
	public  double getPoundage(UserBase user,Integer paymentType,String areaId);
}
