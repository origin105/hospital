package com.wan.hospital.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wan.hospital.entity.Register;
import com.wan.hospital.mapper.RegisterMapper;
import com.wan.hospital.service.RegisterService;
import org.springframework.stereotype.Service;

/**
 * @author 心缘星雨
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {
}
