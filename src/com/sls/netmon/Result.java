package com.sls.netmon;

public class Result {

	private String icmpSeq;
	private String from;
	private String ttl;
	private String status;
	private String time;
	private String dateTime;
	private String type;
	private String description;

	public Result() {

	}

	public Result(String icmpSeq, String from, String ttl, String status, String dateTime, String type, String description) {
		this.icmpSeq = icmpSeq;
		this.from = from;
		this.ttl = ttl;
		this.status = status;
		this.dateTime = dateTime;
		this.type = type;
		this.description = description;
	}

	public String getIcmpSeq() {
		return icmpSeq;
	}

	public void setIcmpSeq(String icmpSeq) {
		this.icmpSeq = icmpSeq;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
