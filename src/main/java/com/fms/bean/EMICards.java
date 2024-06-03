package com.fms.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EMICards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userCardId;

	private String cardType; // GOLD or TITANIUM

	@Column(nullable = false, name = "`limit`")
	private Double limit = 0.0;

	@Column(length = 16)
	private Long cardNumber;
	private Double balance;
	private String month;
	private String year;

	@Column(length = 3)
	private int cvv;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
	private User user;

	public EMICards() {
		super();
	}

	public EMICards(Integer userCardId, String cardType, Double limit, Long cardNumber, Double balance, String month,
			String year, int cvv, User user) {
		super();
		this.userCardId = userCardId;
		this.cardType = cardType;
		this.limit = limit;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.month = month;
		this.year = year;
		this.cvv = cvv;
		this.user = user;
	}

	public Integer getUserCardId() {
		return userCardId;
	}

	public void setUserCardId(Integer userCardId) {
		this.userCardId = userCardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "EMICards [userCardId=" + userCardId + ", cardType=" + cardType + ", limit=" + limit + ", cardNumber="
				+ cardNumber + ", balance=" + balance + ", month=" + month + ", year=" + year + ", cvv=" + cvv
				+ ", user=" + user + "]";
	}

}
