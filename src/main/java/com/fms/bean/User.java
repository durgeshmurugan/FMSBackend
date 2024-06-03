package com.fms.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String fullname;
	private String username;
	private String password;
	private String occupation;
	private Double salary;
	private Double expenses;
	private String email;
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public User() {
		super();
	}

	public User(Integer userId, String fullname, String username, String password, String occupation, Double salary,
			Double expenses, String email, String phoneNumber, Admin admin) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.occupation = occupation;
		this.salary = salary;
		this.expenses = expenses;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.admin = admin;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullname=" + fullname + ", username=" + username + ", password=" + password
				+ ", occupation=" + occupation + ", salary=" + salary + ", expenses=" + expenses + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", admin=" + admin + "]";
	}

}
