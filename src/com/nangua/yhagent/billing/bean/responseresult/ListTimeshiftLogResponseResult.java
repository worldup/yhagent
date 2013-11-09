package com.nangua.yhagent.billing.bean.responseresult;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.nangua.yhagent.billing.bean.Occupancy;
import com.nangua.yhagent.billing.bean.base.ResponseResult;

public class ListTimeshiftLogResponseResult extends ResponseResult{
	@XmlElementWrapper(name="Occupancies")
	@XmlElement(name="Occupancy")
	public List<Occupancy> Occupancies;
}
