package com.backend.controller;
import com.backend.model.Plan;
import com.backend.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanController
{
    @Autowired
    PlanService planService;

    @PostMapping("plans/add")
    public ResponseEntity<Plan> savePlan(@RequestBody Plan plan)
    {
        return new ResponseEntity<>(planService.savePlan(plan), HttpStatus.CREATED);
    }

    @GetMapping("plans/all")
    public ResponseEntity<List<Plan>> getAllPlans()
    {
        return new ResponseEntity<>(planService.getAllPlans(),HttpStatus.OK);
    }

    @GetMapping("plans/id/{id}")
    public ResponseEntity<Plan> getPlanById( @PathVariable("id") int planId)
    {
        return new ResponseEntity<>(planService.getPlanById(planId),HttpStatus.OK);
    }

    @PutMapping("plans/update/id/{id}")
    public ResponseEntity<Plan> updatePlan( @PathVariable("id") int planId, @RequestBody Plan plan)
    {
        return new ResponseEntity<>(planService.updatePlan(plan,planId),HttpStatus.OK);
    }

    @DeleteMapping("plans/delete/id/{id}")
    public ResponseEntity<HttpStatus> deletePlanById(@PathVariable("id") int planId)
    {
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}