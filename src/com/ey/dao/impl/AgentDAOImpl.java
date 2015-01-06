package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.SystemManager;
import com.ey.util.DateUtil;
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
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath,b.city,a.signPeriod,a.signDate) from AgentInfo a,Area b where a.areaId = b.id and a.id = ?";
		List<AgentBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public AgentBo findAgentByLoginName(String loginName, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath,b.city,a.signPeriod,a.signDate) from AgentInfo a,Area b where a.areaId = b.id and a.registAccount = ? and a.passwd = ? and a.delFlag = 0";
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
	public List findAgentSelf(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from AgentPaymentBatch where 1=1");
		if(id!=null){
		   hql.append(" and agentId = ?");
		   paramList.add(id);
		}
		createQuerySelfParam(hql,Qparam,paramList);
		hql.append(" order by batchStatus,createTime desc,payType");
		return this.find(hql.toString(), paramList,page,rows);
	}

	@Override
	public List findBillByBatchId(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.PaymentBillBo(a.id, a.accountBillId, a.userId,a.remainBalance,");
		hql.append("a.createTime, a.paidMoney,a.payMoney, a.balance, a.poundage, a.payType,a.entId, a.businessType,");
		hql.append("a.paymentStatus,a.paymentMode, a.uuid, a.divideStatus, a.areaId, a.areaName, a.agentId,");
		hql.append("a.agentName,a.orderNumber, a.remarks, a.payAddress,b.enterpriseName,c.realName,d.propChName,e.id.relationId) from PaymentBill a,ChargeEnterprise b,UserBase c,BaseCustomValue d,BatchPaymentRelation e where d.id.customEngName='payment_type' and a.payType=d.id.dataValue and a.entId=b.id and a.userId=c.id and e.id.paymentBillId = a.id");
		Integer flag = (Integer)Qparam.get("statusFlag");
		if(flag!=null)
		   createQueryBillOrParam(hql,Qparam,paramList);
		else
		   createQueryBillParam(hql,Qparam,paramList);
		hql.append(" order by a.createTime desc,a.payType");
		return this.find(hql.toString(),paramList.toArray(),page,rows);
	}

	@Override
	public void updateBillStatusByIds(List<String> list,Integer status)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update PaymentBill set paymentStatus = ? where id = ?";
		for(String id:list){
			this.executeHql(hql,new Object[]{status,Long.valueOf(id.trim())});
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
	
	private void createQueryBillParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			Long batchId = (Long)Qparam.get("batchId");
			if(batchId!=null){
				query.append(" and e.id.relationId = ?");
				paramList.add(batchId);
			}
			Long billId = (Long)Qparam.get("billId");
			if(billId!=null){
				query.append(" and e.id.paymentBillId = ?");
				paramList.add(billId);
			}
			Boolean errflag = (Boolean)Qparam.get("errorFlag");
			if(errflag!=null){
				query.append(" and e.errorFlag = ?");
				paramList.add(errflag);
			}
			Integer paymentStatus = (Integer)Qparam.get("paymentStatus");
			if(paymentStatus!=null){
				query.append(" and a.paymentStatus = ?");
				paramList.add(paymentStatus);
			}
		}
	}
	
	private void createQueryBillOrParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			Integer paymentStatus = (Integer)Qparam.get("paymentStatus");
			if(paymentStatus!=null){
				query.append(" and a.paymentStatus = ?");
				paramList.add(paymentStatus);
			}
			Long batchId = (Long)Qparam.get("batchId");
			query.append(" and (");
			if(batchId!=null){
				query.append(" e.id.relationId = ?");
				paramList.add(batchId);
			}
			Long billId = (Long)Qparam.get("billId");
			if(billId!=null){
				query.append(" or e.id.paymentBillId = ?");
				paramList.add(billId);
			}
			query.append(")");
		}
	}
	
	private void createQuerySelfParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			Integer status = (Integer)Qparam.get("status");
			Integer payType = (Integer)Qparam.get("payType");
			String startDate = (String)Qparam.get("startDate");
			String endDate = (String)Qparam.get("endDate");
			Boolean errflag = (Boolean)Qparam.get("errorFlag");
			if(status!=null){
				query.append(" and batchStatus = ?");
				paramList.add(status);
			}
			if(payType!=null){
				query.append(" and payType = ?");
				paramList.add(payType);
			}
			if(!StringUtil.isEmptyString(startDate)){
				query.append(" and createTime >= ?");
				paramList.add(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startDate+" 00:00:00"));
			}
			if(!StringUtil.isEmptyString(endDate)){
				query.append(" and createTime <= ?");
				paramList.add(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",endDate+" 23:59:59"));
			}
			if(errflag!=null){
				query.append(" and errorFlag = ?");
				paramList.add(errflag);
			}
		}
	}

	@Override
	public Long findBillTotalBatchId(Long id, Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(e.id.paymentBillId) from PaymentBill a,BatchPaymentRelation e where a.id = e.id.paymentBillId");
		createQueryBillParam(hql,Qparam,paramList);
		List list = this.find(hql.toString(),paramList.toArray());
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0)+"");
		}
		return 0l;
	}

	@Override
	public Long findAgentSelfTotal(Long id, Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(id) from AgentPaymentBatch where 1=1");
		if(id!=null){
			   hql.append(" and agentId = ?");
			   paramList.add(id);
		}
		createQuerySelfParam(hql,Qparam,paramList);
		List list = this.find(hql.toString(), paramList.toArray());
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0)+"");
		}
		return 0l;
	}

	@Override
	public void updateStatusByBatchId(Long id, Integer status)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update AgentPaymentBatch set confirmTime = ?,batchStatus = ? where id = ?";
		this.executeHql(hql, new Object[]{new Date(),status,id});
	}

	@Override
	public List findAgentSignRateByAgentId(Long id)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "from AgentSignRate where agentId = ?";
		return this.find(hql, new Object[]{id});
	}

	@Override
	public void updateSignRateById(Long id, Double rate)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="update AgentSignRate set signRate = ? where id = ?";
		this.executeHql(hql, new Object[]{rate,id});
	}

	@Override
	public void updateErrorFlagByBatchId(Long batchId,Boolean flag)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update AgentPaymentBatch set errorFlag = ? where id = ? and errorFlag=?";
		this.executeHql(hql, new Object[]{flag,batchId,false});
	}

	@Override
	public List findNoticeByBillId(Long billId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="from NoticeInfo where billId = ?";
		return this.find(hql, new Object[]{billId});
	}
 

}
