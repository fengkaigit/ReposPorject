package com.ey.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ey.bo.QueryBillBO;
import com.ey.dao.JfDAO;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.CatvInfo;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentSetting;
import com.ey.dao.entity.UserBase;
import com.ey.service.AreaService;
import com.ey.service.CatvService;
import com.ey.service.ChargeEntService;
import com.ey.service.FeeService;
import com.ey.service.JfService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;
import com.ey.util.FeeUtil;
import com.ey.util.StringUtil;

@Service("jfService")
public class JfServiceImpl implements JfService {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ChargeEntService chargeEntService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private JfDAO jfDAO;
	@Autowired
	private CatvService catvService;
	@Autowired
	private FeeService feeService;
	public Object getObjectById(Class c, Serializable id)
			throws RuntimeException {
		return jfDAO.get(c, id);
	}

	@Override
	public void prePareParams(ModelAndView mav, UserBase currentUser,
			String settingId, Integer type, boolean loadArea,ServletContext servletContext)
			throws RuntimeException {
		PaymentSetting setting = null;
		if (!StringUtil.isEmptyString(settingId)) {
			try {
				setting = (PaymentSetting) getObjectById(PaymentSetting.class,
						new Long(settingId));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		String areaId = null;
		if (setting != null) {
			mav.addObject("billNumber", setting.getBillNumber());
			mav.addObject("payAddress", setting.getPayAddress());
			mav.addObject("hoster", setting.getHoster());
			mav.addObject("vehicleNumber", setting.getVehicleNumber());
			mav.addObject("carframeNumber", setting.getCarframeNumber());
			mav.addObject("engineNumber", setting.getEngineNumber());
			areaId = setting.getAreaId();
		}
		if(areaId==null){
			areaId = currentUser.getAreaId();
		}
		Double poundage = null;
		if(areaId!=null){
			try{
				FeeRule feeRule = feeService.getFeeRule(type, new Date());
				poundage = FeeUtil.getPoundage(feeRule.getRule(), servletContext, currentUser, type, areaId);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		if(poundage==null){
			poundage = 0d;
		}
		mav.addObject("poundage", poundage);
		if (!loadArea) {
			return;
		}
		List<Area> areaList = areaService.getAreasByCity("0");
		List<Area> cityList = null;
		List<ChargeEnterprise> charges = null;
		List<CatvInfo> tvList = null;
		if (setting == null) {
			if (StringUtil.isEmptyString(currentUser.getAreaId())) {
				if (areaList != null && areaList.size() > 0) {
					cityList = areaService.getAreasByCity(areaList.get(0)
							.getId());
					if (cityList != null && cityList.size() > 0) {
						charges = chargeEntService.getChargesByArea(cityList
								.get(0).getId(), type);
					}
					if(type.intValue()==7){
						tvList = catvService.getCatvInfo(cityList.get(0).getId(), 0);
					}
				}
			} else {
				Area city = areaService.getArea(currentUser.getAreaId());
				cityList = areaService.getAreasByCity(city.getCity());
				charges = chargeEntService.getChargesByArea(currentUser
						.getAreaId(), type);
				mav.addObject("parent", city.getCity());
				mav.addObject("areaId", currentUser.getAreaId());
				if(type.intValue()==7){
					tvList = catvService.getCatvInfo(currentUser.getAreaId(), 0);
				}
			}
		} else {
			Area city = areaService.getArea(setting.getAreaId());
			cityList = areaService.getAreasByCity(city.getCity());
			charges = chargeEntService.getChargesByArea(setting.getAreaId(),
					type);
			mav.addObject("parent", city.getCity());
			mav.addObject("areaId", setting.getAreaId());
			if(type.intValue()==7){
				tvList = catvService.getCatvInfo(setting.getAreaId(), 0);
			}
		}

		if (areaList == null) {
			areaList = new ArrayList();
		}
		if (cityList == null) {
			cityList = new ArrayList();
		}
		if (charges == null) {
			charges = new ArrayList();
		}
		if (tvList == null) {
			tvList = new ArrayList();
		}
		mav.addObject("areaList", areaList);
		mav.addObject("cityList", cityList);
		mav.addObject("charges", charges);
		mav.addObject("tvList", tvList);

	}

	@Override
	public List<QueryBillBO> getTotalRecords(Long userId, Integer year,
			String startMonth, String endMonth) throws RuntimeException {
		// TODO Auto-generated method stub
		List<Object[]> records = jfDAO.getTotalRecords(userId, year,
				startMonth, endMonth);
		Map<Integer, Map<Integer, Integer>> statusMap = new HashMap();// 存放每月成功多少比、失败多少比
		Map<Integer, Map<Integer, Double>> moneyMap = new HashMap();// 存放每月缴费钱数，成功多少、失败多少
		Map<Integer, String> itemMap = new HashMap();// 存放每月缴费内容
		List<Integer> monthList = new ArrayList();
		List<QueryBillBO> pbList = new ArrayList();
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		// sum(payMoney+poundage),payType,paymentStatus,year,month
		for (Object[] objs : records) {
			Double money = (Double) objs[0];// 金额
			Integer payType = (Integer) objs[1];// 缴费类型
			//Integer payStatus = (Integer) objs[2];// 状态
			Integer _year = (Integer) objs[2];// 年
			Integer _month = (Integer) objs[3];// 月
			//Long _sum = (Long) objs[4];// 笔数
			//buildStatusMap(statusMap, payStatus, _month,_sum);
			buildMoneyMap(moneyMap, null, _month, money);
			buildItemMap(itemMap, payType, _month, paymentTypes);
			if (!monthList.contains(_month)) {
				monthList.add(_month);
			}
		}
		Collections.sort(monthList);
		for (Integer mst : monthList) {
			//Map<Integer, Integer> _statusMap = statusMap.get(mst);
			Map<Integer, Double> _moneyMap = moneyMap.get(mst);
			String _item = itemMap.get(mst);
			QueryBillBO pb = new QueryBillBO(_moneyMap.get(2), null, null,
					year, mst, _item, _moneyMap.get(1), _moneyMap.get(0),
					null, null, null);
			pbList.add(pb);
		}
		return pbList;
	}

	void buildItemMap(Map<Integer, String> itemMap, Integer payType,
			Integer _month, List<BaseCustomValue> paymentTypes) {
		String payTypeName = null;

		for (BaseCustomValue paymentType : paymentTypes) {
			if (paymentType.getId().getDataValue().intValue() == payType
					.intValue()) {
				payTypeName = paymentType.getPropChName();
				break;
			}
		}
		String _item = itemMap.get(_month);
		if (_item == null) {
			_item = "";

		}
		String[] _items = _item.split("，");
		boolean contain = false;
		for (String _t : _items) {
			if (_t.equals(payTypeName)) {
				contain = true;
				break;
			}
		}
		if (!contain) {
			if (_item.length() == 0) {
				_item = payTypeName;
			} else {
				_item = _item + "，" + payTypeName;
			}

		}
		itemMap.put(_month, _item);
	}

	void buildMoneyMap(Map<Integer, Map<Integer, Double>> moneyMap,
			Integer payStatus, Integer _month, Double money) {

		Map<Integer, Double> _moneyMap = moneyMap.get(_month);
		if (_moneyMap == null) {
			_moneyMap = new HashMap();
			moneyMap.put(_month, _moneyMap);
		}
		Double ds = _moneyMap.get(0);
		Double ss = _moneyMap.get(1);
		Double ts = _moneyMap.get(2);
		if (ds == null) {
			ds = 0d;
		}
		if (ss == null) {
			ss = 0d;
		}
		if (ts == null) {
			ts = 0d;
		}
		//if (payStatus.intValue() == 1) {
			ss = ss + money;
		//}
		/*if (payStatus.intValue() == 2) {
			ds = ds + money;
		}*/
		ts = ss + ds;
		_moneyMap.put(0, ds);
		_moneyMap.put(1, ss);
		_moneyMap.put(2, ts);

	}

	void buildStatusMap(Map<Integer, Map<Integer, Integer>> statusMap,
			Integer payStatus, Integer _month,Long _sum) {
		if(_sum==null){
			_sum = 1l;
		}
		Map<Integer, Integer> _statusMap = statusMap.get(_month);
		if (_statusMap == null) {
			_statusMap = new HashMap();
			statusMap.put(_month, _statusMap);
		}
		Integer ds = _statusMap.get(0);
		Integer ss = _statusMap.get(1);
		Integer ts = _statusMap.get(2);
		if (ds == null) {
			ds = 0;
		}
		if (ss == null) {
			ss = 0;
		}
		if (ts == null) {
			ts = 0;
		}
		if (payStatus.intValue() == 1) {
			ss = ss + _sum.intValue();
		}
		if (payStatus.intValue() == 2) {
			ds = ds + _sum.intValue();
		}
		ts = ss + ds;
		_statusMap.put(0, ds);
		_statusMap.put(1, ss);
		_statusMap.put(2, ts);
	}

	@Override
	public List<QueryBillBO> getDetails(Long userId, Integer year,
			Integer month) {
		// TODO Auto-generated method stub
		List<PaymentBill> bills = jfDAO.getDetails(userId, year, month);
		List<BaseCustomValue> paymentTypes = staticService
				.listValues("payment_type");
		List<BaseCustomValue> businessTypes = staticService
				.listValues("bussiness_type");
		List<BaseCustomValue> payModes = staticService
				.listValues("payment_mode");
		List<BaseCustomValue> payStatus = staticService
				.listValues("payment_status");
		List<QueryBillBO> list = new ArrayList();
		for (PaymentBill bill : bills) {
			QueryBillBO pb = new QueryBillBO(bill.getPayType(), bill
					.getPaymentStatus(), bill.getYear(), bill.getMonth(), bill
					.getPayMoney(), bill.getPoundage(), bill.getBusinessType(),
					bill.getPaymentMode(), bill.getCreateTime());
			pb.setPayTypeStr(staticService.getLabel(paymentTypes, bill.getPayType()));
			pb.setPayStatuStr(staticService.getLabel(payStatus, bill.getPaymentStatus()));
			pb.setBusiness(staticService.getLabel(businessTypes, bill.getBusinessType()));
			pb.setPayMode(staticService.getLabel(payModes, bill.getPaymentMode()));
			pb.setPayTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", bill
					.getCreateTime()));
			list.add(pb);
		}
		return list;
	}

	

}
