package com.backend.service;

import com.backend.model.Plan;
import java.util.List;

public interface PlanService
{
    Plan savePlan (Plan plan);
    List<Plan> getAllPlans();
    Plan getPlanById(int id);
    Plan updatePlan(Plan plan,int id);
    void deletePlan(int id);
}
