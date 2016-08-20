package com.plancancer.plancancernews.service;

import com.plancancer.plancancernews.persistance.model.PlanCancerComment;

import java.util.List;

/**
 * Created by Yazid on 10/05/2016.
 */
public interface IServicesAccessController {
    public List<PlanCancerComment> getUserComments();
}
