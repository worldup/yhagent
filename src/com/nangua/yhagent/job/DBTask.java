package com.nangua.yhagent.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component  
public class DBTask implements IDBTask{
   
	//   @Scheduled(cron="0/5 * *  * * ? ")   //ÿ5��ִ��һ��  
	    
	      public void myTest(){  
	            System.out.println("�������");  
	      }  
  

}
