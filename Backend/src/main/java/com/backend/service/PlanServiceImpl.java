package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Image;
import com.backend.model.Plan;
import com.backend.repository.ImageRepository;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import com.backend.tool.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService
{
    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

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
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        return existingPlan;
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
        if(!userRepository.findByPlan_Id(id).isEmpty())
        {
            throw new RuntimeException("Plan with ID " + id + "can not be deleted. User or users are subscribed to it");
        }
        planRepository.deleteById(id);
    }

    @Override
    public Plan uploadImage(MultipartFile file, int id) throws IOException
    {
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        if(!existingPlan.getImage_url().isEmpty())
        {
            existingPlan = deleteImage(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingPlan.setImage_url(file.getOriginalFilename());
        planRepository.save(existingPlan);
        return existingPlan;
    }

    @Override
    public Plan deleteImage(int id)
    {
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        String tobedeletedImageName = existingPlan.getImage_url();
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has mot been found"));
        imageRepository.delete(tobedeletedImage);
        existingPlan.setImage_url("");
        return planRepository.save(existingPlan);
    }
}