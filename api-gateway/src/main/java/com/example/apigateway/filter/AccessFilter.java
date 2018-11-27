package com.example.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: api-gateway
 * @Package: com.example.apigateway.filter
 * @ClassName: AccessFilter
 * @Description: java类作用描述
 * @Author: baofei.
 * @CreateDate: 2018/11/26 18:41
 * @UpdateUser:
 * @UpdateDate: 2018/11/26 18:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */

@Slf4j
public class AccessFilter extends ZuulFilter{

    public AccessFilter() {
        super();
    }

    @Override
    public boolean isStaticFilter() {
        return super.isStaticFilter();
    }

    @Override
    public String disablePropertyName() {
        return super.disablePropertyName();
    }

    @Override
    public boolean isFilterDisabled() {
        return super.isFilterDisabled();
    }

    @Override
    public ZuulFilterResult runFilter() {
        return super.runFilter();
    }

    @Override
    public int compareTo(ZuulFilter filter) {
        return super.compareTo(filter);
    }

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}",request.getMethod(),request.getRequestURI());
        Object aaccessToken = request.getParameter("aaccessToken");
        if(null == aaccessToken){
            log.warn("aaccessToken is null");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        log.info("aaccessToken is ok");
        return null;
    }


}
