package com.wan.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wan.hospital.utils.Price;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 心缘星雨
 */
@Data
@NoArgsConstructor
public class Register {
    @TableId
    private Integer id;
    private String patientId;
    private String deptId;
    private String regTime;
    private Float price;
    private String doctorId;

    public Register(String patientId, String deptId, String doctorId) {
        this.patientId = patientId;
        this.deptId = deptId;
        this.doctorId = doctorId;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.regTime = formatter.format(date);
        this.price = Price.getPrice(regTime);
    }
}
