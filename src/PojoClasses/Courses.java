package PojoClasses;

import java.util.*;

public class Courses {
	
	// WebAutomation is an array so we have tell our WebAutomation POJO class that there could be an array by List<WebAutomation>
	private List<WebAutomation> webAutomation ;
	private List<API> api ;
	private List<Mobile> mobile ;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<API> getApi() {
		return api;
	}
	public void setApi(List<API> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

}