package com.wan.hospital.service.iml;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wan.hospital.entity.Department;
import com.wan.hospital.mapper.DepartmentMapper;
import com.wan.hospital.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 心缘星雨
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
