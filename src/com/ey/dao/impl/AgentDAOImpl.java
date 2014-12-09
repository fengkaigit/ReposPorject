package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.SystemManager;
import com.ey.util.StringUtil;
import org.hibernate.type.*;

@Repository("agentDAO")
public class AgentDAOImpl extends BaseDAOImpl implements AgentDAO {

	@Override
	public void deleteByAgentId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
         String hql = "update from AgentInfo set delFlag = ? where id = ?";
         this.executeHql(hql, new Object[]{true,id});
	}

	@Override
	public List<AgentBo> getAllAgent(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath) from AgentInfo a,Area b where a.areaId = b.id and a.delFlag = 0");
		createQueryParam(hql,Qparam,paramList);
		hql.append(" order by a.areaId");
		return this.find(hql.toString(), paramList.toArray(),page, rows);
	}

	@Override
	public AgentBo getAgent(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath,b.city) from AgentInfo a,Area b where a.areaId = b.id and a.id = ?";
		List<AgentBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public AgentBo findAgentByLoginName(String loginName, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath,b.city) from AgentInfo a,Area b where a.areaId = b.id and a.registAccount = ? and a.passwd = ? and a.delFlag = 0";
		List<AgentBo> agents = this.find(hql.toString(),new Object[]{loginName,password});
		if(agents!=null&&agents.size()>0)
			return agents.get(0);
		return null;
	}

	@Override
	public Long findAgentByLoginName(String loginName) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select count(id) from AgentInfo a where a.registAccount = ? and a.delFlag = 0";
		return this.count(hql, new Object[]{loginName});
	}

	@Override
	public void updatePassById(Long id, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update AgentInfo set passwd = ? where id = ?";
		this.executeHql(hql, new Object[]{password,id});
	}

	@Override
	public BankAccount getBankAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select b from AgentAccountRelation a,BankAccount b where a.id.bankAccountId = b.id and a.id.id = ? and a.flag = ?";
		List<BankAccount> list = this.find(hql, new Object[]{id,false});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	private void createQueryParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			String areaId = (String)Qparam.get("areaId");
			if(!StringUtil.isEmptyString(areaId)){
				query.append(" and a.areaId = ?");
				paramList.add(areaId);
			}
			
		}
	}
 
	@Override
	public List<Object> findUserByParam(String areaId,String year)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select regTime from UserBase where 1=1");
		if(!StringUtil.isEmptyString(areaId)){
			hql.append(" and areaId = ?");
			paramList.add(areaId);
		}
		if(!StringUtil.isEmptyString(year)){
			hql.append(" and date_format(regTime,'%Y') = ?");
			paramList.add(year);
		}
		return this.find(hql.toString(),paramList.toArray());
	}
	@Override
	public List findPaymentBillByAgentId(Long id, String year)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select createTime,paidMoney from PaymentBill where agentId = ? and date_format(createTime,'%Y') = ? and paymentStatus not in(0,2)";
		return this.find(hql, new Object[]{id,year});
	}

	@Override
	public List findBillNumByMonth(Long id, String month)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select paymentStatus from PaymentBill where agentId = ? and date_format(createTime,'%Y-%m') = ?";
		return this.find(hql, new Object[]{id,month});
	}

	@Override
	public AgentInfo getAgentByArea(String areaId) throws RuntimeException {
		String hql = "from AgentInfo a where a.delFlag = 0 and areaId=?";
		List<AgentInfo> lst = this.find(hql, new Object[]{areaId});
		if (lst!=null && lst.size()>0)
			return lst.get(0);
		else
			return null;
	}

	@Override
	public List findBillSettleByMonth(Long id, String month)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List billSettlist = new ArrayList();
		String hql = "select coalesce(sum(paidMoney),0),'billmoney' from PaymentBill where agentId = ? and date_format(createTime,'%Y-%m') = ? and paymentStatus not in(0,2)";
		List billlist = this.find(hql, new Object[]{id,month});
		hql = "select coalesce(sum(profitMoney),0),'settlemoney' from SettleBill where agentId = ? and date_format(createDate,'%Y-%m') = ?";
		List settlelist = this.find(hql, new Object[]{id,month});
		billSettlist.addAll(billlist);
		billSettlist.addAll(settlelist);
		return billSettlist;
	}

	@Override
	public List findBillByCurrentDay(String currentDay)
			throws RuntimeException {
		// TODO Auto-generated method stub
		Object[][] scalaries={{"pay_type",StandardBasicTypes.INTEGER},
				              {"agent_id",StandardBasicTypes.LONG},
				              {"ids",StandardBasicTypes.STRING},
				              {"total",StandardBasicTypes.DOUBLE},
				              {"num",StandardBasicTypes.LONG}};
		String sql = "select pay_type,agent_id,group_concat(Convert(id ,char)) as ids,sum(paid_money) as total,count(id) as num from payment_bill where payment_status = ? group by pay_type,ent_id,agent_id";
		return this.List(sql,scalaries,new Object[]{1});
	}

	@Override
	public List findAgentSelf(Long id,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "from AgentPaymentBatch where agentId = ? and batchStatus = 0 order by createTime desc,payType";
		return this.find(hql, new Object[]{id},page,rows);
	}

	@Override
	public List findBillByBatchId(Long id,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select b from BatchPaymentRelation a,PaymentBill b where a.id.paymentBillId = b.id and a.id.relationId = ? order by b.createTime desc,payType";
		return this.find(hql, new Object[]{id},page,rows);
	}

	@Override
	public void updateBillStatusByIds(java.util.List<String> list)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update PaymentBill set paymentStatus = 3 where id = ?";
		for(String id:list){
			this.executeHql(hql,new Object[]{Long.valueOf(id.trim())});
		}
	}

	@Override
	public Long getTotalAgentByParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(id) from AgentInfo a where a.delFlag = 0");
		createQueryParam(hql,Qparam,paramList);
		List list= this.find(hql.toString(),paramList.toArray());
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0)+"");
		}
		return 0l;
	}

}
