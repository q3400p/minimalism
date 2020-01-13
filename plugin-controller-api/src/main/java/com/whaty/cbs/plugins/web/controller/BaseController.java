package com.whaty.cbs.plugins.web.controller;

import com.whaty.cbs.plugins.web.response.JsonResponseReturn;
import com.whaty.cbs.plugins.web.response.ResponseReturn;
import com.whaty.cbs.plugins.web.session.Session;
import com.whaty.cbs.plugins.web.session.WebSession;
import com.windf.core.entity.BaseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制层的父类，用于提供api，基于spring MVC
 * @param <T>
 */
public abstract class BaseController<T extends BaseEntity> {

    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取ResponseReturn {@link ResponseReturn}
     * 用于返回数据
     * @return
     */
    public ResponseReturn getResponseReturn() {
        return new JsonResponseReturn();
    }

    /**
     * 获取session
     * @return
     */
    public Session getSession() {
        return new WebSession(this.getRequest().getSession());
    }
}
