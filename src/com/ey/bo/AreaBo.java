package com.ey.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AreaBo {

	public String id;
	
	private String name;
	
	private String parentId;
	
	/** 
	  * 孩子节点列表 
	  */  
	private List children = new ArrayList();  

	// 添加孩子节点  
	public void addChild(AreaBo node) {  
		this.children.add(node);
	}

	public AreaBo(){
		
	}
	
	public AreaBo(String id, String name, String parentId){
		this.id = id ;
		this.name = name;
		this.parentId = parentId;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String findParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}
	
	// 孩子节点排序  
	public void sortChildren() {  
		// 对本层节点进行排序  
		// 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器  
		Collections.sort(children, new NodeIDComparator());  
		// 对每个节点的下一层节点进行排序  
		for (Iterator it = children.iterator(); it.hasNext();) {  
			((AreaBo) it.next()).sortChildren();  
		}  
	}
	
	// 拼接孩子节点的JSON字符串
	public String toString() {  
	  String result = "[";    
	  for (Iterator it = children.iterator(); it.hasNext();) {  
	   result += ((AreaBo) it.next()).toString();  
	   result += ",";  
	  }
	  result = result.substring(0, result.length() - 1);  
	  result += "]";  
	  return result;  
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
