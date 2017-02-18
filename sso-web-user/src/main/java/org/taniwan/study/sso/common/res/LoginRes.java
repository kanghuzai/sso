package org.taniwan.study.sso.common.res;

import java.util.List;

public class LoginRes {

	private String ssoToken;
	private List<String> ssoUrls;

	public String getSsoToken() {
		return ssoToken;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}

	public List<String> getSsoUrls() {
		return ssoUrls;
	}

	public void setSsoUrls(List<String> ssoUrls) {
		this.ssoUrls = ssoUrls;
	}

	@Override
	public String toString() {
		return "LoginRes [ssoToken=" + ssoToken + ", ssoUrls=" + ssoUrls + "]";
	}

}
