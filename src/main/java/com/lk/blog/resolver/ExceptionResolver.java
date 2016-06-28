package com.lk.blog.resolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lk.blog.exception.BusinessException;
import com.lk.blog.exception.ParameterException;
import com.lk.blog.vo.AjaxResult;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = Logger.getLogger(ExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (request.getHeader("accept").indexOf("application/json") > -1 && request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) {
            logger.error("ajax error", ex);
            return this.doAjaxResolverException(request, response, ex);
        }
        return super.doResolveException(request, response, handler, ex);
    }

    private ModelAndView doAjaxResolverException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setSuccess(false);
            if (ex instanceof DataAccessException) {
                ajaxResult.setMessage("DataAccessException");
            } else if (ex instanceof ParameterException) {
                ajaxResult.setMessage("ParameterException");
            } else if (ex instanceof BusinessException) {
                ajaxResult.setMessage("BusinessException");
            } else {
                ajaxResult.setMessage(ex.getMessage());
            }
            Gson gson = new Gson();
            out.write(gson.toJson(ajaxResult));
        } catch (IOException e) {
            logger.error("write reponse error", e);
            return null;
        }
        return null;
    }
}
