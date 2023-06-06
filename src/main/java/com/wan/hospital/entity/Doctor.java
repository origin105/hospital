package com.wan.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 心缘星雨
 */
@Data
@AllArgsConstructor
public class Doctor {
    private String id;
    private String name;
    private String deptId;
    private Integer sex;
    private String passwd;
    private String email;
}
