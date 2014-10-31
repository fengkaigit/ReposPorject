package com.ey.service.impl;

import org.springframework.stereotype.Service;

import com.ey.dao.common.dbid.DatabaseDbidGenerator;
import com.ey.service.InitDbIdService;
import com.ey.service.common.SpringWiredBean;


@Service("initDdbIdService")
public class defaultInitDbIdService implements InitDbIdService {

	@Override
	public void execute() throws RuntimeException {
		// TODO Auto-generated method stub
		SpringWiredBean wiredBean = SpringWiredBean.getInstance();
		DatabaseDbidGenerator dbidGenerator = (DatabaseDbidGenerator)wiredBean.getBean("dbIdGenerator");
		dbidGenerator.initialize();
	}

}
