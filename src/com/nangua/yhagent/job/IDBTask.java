package com.nangua.yhagent.job;

import com.nangua.yhagent.job.db.DemondBill;

public interface IDBTask {
	  public void deleteAllDoneCommand();  
	  public boolean insertCommand(String content);
	  public boolean insertCommand(DemondBill bill);
}
