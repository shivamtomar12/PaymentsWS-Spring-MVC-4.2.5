package com.shivam.rest.payments.dao;

public class SQL {
	public static final String GETPAYMENTSBYCUSTOMER = "select * from payments p where p.customerNumber = :customerNumber";
	public static final String GETPAYMENTS = "select * from payments";
	public static final String CREATEPAYMENT = "insert into payments (customerNumber,checkNumber,paymentDate,amount) values (:customerNumber,:checkNumber,:paymentDate,:amount)";
	public static final String UPDATEPAYMENTS = "update payments set amount = ? where customerNumber=? and checkNumber = ?";
	public static final String DELETEPAYMENTS = "delete from payments where customerNumber = :customerNumber and checkNumber = :checkNumber";
	public static final String DELETEPAYMENTSBYCUSTOMER = "delete from payments where customerNumber = :customerNumber";
	

}
