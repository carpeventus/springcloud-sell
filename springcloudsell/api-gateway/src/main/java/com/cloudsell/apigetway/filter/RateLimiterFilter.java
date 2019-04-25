package com.cloudsell.apigetway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author Haiyu
 * @date 2019/4/18 21:58
 */
@Component
public class RateLimiterFilter extends ZuulFilter {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 限流肯定是优先级最高的，因此设置比最高优先级（-3）还要小
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 没有拿到令牌
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RuntimeException();
        }
        return null;
    }
}
