package com.wan.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 心缘星雨
 */
@Data
@AllArgsConstructor
public class Department {
    private String id;
    private String name;
    private Integer type;
    private String remark;
}
