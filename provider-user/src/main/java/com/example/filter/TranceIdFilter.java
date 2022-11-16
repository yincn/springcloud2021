package com.example.filter;

import com.example.constant.LogConstant;
import com.example.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Yincn
 * @Date 2022/3/18
 */
@Component
public class TranceIdFilter implements Filter {

    Logger log = LoggerFactory.getLogger(TranceIdFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        log.info(url);
        String traceId = Optional.ofNullable(request.getHeader(LogConstant.TRACE_ID)).orElse(LogUtil.getUUID());
        MDC.put(LogConstant.TRACE_ID, traceId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
