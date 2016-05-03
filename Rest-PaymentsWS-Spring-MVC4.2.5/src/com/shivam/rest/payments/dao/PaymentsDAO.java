package com.shivam.rest.payments.dao;

import java.text.ParseException;
import java.util.List;

import com.shivam.rest.payments.model.Payment;

public interface PaymentsDAO {
public List<Payment> getPaymentByCustomerNumber(Integer customerNumber);
public List<Payment> getPayments();
public boolean createPayment(Payment payment) throws ParseException;
public boolean updatePayment(Payment payment);
public boolean deletePayment(Integer customerNumber,String checkNumber);
public boolean deleteAllPaymentsOfCustomer(Integer customerNumber);
}
