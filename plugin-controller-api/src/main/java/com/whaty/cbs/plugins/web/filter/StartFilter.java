package com.whaty.cbs.plugins.web.filter;

import com.whaty.cbs.core.frame.FilterController;

import javax.servlet.*;
import java.io.IOException;

public class StartFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        FilterController.getInstance().doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
    }

}
