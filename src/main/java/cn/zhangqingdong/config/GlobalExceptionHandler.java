package cn.zhangqingdong.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;


/**
 * @author qingdong.zhang
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { IOException.class , RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(Exception exception, WebRequest request) {
        logger.info("Catch an exception", exception);
        return  new ModelAndView("500");
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView noMapping(Exception exception, WebRequest request) {
        logger.info("No mapping exception", exception);
        return  new ModelAndView("404");
    }

    @ExceptionHandler(value = {MultipartException.class})
    public ModelAndView fileError(Exception exception, WebRequest request){
        logger.error("上传文件异常，信息:{}",exception.getMessage());
        System.out.println(exception.getMessage());
        return  new ModelAndView("/error");
    }
}

