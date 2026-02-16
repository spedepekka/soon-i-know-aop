package fi.kranu.servicea.aop

import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class UserAuthenticationAspect {

    private val logger = LoggerFactory.getLogger(UserAuthenticationAspect::class.java)

    @Before("@annotation(fi.kranu.servicea.aop.CustomAuthentication)")
    fun authenticate() {
        val request = (RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes)?.request
        val userId = request?.getHeader("X-User-Id")

        if (userId.isNullOrBlank()) {
            logger.warn("Authentication failed: X-User-Id header is missing")
            throw SecurityException("User authentication required")
        } else if (!userId.equals("dirty-hack")) {
            // This is just a dirty hack for testing purposes. Don't do this in production.
            logger.warn("Wrong secret")
            throw SecurityException("User authentication required")
        }

        logger.info("User $userId authenticated successfully")
    }
}
