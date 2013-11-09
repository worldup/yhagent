package com.nangua.yhagent.billing.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;
import com.nangua.yhagent.billing.bean.xml.Helper;

@XmlRootElement(name = "Result")
public class GetProgramListResponse {
	private ResultInfo resultInfo;

	private Service service;

	private ProgramResponseResultData resultData;

	static class ProgramResponseResultData  {

		private List<Program> programs;

		@XmlElement(name = "programs")
		public List<Program> getPrograms() {
			return programs;
		}

		public ProgramResponseResultData setPrograms(List<Program> programs) {
			this.programs = programs;
			return this;
		}

	}

	@XmlElement(name = "ResultData")
	public ProgramResponseResultData getResultData() {
		return resultData;
	}

	public GetProgramListResponse setResultData(ProgramResponseResultData resultData) {
		this.resultData = resultData;
		return this;
	}

	@XmlElement(name = "ResultInfo")
	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public GetProgramListResponse setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
		return this;
	}

	@XmlElement(name = "Service")
	public Service getService() {
		return service;
	}

	public GetProgramListResponse setService(Service service) {
		this.service = service;
		return this;
	}
	public static void main(String []args){
		GetProgramListResponse response=	new GetProgramListResponse();
		GetProgramListResponse.ProgramResponseResultData data= new GetProgramListResponse.ProgramResponseResultData();
		List<Program > list=new ArrayList();
		list.add(new Program().setProgramId("111").setProgramName("222").setProgramPrice("333"));
		response.setResultInfo(new ResultInfo().setErrormessage("cuole").setResultcode("201")).setService(new Service().setBusiness("111").setFunction("222")).setResultData(data.setPrograms(list));
		String xmlStr=Helper.genXmlStr(response);
		System.out.println(xmlStr);
	}
}
