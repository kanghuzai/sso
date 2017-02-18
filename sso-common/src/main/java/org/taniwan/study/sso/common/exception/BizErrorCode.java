package org.taniwan.study.sso.common.exception;

import java.io.Serializable;

/**
 *  业务系统模块实现该异常码接口，定义好全局唯一的system和model，用来标识自己的身份<br/>
 *  system model code 三个组合是全系统唯一的。<br/>
 *  建议独立对外服务的系统，在启动时做检查，保障依赖的其它系统与自己的省份标识不冲突。<br/>
 *  这样对前端和第三方系统来说，可以根据你的业务码做相应的处理动作。<br/>
 * @author Chao Zhang
 * @date 2017/1/24-11:33
 */
public interface BizErrorCode extends Serializable {

    /**
     * 获取系统标识
     * @return
     */
    String getSystem();

    /**
     * 设置系统标识
     * @param system
     */
    void setSystem(String system);

    /**
     * 获取模块标识
     * @return
     */
    String getModel();

    /**
     * 设置模块标识
     * @param model
     */
    void setModel(String model);

    /**
     * 获取业务码标识
     * @return
     */
    String getCode();

    /**
     * 设置业务码标识
     * @param code
     */
    void setCode(String code);

    /**
     * 获取业务码描述
     * @return
     */
    String getName();

    /**
     * 设置业务码描述
     * @param name
     */
    void setName(String name);

    /**
     * 格式化name的${}占位符
     * @param dynamicText 动态描述语
     * @return
     */
    BizErrorCode format(String[] dynamicText);

    /**
     *  保障能打印出详细的errorCode信息，必须覆写该方法
     * @return
     */
    @Override
    String toString();

}
