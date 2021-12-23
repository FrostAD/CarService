package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import com.example.carservice.services.QualificationService;
import com.example.carservice.web.view.model.qualification.CreateQualificationViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import com.example.carservice.web.view.model.qualification.UpdateQualificationViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/qualifications")
public class QualificationController {
    private final QualificationService qualificationService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String getQualifications(Model model){
        model.addAttribute("qualifications",qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        return "/qualifications/qualifications";
    }

    @GetMapping("/create-qualification")
    public String showQualificationCreateForm(Model model){
        model.addAttribute("qualification",new CreateQualificationViewModel());
        return "/qualifications/create-qualification";
    }
    @PostMapping("/create")
    public String createQualification(@Valid @ModelAttribute("qualification") CreateQualificationViewModel qualification, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/qualifications/create-qualification";
        }
        qualificationService.create(modelMapper.map(qualification, CreateQualificationDTO.class));
        return "redirect:/qualifications/";
    }
    @GetMapping("/edit/{id}")
    public String showQualificationEditForm(Model model,@PathVariable Long id){
        UpdateQualificationViewModel qualification = modelMapper.map(qualificationService.getQualification(id),UpdateQualificationViewModel.class);
        model.addAttribute("qualification",qualification);
        return "/qualifications/edit-qualification";
    }
    @PostMapping("/update/{id}")
    public String updateQualification(@PathVariable long id, @Valid @ModelAttribute("qualification") UpdateQualificationViewModel qualification, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/qualifications/edit-qualification";
        }
        qualificationService.updateQualification(id, modelMapper.map(qualification,UpdateQualificationDTO.class));
        return "redirect:/qualifications/";
    }
    @GetMapping("/delete/{id}")
    public String deleteQualification(@PathVariable long id){
        qualificationService.deleteQualification(id);
        return "redirect:/qualifications/";
    }
}
