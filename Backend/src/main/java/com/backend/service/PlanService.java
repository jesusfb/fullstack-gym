package com.backend.service;

import com.backend.model.Plan;
import com.backend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlanService
{
    Plan savePlan (Plan plan);
    List<Plan> getAllPlans();
    Plan getPlanById(int id);
    Plan updatePlan(Plan plan,int id);
    void deletePlan(int id);
    Plan uploadImage(MultipartFile file, int id) throws IOException;

    Plan deleteImage(int id);
}
