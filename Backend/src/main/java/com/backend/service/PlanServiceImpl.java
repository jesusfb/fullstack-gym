package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Plan;
import com.backend.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService
{
    @Autowired
    PlanRepository planRepository;

    @Override
    public Plan savePlan(Plan plan)
    {
        return planRepository.save(new Plan(plan.getPlan_type(), plan.getPlan_desc(), plan.getPlan_duration(), plan.getPlan_price()));
    }

    @Override
    public List<Plan> getAllPlans()
    {
        return planRepository.findAll();
    }

    @Override
    public Plan getPlanById(int id)
    {
        Optional<Plan> plan = planRepository.findById(id);
        if(plan.isPresent())
        {
            return plan.get();
        }
        else
        {
            throw new ResourceNotFoundException("Plan","Id",id);
        }
    }

    @Override
    public Plan updatePlan(Plan plan, int id)
    {
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        existingPlan.setPlan_desc(plan.getPlan_desc());
        existingPlan.setPlan_duration(plan.getPlan_duration());
        existingPlan.setPlan_price(plan.getPlan_price());
        existingPlan.setPlan_type(plan.getPlan_type());
        planRepository.save(existingPlan);
        return existingPlan;
    }

    @Override
    public void deletePlan(int id)
    {
        planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        planRepository.deleteById(id);
    }
}