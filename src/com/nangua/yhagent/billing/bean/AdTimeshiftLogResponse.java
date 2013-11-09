package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;

@XmlRootElement(name="Result")
public class AdTimeshiftLogResponse {
 public ResultInfo ResultInfo ;
 public Service Service ;
}
