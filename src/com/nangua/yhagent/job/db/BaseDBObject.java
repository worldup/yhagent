package com.nangua.yhagent.job.db;

import com.avaje.ebean.Ebean;

public class BaseDBObject {
	 
	 public void save(){
		   Ebean.save(this);   
	   }
	   public void delete(){
		  Ebean.delete(this); 
	   }
	   public void delete(Object pk){
		 Ebean.delete(pk);   
	   }
	   public void update(){
		  Ebean.update(this); 
	   }
}
