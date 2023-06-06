package com.wan.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 心缘星雨
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String id;
    private String name;
    private Integer sex;
    private Integer age;
    private String passwd;

}
