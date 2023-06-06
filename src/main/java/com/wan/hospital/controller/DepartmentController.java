package com.wan.hospital.controller;

import com.wan.hospital.entity.Department;
import com.wan.hospital.service.iml.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 心缘星雨
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl service;

    @RequestMapping("/dept")
     public String list(Model model) {
         List<Department> departments = service.list();
         System.out.println(departments);
         model.addAttribute("depts",departments);
         return "list";
     }

}
