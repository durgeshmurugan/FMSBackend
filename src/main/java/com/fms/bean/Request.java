package com.fms.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requestId;
	private String status;

	private boolean isApplied;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private User user;

	public Request() {
		super();
	}

	public Request(Integer requestId, String status, boolean isApplied, User user) {
		super();
		this.requestId = requestId;
		this.status = status;
		this.isApplied = isApplied;
		this.user = user;
	}

	/**
	 * @return the isApplied
	 */
	public boolean isApplied() {
		return isApplied;
	}

	/**
	 * @param isApplied the isApplied to set
	 */
	public void setApplied(boolean isApplied) {
		this.isApplied = isApplied;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", status=" + status + ", isApplied=" + isApplied + ", user=" + user
				+ "]";
	}

}
