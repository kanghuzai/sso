package org.taniwan.study.sso.common.mvc.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring mvc全局数据绑定增强处理器
 * @author Chao Zhang
 * @date 2017/1/23-17:36
 */
@ControllerAdvice
public class DataBinderAdviceHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdviceHandler.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
