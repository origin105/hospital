package com.wan.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wan.hospital.entity.Doctor;
import com.wan.hospital.entity.Patient;
import com.wan.hospital.service.iml.DoctorServiceImpl;
import com.wan.hospital.service.iml.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 心缘星雨
 */
@Controller
public class LogonController {

    @Autowired
    PatientServiceImpl patientService;
    DoctorServiceImpl doctorService;

    @RequestMapping("/tologon")
    public String tologon() {
        return "logon";
    }

    @RequestMapping("/mylogon")
    public String logon(@RequestParam("id") String id, @RequestParam("name") String name,
                        @RequestParam("password") String password, @RequestParam("age") String age,
                        @RequestParam("sex") String sex, Model model){
        if(StringUtils.hasText(id) || StringUtils.hasText(name) || StringUtils.hasText(password) || StringUtils.hasText(age) || StringUtils.hasText(sex)) {
            model.addAttribute("msg","请填所有信息");
            return "logon";
        } else {
            Integer agec = Integer.valueOf(age);
            Integer sexc = Integer.valueOf(sex);
            Patient patient = new Patient(id,name,agec,sexc,password);

            Patient patient1 = patientService.getById(id);
            QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);
            Patient patient2 = patientService.getOne(queryWrapper);
            if(patient1 != null || patient2 != null) {
                model.addAttribute("msg","账号已经注册，请登录");
                return  "logon";
            } else {
                patientService.save(patient);
                return "login";
            }

        }
    }

    @RequestMapping("/mylogonD")
    public String logonD(@RequestParam("id") String id, @RequestParam("name") String name,
                         @RequestParam("password") String password, @RequestParam("deptId") String deptId,
                         @RequestParam("sex") String sex, @RequestParam("email") String email, Model model) {
        if(StringUtils.hasText(id) || StringUtils.hasText(name) || StringUtils.hasText(password) || StringUtils.hasText(deptId) || StringUtils.hasText(sex)) {
            model.addAttribute("msg","信息缺失");
            return "logonD";
        } else {
            Integer sexc = Integer.valueOf(sex);

            Doctor doctor = new Doctor(id,name,deptId,sexc,password,email);

            Doctor doctor1 = doctorService.getById(id);
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);
            Doctor doctor2 = doctorService.getOne(queryWrapper);

            if(doctor1 != null || doctor2 != null){
                model.addAttribute("msg","账号已经注册，请登录");
                return  "logonD";
            } else {
                doctorService.save(doctor);
                return "login";
            }

        }
    }
}
