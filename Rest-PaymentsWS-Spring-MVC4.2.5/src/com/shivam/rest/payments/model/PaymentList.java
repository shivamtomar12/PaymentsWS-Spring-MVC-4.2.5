package com.shivam.rest.payments.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="payments")
public class PaymentList {
private List<Payment> payment;

public List<Payment> getPayment() {
	return payment;
}

public void setPayment(List<Payment> payment) {
	this.payment = payment;
}

}
