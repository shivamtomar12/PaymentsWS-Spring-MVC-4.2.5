package com.shivam.rest.payments.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="payment")
public class Payment {
private Integer customerNumber;
private String checkNumber;
private String paymentDate;
private double amount;
public Integer getCustomerNumber() {
	return customerNumber;
}
public void setCustomerNumber(Integer customerNumber) {
	this.customerNumber = customerNumber;
}
public String getCheckNumber() {
	return checkNumber;
}
public void setCheckNumber(String checkNumber) {
	this.checkNumber = checkNumber;
}
public String getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(String paymentDate) {
	this.paymentDate = paymentDate;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "Payment [customerNumber=" + customerNumber + ", checkNumber=" + checkNumber + ", paymentDate=" + paymentDate
			+ ", amount=" + amount + "]";
}
}
