package com.realdolmen.air.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "XHTMLFilter", urlPatterns = {"*.xhtml"})
public class XHTMLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ((HttpServletResponse)response).sendError(404);
        //chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
