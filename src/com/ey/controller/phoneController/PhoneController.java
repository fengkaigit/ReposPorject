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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ey.bo.AreaBo;
import com.ey.dao.entity.Area;
import com.ey.service.AreaService;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {

	@Autowired
    private AreaService areaService;
	
	@RequestMapping(value="{areaType}", method = RequestMethod.GET) 
	public @ResponseBody List<AreaBo> getAreaInJSON(@PathVariable("areaType") String areaType,HttpServletRequest request,
			HttpServletResponse response){
		areaType="110";
		List<AreaBo> areaLst = new ArrayList();
		List<Area> dataList = areaService.getAreaList(areaType);
		// 节点列表（散列表，用于临时存储节点对象）  
		HashMap nodeList = new HashMap();  
		// 根节点
		
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
		AreaBo root = null;
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
		System.out.println(areaLst.toString());   
		return areaLst;
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
