package com.wan.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wan.hospital.entity.Doctor;
import com.wan.hospital.entity.Patient;
import com.wan.hospital.service.iml.DoctorServiceImpl;
import com.wan.hospital.service.iml.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

/**
 * @author 心缘星雨
 */
@Controller
public class LoginController {

    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    DoctorServiceImpl doctorService;

    @RequestMapping("/user/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("identity") String identity,
                        Model model, HttpSession httpSession) {

        if ("1".equals(identity)) {
            QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", name);
            Patient patient = patientService.getOne(queryWrapper);
            if(patient == null) {
                model.addAttribute("msg","用户不存在");
                return "login";
            } else if (patient.getPasswd().equals(password)) {
                httpSession.setAttribute("loginUser", name);
                return "redirect:/patient";
            } else {
                model.addAttribute("msg", "用户名或密码错误");
                return "login";
            }
        } else if ("2".equals(identity)) {
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", name);
            Doctor doctor = doctorService.getOne(queryWrapper);

            if(doctor == null) {
                model.addAttribute("msg","用户不存在");
                return "login";
            } else if (doctor.getPasswd().equals(password)) {
                httpSession.setAttribute("loginUser", name);
                return "redirect:/welcome";
            } else {
                model.addAttribute("msg", "用户名或密码错误");
                return "login";
            }
        } else {
            model.addAttribute("msg", "系统错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

}
