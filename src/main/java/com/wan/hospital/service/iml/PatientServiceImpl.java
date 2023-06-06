package com.wan.hospital.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wan.hospital.entity.Patient;
import com.wan.hospital.mapper.PatientMapper;
import com.wan.hospital.service.PatientService;
import org.springframework.stereotype.Service;

/**
 * @author 心缘星雨
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
}
