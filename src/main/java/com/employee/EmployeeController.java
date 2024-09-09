package com.employee;

import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> search(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "fromSalary", required = false) Long fromSalary,
                                    @RequestParam(value = "toSalary", required = false) Long toSalary) {
        return ResponseEntity.ok(employeeService.search(name, fromSalary, toSalary));
    }

}
