/*
 */
package cz.fai.blog.logging;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author František Špaček
 */
@LogAround
@Interceptor
public class LoggingInterceptor {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object logAndExecute(InvocationContext context) throws Exception {
        LOG.info("Execution of method {} started", context.getMethod().getName());
        final long start = System.currentTimeMillis();
        final Object result = context.proceed();
        LOG.debug("Execution of method {} finished ", context.getMethod().getName());
        LOG.debug("Execution of method {} took {}ms",
                context.getMethod().getName(), System.currentTimeMillis() - start);

        return result;
    }
}
