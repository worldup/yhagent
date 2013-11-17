package com.nangua.yhagent.job.db;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class EbeanTest {
	public static void main(String []args){
		Command command=new Command();
		command.setCommandstype("1111");
		command.save();
		command.delete();
		Ebean.find(Command.class).getTotalHits();
	}
}
