package com.ey.controller.phoneController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ey.bo.AreaBo;
import com.ey.bo.CatvInfoBo;
import com.ey.bo.PaymentSettingPhoneBo;
import com.ey.bo.PaymentSettingPropertyBo;
import com.ey.bo.PaymentSettingStandardBo;
import com.ey.bo.PaymentSettingTrafficBo;
import com.ey.bo.ResultBo;
import com.ey.bo.StandardBo;
import com.ey.consts.SystemConst;
import com.ey.dao.entity.Area;
import com.ey.dao.entity.CatvInfo;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.PaymentSetting;
import com.ey.dao.entity.UserBase;
import com.ey.service.AreaService;
import com.ey.service.CatvService;
import com.ey.service.ChargeEntService;
import com.ey.service.SettingService;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {

	@Autowired
    private AreaService areaService;
	
	@Autowired
    private ChargeEntService chargeEntService;
	
	@Autowired
    private SettingService settingService;
	
	@Autowired
    private CatvService catvService;
	
	private ResultBo json;
	
	@RequestMapping(value="/areaJson")
	public @ResponseBody ResultBo getAreaInJSON(String areaType,HttpServletRequest request,
			HttpServletResponse response){
		try{
			List<AreaBo> areaLst = new ArrayList();
			List<Area> dataList = areaService.getAreaList(areaType);
			// 节点列表（散列表，用于临时存储节点对象）  
			HashMap nodeList = new HashMap();  
			// 根据结果集构造节点列表（存入散列表）
			for (Iterator it = dataList.iterator(); it.hasNext();) {
				Area dataRecord = (Area) it.next();
				AreaBo node = new AreaBo();  
	
				node.setId( (String)dataRecord.getId());
				node.setName( (String) dataRecord.getProvince());
				node.setParentId( (String)dataRecord.getCity());
				nodeList.put(node.getId(), node);
			}
			
			// 构造无序的多叉树
			Set entrySet = nodeList.entrySet();  
			for (Iterator it = entrySet.iterator(); it.hasNext();) {
				AreaBo node = (AreaBo) ((Map.Entry) it.next()).getValue();
				if (node.findParentId().equals("0")) {  
					((AreaBo)nodeList.get(node.getId())).setId(node.getId());
					((AreaBo)nodeList.get(node.getId())).setName(node.getName());
					((AreaBo)nodeList.get(node.getId())).setParentId(node.findParentId());
				}else{
				   ((AreaBo) nodeList.get(node.findParentId())).addChild(node);
				}
			}
			for (Iterator it = entrySet.iterator(); it.hasNext();) {
				AreaBo node = (AreaBo) ((Map.Entry) it.next()).getValue();
				if (node.findParentId().equals("0")){
					((AreaBo)nodeList.get(node.getId())).sortChildren();
					areaLst.add((AreaBo)nodeList.get(node.getId()));
				}
			}
			// 输出有序的树形菜单的JSON字符串
			Collections.sort(areaLst,new NodeIDComparator());
			json = new ResultBo();
			json.setSuccess(true);
			json.setData(areaLst);
		}catch (Exception e) {
			json = new ResultBo();
			json.setSuccess(false);
			json.setData("获取缴费地区信息失败！");
		}
		return json;
	}
	
	@RequestMapping(value="/entJson")
	public @ResponseBody ResultBo getEntInJSON(String areaId,int payType,HttpServletRequest request,
			HttpServletResponse response){
		try{
			List<ChargeEnterprise> lst = chargeEntService.getChargesByArea(areaId, payType);
			List<StandardBo> boLst = new ArrayList();
			for (ChargeEnterprise ent:lst){
				StandardBo bo = new StandardBo(ent.getId(),ent.getEnterpriseName());
				boLst.add(bo);
			}
			json = new ResultBo();
			json.setSuccess(true);
			json.setData(boLst);
		}catch (Exception e) {
			json = new ResultBo();
			json.setSuccess(false);
			json.setData("获取缴费单位信息失败！");
		}
		return json;
	}
	
	@RequestMapping(value="/paymentSettingJson")
	public @ResponseBody ResultBo getPaymentSettingInJSON(int payType,HttpServletRequest request,
			HttpServletResponse response){
		try{
			UserBase currentUser = (UserBase) request.getSession().getAttribute(
					SystemConst.USER);
			List<PaymentSetting> lst = settingService.list(currentUser.getId(),payType);
			List standardLst = new ArrayList();
			for (PaymentSetting setting:lst){
				if (payType==4){
					PaymentSettingPhoneBo bo = new PaymentSettingPhoneBo(setting.getBillNumber(),setting.getHoster());
					standardLst.add(bo);
				}else if (payType==5){
					PaymentSettingTrafficBo bo = new PaymentSettingTrafficBo(setting.getBillNumber(),setting.getHoster(),setting.getVehicleNumber(),setting.getCarframeNumber(),setting.getEngineNumber());
					standardLst.add(bo);
				}else if (payType==6){
					PaymentSettingPropertyBo bo = new PaymentSettingPropertyBo(setting.getHoster(),setting.getPayAddress());
					standardLst.add(bo);
				}else{
					PaymentSettingStandardBo bo = new PaymentSettingStandardBo(setting.getBillNumber(),setting.getHoster(),setting.getPayAddress());
					standardLst.add(bo);
				}
			}
			json = new ResultBo();
			json.setSuccess(true);
			json.setData(standardLst);
		}catch (Exception e) {
			json = new ResultBo();
			json.setSuccess(false);
			json.setData("获取缴费账户设置信息失败！");
		}
		return json;
	}
	
	@RequestMapping(value="/catvInfoJson")
	public @ResponseBody ResultBo getCatvInJSON(String areaId,int televisionType,HttpServletRequest request,
			HttpServletResponse response){
		try{
			List<CatvInfo> lst = catvService.getCatvInfo(areaId, televisionType);
			List<CatvInfoBo> boLst = new ArrayList();
			for (CatvInfo catv:lst){
				CatvInfoBo bo = new CatvInfoBo(catv.getId(),catv.getTelevisionName(),catv.getTelevisionMoney());
				boLst.add(bo);
			}
			json = new ResultBo();
			json.setSuccess(true);
			json.setData(boLst);
		}catch (Exception e) {
			json = new ResultBo();
			json.setSuccess(false);
			json.setData("获取有线电视设置信息失败！");
		}
		return json;
	}
	
	
}



/** 
* 节点比较器 
*/  
class NodeIDComparator implements Comparator {  
	// 按照节点编号比较  
	public int compare(Object o1, Object o2) {  
		int j1 = Integer.parseInt(((AreaBo)o1).id);  
		int j2 = Integer.parseInt(((AreaBo)o2).id);  
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
	}   
}
