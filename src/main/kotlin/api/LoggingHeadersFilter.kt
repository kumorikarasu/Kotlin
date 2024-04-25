package api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.micronaut.core.order.Ordered
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.RequestFilter
import io.micronaut.http.annotation.ServerFilter
import io.micronaut.http.filter.ServerFilterPhase
import io.micronaut.http.util.HttpHeadersUtil
import io.micronaut.http.annotation.Filter.MATCH_ALL_PATTERN

@ServerFilter(MATCH_ALL_PATTERN) 
class LoggingHeadersFilter: Ordered {

    companion object {
        @JvmField val LOG = LoggerFactory.getLogger(LoggingHeadersFilter::class.java)
    }

    @RequestFilter 
    fun filterRequest(request: HttpRequest<kotlin.Any>) {
        HttpHeadersUtil.trace(LOG, request.getHeaders());
    }

    @Override
    public override fun getOrder() : Int { 
        return ServerFilterPhase.FIRST.order();
    }
}
