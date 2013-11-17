package com.nangua.yhagent.job.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

@Entity
@Table(name="command")
public class Command extends BaseDBObject{
	@Id
	int commandsid;

	String commandstype;

	String roomnumber;

	String status;

	String mark;
	
	public int getCommandsid() {
		return commandsid;
	}

	public void setCommandsid(int commandsid) {
		this.commandsid = commandsid;
	}

	public String getCommandstype() {
		return commandstype;
	}

	public void setCommandstype(String commandstype) {
		this.commandstype = commandstype;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
  
	
}
