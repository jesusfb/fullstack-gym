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
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("plans")
    public ResponseEntity<Plan> getPlanById( @RequestParam("plan_id") int plan_id)
    {
        return new ResponseEntity<>(planService.getPlanById(plan_id),HttpStatus.OK);
    }

    @PutMapping("plans/update")
    public ResponseEntity<Plan> updatePlan( @RequestParam("plan_id") int plan_id, @RequestBody Plan plan)
    {
        return new ResponseEntity<>(planService.updatePlan(plan,plan_id),HttpStatus.OK);
    }

    @DeleteMapping("plans/delete")
    public ResponseEntity<HttpStatus> deletePlanById(@RequestParam("plan_id") int plan_id)
    {
        planService.deletePlan(plan_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}