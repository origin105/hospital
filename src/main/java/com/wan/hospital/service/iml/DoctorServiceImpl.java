package com.wan.hospital.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wan.hospital.entity.Doctor;
import com.wan.hospital.mapper.DoctorMapper;
import com.wan.hospital.service.DoctorService;
import org.springframework.stereotype.Service;

/**
 * @author 心缘星雨
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService{
}
