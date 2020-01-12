package com.whaty.cbs.plugins.web;

import com.whaty.cbs.plugins.web.request.RequestParamenter;
import com.whaty.cbs.plugins.web.request.RequestPath;
import com.whaty.cbs.plugins.web.response.ResponseReturn;
import com.whaty.cbs.plugins.web.response.ResponseReturnFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制层的父类
 *
 * @author 陈亚峰
 */
public abstract class BaseControler {

    public BaseControler() {
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public RequestParamenter getParamenter() {
        return new RequestParamenter(getRequest());
    }

    public RequestPath getPath() {
        return new RequestPath(getRequest(), this);
    }

    public ResponseReturn getResponseReturn() {
        return ResponseReturnFactory.getResponseReturn(getPath().getSuffix(), this);
    }
}
