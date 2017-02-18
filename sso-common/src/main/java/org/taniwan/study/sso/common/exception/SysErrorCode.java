package org.taniwan.study.sso.common.exception;

/**
 * 业务系统模块继承该异常码基类，定义好全局唯一的system和model，用来标识自己的身份
 * @author Chao Zhang
 * @date 2017/1/23-16:52
 */
public class SysErrorCode implements BizErrorCode {

    private static final long serialVersionUID = -5306992673686088813L;

    /**
     * 操作成功
     */
    public static final SysErrorCode SUCCESS = new SysErrorCode("1", "操作成功");
    /**
     * 操作失败
     */
    public static final SysErrorCode FAIL = new SysErrorCode("2", "操作失败");
    /**
     * 接口参数错误
     */
    public static final SysErrorCode PARAM_ERROR = new SysErrorCode("3", "接口参数错误");
    /**
     * 系统授权过期
     */
    public static final SysErrorCode PERMISSION_EXPIRED = new SysErrorCode("4", "系统授权过期");
    /**
     * 接口权限不足
     */
    public static final SysErrorCode PERMISSION_DENIED = new SysErrorCode("5", "操作权限不足");
    /**
     * 系统数据异常
     */
    public static final SysErrorCode DATE_NULL = new SysErrorCode("6", "系统数据异常");
    /**
     * 服务器内部错误
     */
    public static final SysErrorCode INSIDE_ERROR = new SysErrorCode("7", "服务器内部错误");

    private String system = "common";
    private String model = "base";
    private String code;
    private String name;

    // 一定要声明无参构造，否则dubbo序列化会报错。
    public SysErrorCode(){}

    public SysErrorCode(String code, String name){
        this.code = code;
        this.name = name;
    }

    @Override
    public String getSystem() {
        return system;
    }

    @Override
    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BizErrorCode format(String[] dynamicText){
        this.setName(String.format(this.getName(), dynamicText));
        return this;
    }

    @Override
    public String toString() {
        return "SysBaseErrorCode{" +
                "system='" + system + '\'' +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
