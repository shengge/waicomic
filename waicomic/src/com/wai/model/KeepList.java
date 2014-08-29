package com.wai.model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

@Table(name="keep_list")
public class KeepList {
	public KeepList() {}

	public KeepList(Integer pmk, Integer rid, Integer rmk, Integer sid, Integer smk, Integer sta,Integer price) {
		this.pmk = pmk;
		this.rid = rid;
		this.rmk = rmk;
		this.sid = sid;
		this.smk = smk;
		this.sta = sta;
		this.price = price;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPmk() {
		return pmk;
	}

	public void setPmk(Integer pmk) {
		this.pmk = pmk;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getRmk() {
		return rmk;
	}

	public void setRmk(Integer rmk) {
		this.rmk = rmk;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSmk() {
		return smk;
	}

	public void setSmk(Integer smk) {
		this.smk = smk;
	}

	public Integer getSta() {
		return sta;
	}

	public void setSta(Integer sta) {
		this.sta = sta;
	}
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	@Id(column="pid")
	private Integer pid;
	private Integer pmk;
	private Integer rid;
	private Integer rmk;
	private Integer sid;
	private Integer smk;
	private Integer sta;
	private Integer price;
	
}