package com.nangua.yhagent.job.db;

import java.util.List;

import com.avaje.ebean.Ebean;

public class EbeanTest {
	public static void main(String []args){
		testAllCommand();
	}
	public static void testAllCommand(){
	List<Command> commands=	Ebean.find(Command.class).where().eq("status", "1").findList();
	if(commands!=null&&commands.size()>0){
		for(Command command:commands){
			System.out.println(command);
		}
	}
	}
	
}
