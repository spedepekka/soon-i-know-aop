package fi.kranu.serviceb

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*

@Component
class CorrelationIdFilter : OncePerRequestFilter() {
    private val logger = LoggerFactory.getLogger(CorrelationIdFilter::class.java)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var correlationId = request.getHeader(HEADER_NAME)

        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString()
            logger.debug("Generated new correlation ID $correlationId")
        } else {
            logger.debug("Using existing correlation ID $correlationId")
        }

        MDC.put("correlationId", correlationId)
        response.setHeader(HEADER_NAME, correlationId)

        try {
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }

    companion object {
        private const val HEADER_NAME = "X-Correlation-Id"
    }
}
