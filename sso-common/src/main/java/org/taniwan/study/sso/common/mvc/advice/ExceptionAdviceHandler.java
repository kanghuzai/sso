package org.taniwan.study.sso.common.mvc.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.taniwan.study.sso.common.exception.BizException;
import org.taniwan.study.sso.common.exception.SysErrorCode;
import org.taniwan.study.sso.common.mvc.bean.ResBody;

import javax.servlet.http.HttpServletRequest;

/**
 * spring mvc全局异常处理器
 * @author Chao Zhang
 * @date 2017/1/23-16:52
 */
@ControllerAdvice
public class ExceptionAdviceHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdviceHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Throwable e) {
        boolean isAjaxReq = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) ? true : false;
//        if(!isAjaxReq) {
//            ModelAndView mav = new ModelAndView("/error.jsp");
//            if (e instanceof BizException) {
//                mav.addObject("errorMsg:", ((BizException) e).getErrorCode());
//                return mav;
//            }
//            mav.addObject(SysErrorCode.FAIL.toString());
//            return mav;
//        }
//        else{
            if (e instanceof BizException) {
                BizException be = (BizException) e;
                log.info("system: " + be.getErrorCode().getSystem() + ", model: " + be.getErrorCode().getModel()
                        + ", code: " + be.getErrorCode().getCode() + ", msg: " + be.getErrorCode().getName(), e);
                return ResBody.buildCustomResBody(be.getErrorCode());
            }
            log.error(e.getMessage(), e);
            return ResBody.buildFailResBody();
//        }
    }
}
