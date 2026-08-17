package net.javaguides.employee.controller;

import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.service.EmployeeService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments ")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable("departmentId") Long departmentId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.addEmployee(departmentId, employeeDto);

        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeebyId(@PathVariable("departmentId") Long departmentId, @PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(departmentId, employeeId);

        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        List<EmployeeDto> employees = employeeService.getAllEmployeesByDepartmentId(departmentId);

        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(departmentId, employeeId, employeeDto);

        return ResponseEntity.ok(updatedEmployeeDto);
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("departmentId") Long id, @PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(id, employeeId);
        return ResponseEntity.ok("Delete employee successfully");
    }
}
