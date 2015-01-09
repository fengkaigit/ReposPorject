package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.bo.TotalBillBo;
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
				              {"num",StandardBasicTypes.LONG},
				              {"agent_name",StandardBasicTypes.STRING},
				              {"area_id",StandardBasicTypes.STRING},
				              {"area_name",StandardBasicTypes.STRING}};
		String sql = "select pay_type,agent_id,group_concat(Convert(id ,char)) as ids,sum(paid_money) as total,count(id) as num,agent_name,area_id,area_name from payment_bill where payment_status = ? group by pay_type,agent_id,agent_name,area_id,area_name";
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
		hql.append(" order by batchStatus,areaId,createTime desc,payType");
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
			Boolean errorSeeFlag = (Boolean)Qparam.get("errorSeeFlag");
			if(errorSeeFlag!=null){
				query.append(" and e.errorSeeFlag = ?");
				paramList.add(errorSeeFlag);
			}
			Integer paymentStatus = (Integer)Qparam.get("paymentStatus");
			if(paymentStatus!=null){
				query.append(" and a.paymentStatus = ?");
				paramList.add(paymentStatus);
			}
			Boolean outDate = (Boolean)Qparam.get("outDate");
			if(outDate!=null){
				Date date = new Date();
				String currentMonth = DateUtil.getDateTime("yyyy-MM",date);
				query.append(" and '"+currentMonth+"-'||b.outDate <= ?");
				paramList.add(DateUtil.getDate(date));
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
			Boolean errHandleFlag = (Boolean)Qparam.get("errHandleFlag");
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
			if(errHandleFlag!=null){
				query.append(" and errHandleFlag = ?");
				paramList.add(errHandleFlag);
			}
		}
	}

	@Override
	public Long findBillTotalBatchId(Long id, Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(e.id.paymentBillId) from PaymentBill a,ChargeEnterprise b,BatchPaymentRelation e where a.id = e.id.paymentBillId and a.entId = b.id");
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
	public void updateErrorFlagByBatchId(Long batchId,Long billId,Boolean flag)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update AgentPaymentBatch set errorFlag = ? where id = ?";
		this.executeHql(hql, new Object[]{flag,batchId});
		hql = "select count(id.paymentBillId) from BatchPaymentRelation where errorFlag = ? and id.paymentBillId = ? and id.relationId = ?";
		List list = this.find(hql, new Object[]{true,billId,batchId});
		if(list!=null&&list.size()>0){
			Long num = Long.valueOf(list.get(0)+"");
			if(num!=null&&num==0){
				hql = "update AgentPaymentBatch set errorBillNum = errorBillNum + 1 where id = ?";
			   this.executeHql(hql, new Object[]{batchId});
			}
		}
		updateErrorFlagByBilldId(batchId,billId,flag);
	}
	@Override
	public void updateComplateFlagByBatchId(Long batchId,List<String> list,Boolean flag)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select id.paymentBillId from BatchPaymentRelation where id.relationId = ? and errorFlag = ?";
		List errlist = this.find(hql, new Object[]{batchId,true});
		if(list!=null&&list.size()>0){
			for(String billIdStr:list){
				Long billId = Long.valueOf(billIdStr.trim());
				if(errlist.contains(billId)){
					hql = "update AgentPaymentBatch set errorBillNum = errorBillNum - 1 where id = ?";
					this.executeHql(hql, new Object[]{batchId});
					errlist.remove(billId);
				}
				updateErrorFlagByBilldId(batchId,billId,flag);
			}
		}
		if(errlist!=null&&errlist.size()==0){
		     hql = "update AgentPaymentBatch set errorFlag = ? where id = ?";
		     this.executeHql(hql, new Object[]{flag,batchId});
		}
	}
   
	public void updateErrorFlagByBilldId(Long batchId,Long billId,Boolean flag)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update BatchPaymentRelation set errorFlag = ? where id.paymentBillId = ? and id.relationId = ?";
		this.executeHql(hql, new Object[]{flag,billId,batchId});
		hql = "delete from PaymentHedge where billId = ?";
		this.executeHql(hql, new Object[]{billId});
		hql = "delete from NoticeInfo where billId = ?";
		this.executeHql(hql, new Object[]{billId});
	}
	@Override
	public List findNoticeByBillId(Long billId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="from NoticeInfo where billId = ?";
		return this.find(hql, new Object[]{billId});
	}

	@Override
	public void updateErrHandFlagById(final Long batchId, Long billId,Boolean handFlag)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update BatchPaymentRelation set errorSeeFlag = ? where id.paymentBillId = ? and id.relationId = ?";
		this.executeHql(hql, new Object[]{handFlag,billId,batchId});
		Long num = this.findBillTotalBatchId(batchId, new HashMap<String,Object>(){
		     {
		    	 put("batchId", batchId);
		    	 put("errorSeeFlag",false);
		     }
		     });
		if(num!=null&&num==0){
		    hql = "update AgentPaymentBatch set errHandleFlag = ? where id = ?";
			this.executeHql(hql, new Object[]{handFlag,batchId});
		}
	}

	@Override
	public List getOutBatchBill(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		Date date = new Date();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.TotalBillBo(a.id,a.payType,a.payTypeName,a.areaId,a.areaName,a.createTime,count(a.id)) from AgentPaymentBatch a,BatchPaymentRelation b,ChargeEnterprise c,PaymentBill d where a.id = b.id.relationId and b.id.paymentBillId = d.id and d.entId = c.id");
		hql.append(" and d.paymentStatus = ?");
		paramList.add(3);
		String currentMonth = DateUtil.getDateTime("yyyy-MM",date);
		hql.append(" and '"+currentMonth+"-'||c.outDate <= ?");
		paramList.add(DateUtil.getDate(date));
		hql.append(" group by a.id,a.payType,a.payTypeName,a.areaId,a.areaName,a.createTime order by a.areaId,a.payType,a.createTime desc");
		return this.find(hql.toString(), paramList,page,rows);
	}
	@Override
	public Long getCountOutBatchBill(Map<String,Object> Qparam) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		Date date = new Date();
		//StringBuffer totalHql = new StringBuffer("select count(*) from (");
		StringBuffer hql = new StringBuffer("select a.id from AgentPaymentBatch a,BatchPaymentRelation b,ChargeEnterprise c,PaymentBill d where a.id = b.id.relationId and b.id.paymentBillId = d.id and d.entId = c.id");
		hql.append(" and d.paymentStatus = ?");
		paramList.add(3);
		String currentMonth = DateUtil.getDateTime("yyyy-MM",date);
		hql.append(" and '"+currentMonth+"-'||c.outDate <= ?");
		paramList.add(DateUtil.getDate(date));
		hql.append(" group by a.id,a.payType,a.payTypeName,a.areaId,a.areaName,a.createTime");
		//totalHql.append(hql);
		//totalHql.append(")");
		List list = this.find(hql.toString(), paramList);
		if(list!=null&&list.size()>0){
        	return Long.valueOf(list.size());
        }
        return 0L;
	}

	@Override
	public List statislSummaryErrorBill(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		Object[][] scalaries={{"areaId",StandardBasicTypes.STRING},
	              {"areaName",StandardBasicTypes.STRING},
	              {"payType",StandardBasicTypes.INTEGER},
	              {"payTypeName",StandardBasicTypes.STRING},
	              {"batchIds",StandardBasicTypes.STRING},
	              {"errBillNum",StandardBasicTypes.LONG}};
        StringBuffer query = new StringBuffer("select area_id as areaId,area_name as areaName,pay_type as payType,pay_typename as payTypeName,group_concat(Convert(id ,char)) as batchIds,sum(errorBillNum) as errBillNum from agent_payment_batch where error_flag = ?");
        paramList.add(true);
        createErrCountParam(query,Qparam,paramList);
        query.append(" group by area_id,area_name,pay_type,pay_typename order by area_id,pay_type");
        return this.List(query.toString(),scalaries,TotalBillBo.class,paramList.toArray(),page,rows);
	}
	@Override
	public Long statislSummaryErrorBillCount(Map<String, Object> Qparam) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer totalsql = new StringBuffer("select count(*) from (");
        StringBuffer query = new StringBuffer("select area_id,pay_type from agent_payment_batch where error_flag = ? group by area_id,area_name,pay_type,pay_typename");
        createErrCountParam(query,Qparam,paramList);
        query.append(" group by area_id,area_name,pay_type,pay_typename");
        totalsql.append(query);
        totalsql.append(")");
        List  list =  this.List(totalsql.toString(),paramList.toArray());
        if(list!=null&&list.size()>0){
        	return Long.valueOf(list.get(0)+"");
        }
        return 0L;
	}

	@Override
	public List statislSummaryOutNoCompBill(
			Map<String, Object> Qparam, Integer page, Integer rows)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		Object[][] scalaries={{"areaId",StandardBasicTypes.STRING},
	              {"areaName",StandardBasicTypes.STRING},
	              {"payType",StandardBasicTypes.INTEGER},
	              {"payTypeName",StandardBasicTypes.STRING},
	              {"batchIds",StandardBasicTypes.STRING},
	              {"outBillNum",StandardBasicTypes.LONG}};
      StringBuffer query = new StringBuffer("select a.area_id as areaId,a.area_name as areaName,a.pay_type as payType,a.pay_typename as payTypeName,group_concat(Convert(a.id ,char)) as batchIds,count(a.id) as outBillNum from agent_payment_batch a,batch_payment_relation b,charge_enterprise c,payment_bill d where a.id = b.relation_id and b.payment_bill_id = d.id and d.ent_id = c.id");
	  createOutCountParam(query,Qparam,paramList);
	  query.append(" group by a.area_id,a.area_name,a.pay_type,a.pay_typename order by a.area_id,a.pay_type");
      return this.List(query.toString(),scalaries,TotalBillBo.class,paramList.toArray(),page,rows);
	}
	@Override
	public Long statislSummaryOutBillCount(Map<String, Object> Qparam) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer totalsql = new StringBuffer("select count(*) from (");
		StringBuffer query = new StringBuffer("select a.area_id,a.pay_type from agent_payment_batch a,batch_payment_relation b,charge_enterprise c,payment_bill d where a.id = b.relation_id and b.payment_bill_id = d.id and d.ent_id = c.id");
		createOutCountParam(query,Qparam,paramList);
		query.append(" group by a.area_id,a.area_name,a.pay_type,a.pay_typename");
        totalsql.append(query);
        totalsql.append(")");
        List  list =  this.List(totalsql.toString(),paramList.toArray());
        if(list!=null&&list.size()>0){
        	return Long.valueOf(list.get(0)+"");
        }
        return 0L;
	}
	private void createErrCountParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			
		}
	}
	private void createOutCountParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			Integer payStatus = (Integer)Qparam.get("payStatus");
			if(payStatus!=null){
				query.append(" and d.payment_status = ?");
				paramList.add(payStatus);
			}
			Boolean outDate = (Boolean)Qparam.get("outDate");
			if(outDate!=null){
				Date date = new Date();
				String currentMonth = DateUtil.getDateTime("yyyy-MM",date);
				query.append(" and concat('"+currentMonth+"-',c.out_date) <= ?");
				paramList.add(DateUtil.getDate(date));
			}
		}
	}
 

}
