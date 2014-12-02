package com.ey.dao;

import java.util.Date;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.FeeRule;

public interface FeeDAO extends BaseDAO {

	FeeRule getFeeRule(int payType, Date date) throws RuntimeException;

}
