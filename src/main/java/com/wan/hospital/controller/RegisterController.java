package com.wan.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wan.hospital.entity.Patient;
import com.wan.hospital.entity.Register;
import com.wan.hospital.service.iml.PatientServiceImpl;
import com.wan.hospital.service.iml.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 心缘星雨
 */
@Controller
public class RegisterController {

    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    RegisterServiceImpl registerService;

    @GetMapping("/register/{deptId}/{doctorId}")
    public String register(@PathVariable("deptId")String deptId, @PathVariable("doctorId")String doctorId, Model model, HttpServletRequest request){
        String name = (String) request.getAttribute("loginUser");
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Patient patient = patientService.getOne(queryWrapper);

        String patientId = patient.getId();
        Register register = new Register(patientId, deptId, doctorId);
        if(registerService.save(register)) {
            return "redirect:/patnumlist";
        } else {
            model.addAttribute("msg", "挂号失败");
            return "patient";
        }
    }
}
