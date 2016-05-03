package com.shivam.rest.payments.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.shivam.rest.payments.model.Payment;
@Component
public class PaymentsDaoImpl implements PaymentsDAO {

	NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc= new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Override
	public List<Payment> getPaymentByCustomerNumber(Integer customerNumber) {
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("customerNumber", customerNumber);
		return jdbc.query(SQL.GETPAYMENTSBYCUSTOMER,params, new RowMapper<Payment>() {

			@Override
			public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Payment payment=new Payment();
				payment.setCustomerNumber(rs.getInt("customerNumber"));
				payment.setCheckNumber(rs.getString("checkNumber"));
				
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
				payment.setPaymentDate(sdf.format(rs.getDate("paymentDate")));
				
				payment.setAmount(rs.getDouble("amount"));
				return payment;
			}
		});
	}

	@Override
	public List<Payment> getPayments() {
		return jdbc.query(SQL.GETPAYMENTS, new RowMapper<Payment>() {

			@Override
			public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Payment payment=new Payment();
				payment.setCustomerNumber(rs.getInt("customerNumber"));
				payment.setCheckNumber(rs.getString("checkNumber"));
				
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
				payment.setPaymentDate(sdf.format(rs.getDate("paymentDate")));
				
				payment.setAmount(rs.getDouble("amount"));
				return payment;
			}
		});
	}

	@Override
	public boolean createPayment(Payment payment) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date date=new Date(sdf.parse(payment.getPaymentDate()).getTime());
		payment.setPaymentDate(date.toString());
		BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(payment);
		return jdbc.update(SQL.CREATEPAYMENT,params)==1;
	}

	@Override
	public boolean updatePayment(Payment payment) {
		return jdbc.getJdbcOperations().update(SQL.UPDATEPAYMENTS, payment.getAmount(),payment.getCustomerNumber(),payment.getCheckNumber())==1;
	}

	@Override
	public boolean deletePayment(Integer customerNumber, String checkNumber) {
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("customerNumber", customerNumber);
		params.addValue("checkNumber", checkNumber);
		return jdbc.update(SQL.DELETEPAYMENTS, params)==1;
	}

	@Override
	public boolean deleteAllPaymentsOfCustomer(Integer customerNumber) {
		MapSqlParameterSource params=new MapSqlParameterSource();
		params.addValue("customerNumber", customerNumber);
		return jdbc.update(SQL.DELETEPAYMENTSBYCUSTOMER, params)==1;
	}

}
