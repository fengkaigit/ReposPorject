package com.ey.dao.impl;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.bo.PaymentAgentBo;
import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.ServiceChargeBill;
import com.ey.dao.entity.TempPaymentBill;
import com.ey.dao.entity.TransferRecords;

@Repository("profitDao") 
public class ProfitCalculateDAOImpl extends BaseDAOImpl implements ProfitCalculateDAO{

	@Override
	public void update(PaymentBill paymentBill) throws RuntimeException {
		super.update(paymentBill);
	}

	@Override
	public void saveTempPaymentBill(TempPaymentBill paymentBill) throws RuntimeException {
		super.save(paymentBill);
	}

	@Override
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2, String strWhere)
			throws RuntimeException {
		String hql = "update "+ tableName +" set "+ columnName2 +" =? where "+ columnName1 + "=? " + strWhere; 
		Integer status = this.executeHql(hql, new Object[]{status2,status1});
		return status;
	}

	@Override
	public List<PaymentBill> findPaymentBills(Integer paymentStatus,Integer divideStatus)
			throws RuntimeException {
		String hql = "from PaymentBill where paymentStatus=? and divideStatus=? and (uuid is null or uuid='')";
		List<PaymentBill> payment = this.find(hql, new Object[]{paymentStatus,divideStatus});
		return payment;
	}

	@Override
	public Double getProfitMoney() throws RuntimeException {
		String hql="select sum(poundage) from TempPaymentBill where stageStatus=?";
		List<Double> money = this.find(hql,new Object[]{1});
		if (money!=null && money.size()>0)
			return money.get(0);
		else
			return 0d;
	}

	@Override
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException {
		String hql = "update PaymentBill set uuid=? where divideStatus=? and paymentStatus=? and (uuid is null or uuid='')";
		this.executeHql(hql, new Object[]{uuid.toString(),1,10});
	}

	@Override
	public Long getAccountId(Integer acctType) throws RuntimeException {
		String hql = "select bankAccountId from SystemAccount where acctType=?";
		List<Long> accountId = this.find(hql,new Object[]{acctType});
		if (accountId!=null && accountId.size()>0)
			return accountId.get(0);
		else
			return null;
	}

	@Override
	public void deleteTempPaymentBill() throws RuntimeException {
		String hql = "delete from TempPaymentBill";
		this.executeHql(hql);
	}

	@Override
	public Double findTransferRecordsPoundage(
			Long serviceBillId) throws RuntimeException {
		String hql = "select sum(poundage) from TransferRecords where id in (select id.paymentBillId from ServicePaymentRelation where id.serviceBillId=?)";
		List<Double> poundage=this.find(hql,new Object[]{serviceBillId});
		if (poundage!=null && poundage.size()>0)
			return poundage.get(0);
		else
			return null;
	}

	@Override
	public Double findServiceBillMoney(Long serviceBillId)
			throws RuntimeException {
		String hql = "from ServiceChargeBill where id=?";
		List<ServiceChargeBill> serviceBillList = this.find(hql,new Object[]{serviceBillId});
		if (serviceBillList!=null && serviceBillList.size()>0)
			return serviceBillList.get(0).getProfitMoney();
		else
			return null;
	}

	@Override
	public List<PaymentBill> findPaymentBills(Long serviceBillId)
			throws RuntimeException {
		String hql = "from PaymentBill where id in (select id.paymentBillId from ServicePaymentRelation where id.serviceBillId=?)";
		return this.find(hql, new Object[]{serviceBillId});
	}

	@Override
	public List<PaymentAgentBo> findAgentInfo(Long serviceBillId) 
			throws RuntimeException {
		String hql = "select new com.ey.bo.PaymentAgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,sum(b.poundage)) " +
				"from AgentInfo a, PaymentBill b where a.id=b.agentId and b.id in (select id.paymentBillId from ServicePaymentRelation where id.serviceBillId=?)" +
				"group by a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId";
		return this.find(hql, new Object[]{serviceBillId});
	}

	@Override
	public AgentBo findAgentRule(Long agentId) throws RuntimeException {
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath,c.rule,d.id.bankAccountId) from AgentInfo a,Area b,AgentRule c, AgentAccountRelation d" +
				" where a.areaId = b.id and a.id=c.agentId and a.id=d.id.id and a.id = ? and a.delFlag = 0 and d.flag=0";
		List<AgentBo> lst = this.find(hql,new Object[]{agentId});
		if (lst!=null && lst.size()>0)
			return lst.get(0);
		else
			return null;
	}

	@Override
	public Double getSystemProfitMoney(Long serviceBillId)
			throws RuntimeException {
		String hql = "select sum(profitMoney) from ProfitBill where status=2 and id in (select a.id.profitBillId from ProfitServiceRelation a where a.id.serviceBillId=?)";
		List<Double> lst = this.find(hql,new Object[]{serviceBillId});
		if (lst!=null && lst.size()>0)
			return lst.get(0);
		else
			return null;
	}

	@Override
	public Double getSystemSettleMoney(Long serviceBillId)
			throws RuntimeException {
		String hql = "select sum(profitMoney) from SettleBill where status=2 and id in (select a.id.settleBillId from SettleServiceRelation a where a.id.serviceBillId=?)";
		List<Double> lst = this.find(hql,new Object[]{serviceBillId});
		if (lst!=null && lst.size()>0)
			return lst.get(0);
		else
			return null;
	}
	
	

}
