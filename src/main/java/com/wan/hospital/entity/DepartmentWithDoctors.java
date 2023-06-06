package com.wan.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class DepartmentWithDoctors {
    private String id;
    private String name;
    private Integer type;
    private String remark;
    private List<Doctor> doctorList;
}
