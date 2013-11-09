package com.nangua.yhagent.billing.bean;

import javax.xml.bind.annotation.XmlElement;

public class Program {
	private String programId;
	private String programName;
	private String programPrice;
	@XmlElement(name="ProgrameId")
	public String getProgramId() {
		return programId;
	}
	public Program setProgramId(String programId) {
		this.programId = programId;
		return this;
	}
	@XmlElement(name="ProgrameName")
	public String getProgramName() {
		return programName;
	}
	public Program setProgramName(String programName) {
		this.programName = programName;
		return this;
	}
	@XmlElement(name="ProgramePrice")
	public String getProgramPrice() {
		return programPrice;
	}
	public Program setProgramPrice(String programPrice) {
		this.programPrice = programPrice;
		return this;
	}
}
