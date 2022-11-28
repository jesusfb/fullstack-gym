package com.backend.controller;
import com.backend.model.Plan;
import com.backend.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController
{
    private final PlanService planService;

    public PlanController(PlanService planService)
    {
        super();
        this.planService = planService;
    }

    // http://localhost:8080/plans/add  POST METHOD + JSON BODY
    @PostMapping("/add")
    public ResponseEntity<Plan> savePlan(@RequestBody Plan plan)
    {
        return new ResponseEntity<>(planService.savePlan(plan), HttpStatus.CREATED);
    }

    // http://localhost:8080/plans/all GET  METHOD
    @GetMapping("/all")
    public ResponseEntity<List<Plan>> getAllPlans()
    {
        return new ResponseEntity<>(planService.getAllPlans(),HttpStatus.OK);
    }

    // http://localhost:8080/plans/id/1 GET METHOD
    @GetMapping("/id/{id}")
    public ResponseEntity<Plan> getPlanById( @PathVariable("id") int planId)
    {
        return new ResponseEntity<>(planService.getPlanById(planId),HttpStatus.OK);
    }

    // http://localhost:8080/plans/update/id/1 PUT METH0D + JSON BODY
    @PutMapping("/update/id/{id}")
    public ResponseEntity<Plan> updatePlan( @PathVariable("id") int planId, @RequestBody Plan plan)
    {
        return new ResponseEntity<>(planService.updatePlan(plan,planId),HttpStatus.OK);
    }

    // http://localhost:8080/plans/delete/id/1 DELETE METHOD
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> deletePlanById(@PathVariable("id") int planId)
    {
        planService.deletePlan(planId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}