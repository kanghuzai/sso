package org.taniwan.study.sso.common.mvc.bean;

import java.util.ArrayList;

import org.taniwan.study.sso.common.exception.BizErrorCode;
import org.taniwan.study.sso.common.exception.SysErrorCode;

public class ResBody {

	private String system;
	private String model;
	private String code;
	private String msg;
	private PageRes page;
	private Object data;

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PageRes getPage() {
		return page;
	}

	public void setPage(PageRes page) {
		this.page = page;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 构建成功报文头
	 * @return
	 */
	public  static ResBody buildSucResBody(){
		ResBody res = new ResBody();
		res.setCode(SysErrorCode.SUCCESS.getSystem() + "-" + SysErrorCode.SUCCESS.getModel() + "-" + SysErrorCode.SUCCESS.getCode());
		res.setMsg(SysErrorCode.SUCCESS.getName());
		return res;
	}
	
	public  static ResBody buildSucEmptyResBody(){
		ResBody res = new ResBody();
		PageRes pageRes = new PageRes();
		pageRes.setAllRecordSize(0);
		pageRes.setPageRecodeSize(0);
		res.setPage(pageRes);
		res.setData(new ArrayList<>());
		res.setCode(SysErrorCode.SUCCESS.getSystem() + "-" + SysErrorCode.SUCCESS.getModel() + "-" + SysErrorCode.SUCCESS.getCode());
		res.setMsg(SysErrorCode.SUCCESS.getName());
		return res;
	}
	
	public  static ResBody buildSucResBody(Object data){
		ResBody res = new ResBody();
		res.setCode(SysErrorCode.SUCCESS.getSystem() + "-" + SysErrorCode.SUCCESS.getModel() + "-" + SysErrorCode.SUCCESS.getCode());
		res.setMsg(SysErrorCode.SUCCESS.getName());
		res.setData(data);
		return res;
	}
	
	public  static ResBody buildSucResBody(Object data, PageRes page){
		ResBody res = new ResBody();
		res.setCode(SysErrorCode.SUCCESS.getSystem() + "-" + SysErrorCode.SUCCESS.getModel() + "-" + SysErrorCode.SUCCESS.getCode());
		res.setMsg(SysErrorCode.SUCCESS.getName());
		res.setData(data);
		res.setPage(page);
		return res;
	}
	
	/**
	 * 构建失败报文头
	 * @return
	 */
	public static ResBody buildFailResBody(){
		ResBody res = new ResBody();
		res.setCode(SysErrorCode.FAIL.getSystem() + "-" + SysErrorCode.FAIL.getModel() + "-" + SysErrorCode.FAIL.getCode());
		res.setMsg(SysErrorCode.FAIL.getName());
		return res;
	}
	
	/**
	 * 构建自定义报文头
	 * @param errorCode
	 * @return
	 */
	public static ResBody buildCustomResBody(BizErrorCode errorCode){
		ResBody res = new ResBody();
		res.setCode(errorCode.getSystem() + "-" + errorCode.getModel() + "-" + errorCode.getCode());
		res.setMsg(errorCode.getName());
		return res;
	}

	@Override
	public String toString() {
		return "ResBody{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", page=" + page +
				", data=" + data +
				'}';
	}
}
