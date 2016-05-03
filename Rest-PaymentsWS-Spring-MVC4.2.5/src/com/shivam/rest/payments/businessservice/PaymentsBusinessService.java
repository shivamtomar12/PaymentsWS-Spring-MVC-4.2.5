package com.shivam.rest.payments.businessservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.shivam.rest.payments.dao.PaymentsDAO;
import com.shivam.rest.payments.model.Payment;
@Service
public class PaymentsBusinessService {
	private PaymentsDAO paymentsDao;
	@Autowired
	public void setPaymentsDao(PaymentsDAO paymentsDao) {
		this.paymentsDao = paymentsDao;
	}
	
	public List<Payment> getPaymentByCustomerNumber(Integer customerNumber) throws Exception{
		
		List<Payment> list=new ArrayList<Payment>();
		
		try{
			list=paymentsDao.getPaymentByCustomerNumber(customerNumber);
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return list;
	}
	public List<Payment> getPayments() throws Exception{
		
		List<Payment> list=new ArrayList<Payment>();
		
		try{
			list=paymentsDao.getPayments();
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return list;
	}
	public boolean createPayment(Payment payment) throws Exception{
		boolean isInserted=false;
		try{
			isInserted=paymentsDao.createPayment(payment);
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return isInserted;
	}
	public boolean updatePayment(Payment payment)throws Exception{
		boolean isUpdated=false;
		try{
			isUpdated=paymentsDao.updatePayment(payment);
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return isUpdated;
	}
	public boolean deletePayment(Integer customerNumber,String checkNumber) throws Exception{
		boolean isDeleted=false;
		try{
			isDeleted=paymentsDao.deletePayment(customerNumber,checkNumber);
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return isDeleted;
	}
	public boolean deleteAllPaymentsOfCustomer(Integer customerNumber) throws Exception{
		boolean isDeleted=false;
		try{
			isDeleted=paymentsDao.deleteAllPaymentsOfCustomer(customerNumber);
		}catch(DataAccessException dae){
			throw new Exception(dae);
		}
		return isDeleted;
	}

}
