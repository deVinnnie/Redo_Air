package com.realdolmen.air.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A WebFilter to deny access to the .xhtml pages.
 *
 * All urls which match "*.xhtml" are redirected to a '404 Not Found' page.
 *
 */
@WebFilter(filterName = "XHTMLFilter", urlPatterns = {"*.xhtml"})
public class XHTMLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ((HttpServletResponse)response).sendError(404);
    }

    @Override
    public void destroy() {
    }
}
