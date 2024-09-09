package com.employee.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.NotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class EmployeeRepository extends FileRepository {

    private static final String EMPLOYEES_FILE_PATH = "data/employees.txt";

    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public void saveEmployee(EmployeeEntity employeeEntity) {
        saveData(EMPLOYEES_FILE_PATH, objectMapper.writeValueAsString(employeeEntity));
    }

    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = getAllEmployees().stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
        return employeeEntity.orElseThrow(() -> new NotFoundException("Employee with id: " + id + " not found"));
    }

    public List<EmployeeEntity> searchEmployees(String name, Long fromSalary, Long toSalary) {
        return getAllEmployees().stream()
                .filter(employeeEntity -> filterByName(name, employeeEntity) &&
                        filterBySalary(fromSalary, toSalary, employeeEntity)
                )
                .collect(Collectors.toList());
    }

    private static boolean filterBySalary(Long fromSalary, Long toSalary, EmployeeEntity employeeEntity) {
        return (Objects.isNull(fromSalary) || employeeEntity.getSalary().compareTo(fromSalary) >= 0) &&
                (Objects.isNull(toSalary) || employeeEntity.getSalary().compareTo(toSalary) <= 0);
    }

    private static boolean filterByName(String name, EmployeeEntity employeeEntity) {
        return Objects.isNull(name) ||
                employeeEntity.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                employeeEntity.getLastName().toLowerCase().contains(name.toLowerCase());
    }

    private List<EmployeeEntity> getAllEmployees() {
        return getLines(EMPLOYEES_FILE_PATH).map(content -> {
            try {
                return objectMapper.readValue(content, EmployeeEntity.class);
            } catch (Exception e) {
                log.info("Error while processing employee file content: {}", e.getMessage());
            }
            return null;
        }).toList();
    }
}
