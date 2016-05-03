package com.shivam.rest.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.rest.payments.businessservice.PaymentsBusinessService;
import com.shivam.rest.payments.model.Payment;
import com.shivam.rest.payments.model.PaymentList;
@RestController
@RequestMapping("/paymentsservice")
public class PaymentsServiceImpl {

	private PaymentsBusinessService paymentsbusinessservice;

	@Autowired
	public void setPaymentsbusinessservice(PaymentsBusinessService paymentsbusinessservice) {
		this.paymentsbusinessservice = paymentsbusinessservice;
	}


	@RequestMapping(value="/payment/{customerNumber}",method=RequestMethod.GET,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PaymentList> getPaymentByCustomerNumber(@PathVariable Integer customerNumber){

		PaymentList paymentList=new PaymentList();
		try{

			paymentList.setPayment(paymentsbusinessservice.getPaymentByCustomerNumber(customerNumber));

		}catch(Exception e){
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paymentList,HttpStatus.OK);
	}
	@RequestMapping(value="/payments",method=RequestMethod.GET,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PaymentList> getPayments(){
		PaymentList paymentList=new PaymentList();
		try{

			paymentList.setPayment(paymentsbusinessservice.getPayments());

		}catch(Exception e){
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paymentList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/payments/{payment}",method=RequestMethod.POST)
	public ResponseEntity createPayment(@RequestBody Payment payment){
		try{
			System.out.println(payment);
			paymentsbusinessservice.createPayment(payment);

		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/payments/{payment}",method=RequestMethod.PUT)
	public ResponseEntity updatePayment(@RequestBody Payment payment){
		try{

			paymentsbusinessservice.updatePayment(payment);

		}catch(Exception e){
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	@RequestMapping(value="/payments/{customerNumber}/{checkNumber}",method=RequestMethod.DELETE)
	public ResponseEntity deletePayment(@PathVariable Integer customerNumber,@PathVariable String checkNumber){
		try{

			paymentsbusinessservice.deletePayment(customerNumber,checkNumber);

		}catch(Exception e){
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	@RequestMapping(value="/payments/{customerNumber}",method=RequestMethod.DELETE)
	public ResponseEntity deleteAllPaymentsOfCustomer(@PathVariable Integer customerNumber){
		try{
			System.out.println("customerNumber"+customerNumber);
			if(customerNumber==null){
				 return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			paymentsbusinessservice.deleteAllPaymentsOfCustomer(customerNumber);

		}catch(Exception e){
			return new ResponseEntity<PaymentList>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
