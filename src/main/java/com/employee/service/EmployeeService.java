package com.employee.service;

import com.employee.dto.EmployeeDto;
import com.employee.entity.EmployeeEntity;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.SequenceRepository;
import com.employee.util.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    public static final String NAME_ATTRIBUTE = "name";
    public static final String FROM_SALARY_ATTRIBUTE = "fromSalary";
    public static final String TO_SALARY_ATTRIBUTE = "toSalary";

    private final EmployeeRepository employeeRepository;
    private final SequenceRepository sequenceRepository;
    private final EmployeeMapper employeeMapper;
    private final CacheUtils cacheUtils;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SequenceRepository sequenceRepository,
                           EmployeeMapper employeeMapper, CacheUtils cacheUtils) {
        this.employeeRepository = employeeRepository;
        this.sequenceRepository = sequenceRepository;
        this.employeeMapper = employeeMapper;
        this.cacheUtils = cacheUtils;
    }

    public Long save(EmployeeDto employeeDto) {
        Long id = sequenceRepository.getNextId();
        EmployeeEntity employeeEntity = employeeMapper.mapToEmployeeEntity(employeeDto);
        employeeEntity.setId(id);
        employeeRepository.saveEmployee(employeeEntity);
        return id;
    }

    public EmployeeDto getById(Long id) {
        return employeeMapper.mapToEmployeeDto(employeeRepository.getEmployeeById(id));
    }

    public List<EmployeeDto> search(String name, Long fromSalary, Long toSalary) {
        String cacheKey = getCacheKey(name, fromSalary, toSalary);
        List<EmployeeDto> employeeDtoList = (List<EmployeeDto>) cacheUtils.get(cacheKey);

        if (Objects.isNull(employeeDtoList)) {
            employeeDtoList = employeeRepository.searchEmployees(name, fromSalary, toSalary).stream()
                    .map(employeeMapper::mapToEmployeeDto)
                    .toList();
            cacheUtils.put(cacheKey, employeeDtoList);
        }

        return employeeDtoList;
    }

    public String getCacheKey(String name, Long fromSalary, Long toSalary) {
        return NAME_ATTRIBUTE + name + FROM_SALARY_ATTRIBUTE + fromSalary + TO_SALARY_ATTRIBUTE + toSalary;
    }
}
