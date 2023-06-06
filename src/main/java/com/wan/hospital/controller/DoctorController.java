package com.wan.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wan.hospital.entity.*;
import com.wan.hospital.service.iml.DepartmentServiceImpl;
import com.wan.hospital.service.iml.DoctorServiceImpl;
import com.wan.hospital.service.iml.PatientServiceImpl;
import com.wan.hospital.service.iml.RegisterServiceImpl;
import com.wan.hospital.utils.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author 心缘星雨
 */
@Controller
public class DoctorController {

    @Autowired
    DoctorServiceImpl doctorService;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    RegisterServiceImpl registerService;
    @Autowired
    PatientServiceImpl patientService;

    @RequestMapping("/deptIm")
    public String deptIm(Model model, HttpServletRequest request) {
        String name = (String) request.getAttribute("loginUser");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Doctor doctor = doctorService.getOne(queryWrapper);
        String deptid = doctor.getDeptId();
        Department department = departmentService.getById(deptid);
        model.addAttribute("department",department);
        model.addAttribute("deptName",department.getName());
        return "doctor";
    }

    @RequestMapping("/docRes")
    public String docRes(Model model, HttpServletRequest request) {
        String name = (String) request.getAttribute("loginUser");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Doctor doctor = doctorService.getOne(queryWrapper);
        String deptId = doctor.getDeptId();

//        QueryWrapper<Register> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.eq("dept_id", deptId);
        QueryWrapper<Register> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", name);
        List<Register> registers = registerService.list(queryWrapper1);
        List<Rnumber> rnumbers = new LinkedList<>();
        for(int i = 0; i < registers.size(); i++) {
            Register register = registers.get(i);
            String patientId = register.getPatientId();
            Patient patient = patientService.getById(patientId);
            Department department = departmentService.getById(deptId);
            Integer id = register.getId();
            String regtime = register.getRegTime();
            Float price = register.getPrice();
            Rnumber rnumber = new Rnumber(id,patientId,deptId,regtime,price,patient.getName(),name,department.getName());
            rnumbers.add(rnumber);
        }
        model.addAttribute("rnumbers",rnumbers);
        return "doctors/doctorlist.html";
    }

    @GetMapping("/changedept/{deptId}")
    public String changeDept(@PathVariable("deptId") String deptId, Model model) {
        Department department = departmentService.getById(deptId);
        model.addAttribute("deptId",department.getId());
        model.addAttribute("name",department.getName());
        model.addAttribute("type",department.getType());
        model.addAttribute("remark",department.getRemark());
        return "commons/update";
    }

    @PostMapping("/change")
    public String change(Model model,HttpServletRequest request){
        String loginUser = (String) request.getAttribute("loginUser");
        String deptRemark = request.getParameter("deptRemark");

        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginUser);
        Doctor doctor = doctorService.getOne(queryWrapper);

        String deptId = doctor.getDeptId();
        Department department1 = departmentService.getById(deptId);
        Department department = new Department(department1.getId(),department1.getName(),department1.getType(),deptRemark);
        boolean b = departmentService.updateById(department);
        if (!b) {
            model.addAttribute("msg", "修改失败");
        }
        model.addAttribute("department",department);
        model.addAttribute("deptName",department.getName());
        return "doctor";
    }

    @RequestMapping("/select")
    public String select() {
        return "commons/selectCondition";
    }

    @PostMapping("/selectAll")
    public String selectCondition(Model model,HttpServletRequest request)  {
        String patientName = request.getParameter("patientName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        String loginUser = (String) request.getAttribute("loginUser");
        QueryWrapper<Doctor> doctorQueryWrapper = new QueryWrapper<>();
        doctorQueryWrapper.eq("name",loginUser);
        Doctor doctor = doctorService.getOne(doctorQueryWrapper);

        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", patientName);
        Patient patient = patientService.getOne(queryWrapper);

        QueryWrapper<Register> queryWrapper1 = new QueryWrapper<>();

        queryWrapper1.eq("dept_id",doctor.getDeptId());

        if(!Objects.equals(patientName, "")) {
            queryWrapper1.eq("patient_id",patient.getId());
        }

        if(!Objects.equals(startTime, "")) {
            startTime = MyDate.changTime(startTime);
            queryWrapper1.ge("reg_time",startTime);
        }
        List<Rnumber> rnumbers = new LinkedList<>();
        List<Register> registerList = registerService.list(queryWrapper1);
        for(int i = 0; i < registerList.size(); i++) {
            Patient patient1 = patientService.getById(registerList.get(i).getPatientId());
            Department department = departmentService.getById(registerList.get(i).getDeptId());

            Rnumber rnumber = new Rnumber();
            if(Objects.equals(endTime, "")) {
                rnumber = new Rnumber(registerList.get(i).getId(), registerList.get(i).getPatientId(),
                        registerList.get(i).getDeptId(), registerList.get(i).getRegTime(), registerList.get(i).getPrice(),
                        patient1.getName(),registerList.get(i).getDoctorId(), department.getName());
            } else {
                if(registerList.get(i).getRegTime().compareTo(MyDate.changTime(endTime)) <= 0) {
                    rnumber = new Rnumber(registerList.get(i).getId(),registerList.get(i).getPatientId(),
                            registerList.get(i).getDeptId(), registerList.get(i).getRegTime(), registerList.get(i).getPrice(),
                            patient1.getName(),registerList.get(i).getDoctorId(), department.getName());
                }
            }
            rnumbers.add(rnumber);
        }
        model.addAttribute("rnumbers",rnumbers);
        return "doctors/doctorlist.html";
    }

}
