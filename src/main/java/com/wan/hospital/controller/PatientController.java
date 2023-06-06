package com.wan.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wan.hospital.entity.*;
import com.wan.hospital.service.iml.DepartmentServiceImpl;
import com.wan.hospital.service.iml.DoctorServiceImpl;
import com.wan.hospital.service.iml.PatientServiceImpl;
import com.wan.hospital.service.iml.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 心缘星雨
 */
@Controller
public class PatientController {

    @Autowired
    RegisterServiceImpl registerService;
    @Autowired
    PatientServiceImpl patientService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    DoctorServiceImpl doctorService;

    @RequestMapping("/patient")
    public String patientIndex(Model model) {
        List<Department> departments = departmentService.list();
        List<DepartmentWithDoctors> departmentWithDoctorsList = new ArrayList<>();
        // 遍历部门列表
        for (Department department : departments) {
            QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dept_id",department.getId());
            // 使用科室ID查询该部门下的所有医生
            List<Doctor> doctors = doctorService.list(queryWrapper);
            // 将医生列表与部门对象关联
            DepartmentWithDoctors departmentWithDoctors = new DepartmentWithDoctors(department.getId(), department.getName(),department.getType(),department.getRemark(), doctors);
            departmentWithDoctorsList.add(departmentWithDoctors);
        }
        System.out.println(departmentWithDoctorsList);
        model.addAttribute("depts",departmentWithDoctorsList);
        return "patient";
    }

    @RequestMapping("/patnumlist")
    public String list(Model model, HttpServletResponse response, HttpServletRequest request) {
        // 获取病人姓名
        String myname = (String) request.getAttribute("loginUser");
        // 获取所有挂号记录
        List<Register> registers = registerService.list();
        System.out.println(registers);
        List<Rnumber> rnumbers = new LinkedList<>();

        for (Register register : registers) {
            //获取记录中病人Id
            String patientId = register.getPatientId();
            String name = patientService.getById(patientId).getName();
            if (name.equals(myname)) {
                String deptId = register.getDeptId();
                Integer id = register.getId();
                String regTime = register.getRegTime();
                Float price = register.getPrice();

                //根据订单中的id获取医生姓名显示
                String doctorId = register.getDoctorId();
                Doctor doctor = doctorService.getById(doctorId);
                String doctorName = doctor.getName();

                String deptname = departmentService.getById(deptId).getName();
                Rnumber rnumber = new Rnumber(id, patientId, deptId, regTime, price, name, doctorName, deptname);
                rnumbers.add(rnumber);
            }
        }
        model.addAttribute("patnums",rnumbers);
        return "patients/patientlist";
    }


}
