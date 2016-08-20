package com.plancancer.plancancernews;

import java.util.List;

/**
 * Created by Yazid on 11/05/2016.
 */
public interface PlanCancerServiceConsumer {
    public void consumeService(Object result);
    public void consumeServiceMultiple(List<Object> result);

}
