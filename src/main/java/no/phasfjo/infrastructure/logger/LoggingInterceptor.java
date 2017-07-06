package no.phasfjo.infrastructure.logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * Created by phasf on 31.10.2016.
 */
@Interceptor
@Loggable
public class LoggingInterceptor {


    @Inject
    private Logger logger;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            return ic.proceed();
        }finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }
}
