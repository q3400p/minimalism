package com.whaty.cbs.plugins.web.frame;

import com.whaty.cbs.core.frame.FilterHandler;
import com.whaty.cbs.core.frame.SessionContext;
import com.whaty.cbs.plugins.web.session.WebSession;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SessionFilter implements FilterHandler {

    private ThreadLocal<WebSession> webSession = new ThreadLocal<WebSession>();

    @Override
    public List<String> getUrlPattern() {
        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        webSession.set(new WebSession(request.getSession()));
        SessionContext.start(webSession.get());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        SessionContext.end(webSession.get());
        webSession.set(null);
    }

    @Override
    public int getOrder() {
        return EARLIEST;
    }

}
