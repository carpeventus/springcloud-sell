//package com.cloudsell.apigetway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Haiyu
// * @date 2019/4/18 20:59
// */
//@Component
//public class TokenFilter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        // 越小顺序约靠前，-1 就在下面ORDER之后
//        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//
//        HttpServletRequest request = requestContext.getRequest();
//        // 这里从url参数中取参数，也可以从Cookie，Header中取
//        String token = request.getParameter("token");
//        if (StringUtils.isEmpty(token)) {
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        }
//        return null;
//    }
//}
