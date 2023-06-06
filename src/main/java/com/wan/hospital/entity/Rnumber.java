package com.wan.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rnumber {
    private Integer id;
    private String patientId;
    private String deptId;
    private String regTime;
    private Float price;
    private String name;
    private String doctorName;
    private String deptName;
}
