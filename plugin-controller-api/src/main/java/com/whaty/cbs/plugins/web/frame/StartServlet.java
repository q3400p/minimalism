package com.whaty.cbs.plugins.web.frame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.whaty.cbs.core.frame.ProjectStart;

/**
 * 跟随项目启动的类的起点
 */
public class StartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StartServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        ProjectStart.getInstance().start();
    }

}
