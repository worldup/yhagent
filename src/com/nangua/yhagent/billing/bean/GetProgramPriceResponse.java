package com.nangua.yhagent.billing.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nangua.yhagent.billing.bean.base.Service;

 
@XmlRootElement(name="Result")
public class GetProgramPriceResponse {
	private ResultInfo  resultInfo;
	private Service  service;
	private ProgramResponseResultData resultData;
	@XmlElement(name="ResultInfo")
	public ResultInfo getResultInfo() {
		return resultInfo;
	}
	public GetProgramPriceResponse setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
		return this;
	}
	@XmlElement(name="Service")
	public Service getService() {
		return service;
	}
	public GetProgramPriceResponse setService(Service service) {
		this.service = service;
		return this;
	}
	static class ProgramResponseResultData {

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

	 
	public GetProgramPriceResponse setResultData(ProgramResponseResultData resultData) {
		this.resultData=resultData;
		return this;
	}

}
