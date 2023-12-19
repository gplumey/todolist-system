package org.gplumey.todolist.container.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.aop.ObservedAspect;
import org.aspectj.lang.ProceedingJoinPoint;

public class AbstractObserveAroundMethodHandler extends AbstractLogAspect
        implements ObservationHandler<ObservedAspect.ObservedAspectContext> {

    @Override
    public void onStart(ObservedAspect.ObservedAspectContext context) {
        /* we can get many information (including class, arguments...)
        form ProceedingJoinPoint. */
        ProceedingJoinPoint joinPoint = context.getProceedingJoinPoint();
        super.logBefore(joinPoint);
    }

    @Override
    public void onStop(ObservedAspect.ObservedAspectContext context) {
        ProceedingJoinPoint joinPoint = context.getProceedingJoinPoint();
        super.logAfter(joinPoint);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        /* required, otherwise the here will handle the
        non-spring bean method (e.g. handling http.server.requests)
        and throw a class cast exception. */
        return context instanceof ObservedAspect.ObservedAspectContext;
    }
}
