package com.nangua.yhagent.job.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="commands")
public class Command  {
	@Id
	int commandid;

	String commandtype;

	String roomnumber;

	String status;

	String mark;

	public int getCommandid() {
		return commandid;
	}

	public void setCommandid(int commandid) {
		this.commandid = commandid;
	}

	public String getCommandtype() {
		return commandtype;
	}

	public void setCommandtype(String commandtype) {
		this.commandtype = commandtype;
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
	
	@Override 
	public String toString(){
		return "commandid->"+commandid+" commandtype->"+commandtype+" mark->"+mark+" status->"+status+" ->roomnumber"+roomnumber;
	}
	
}
