package com.nangua.yhagent.job;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.nangua.yhagent.billing.bean.xml.Helper;
import com.nangua.yhagent.job.db.Command;
import com.nangua.yhagent.job.db.DemondBill;

@Component
public class DBTask implements IDBTask {

	@Scheduled(cron = "0/5 * *  * * ? ")
	// 每5秒执行一次
	public void deleteAllDoneCommand() {
		String sqlDel = "delete from  commands where status = :status and commandtype=4";
		SqlUpdate update = Ebean.createSqlUpdate(sqlDel);
		update.setParameter("status", "2");
		int modifiedCount = Ebean.execute(update);

	}
	public boolean insertCommand(String content){
		boolean result=false;
		try {
			String billNode=Helper.getNode(content,"DemondBill");
			DemondBill bill=Helper.genType(billNode, DemondBill.class);
			return insertCommand(bill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean insertCommand(DemondBill bill) {
		
		boolean flag = false;
		try {
			Ebean.beginTransaction();
			Command command=new Command();
//			 设置为传账类型
			command.setCommandtype("4");
			command.setMark(bill.getFilmname());
			command.setRoomnumber(bill.getRoomnumber());
			//设置状态为1
			command.setStatus("1");
			Ebean.save(bill);
			Ebean.save(command);
			Ebean.commitTransaction();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			Ebean.endTransaction();
		}
		return flag;
	}
	

}
