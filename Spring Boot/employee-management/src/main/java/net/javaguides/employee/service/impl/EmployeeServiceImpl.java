package net.javaguides.employee.service.impl;

import net.javaguides.employee.dto.EmployeeDto;
import net.javaguides.employee.entity.Department;
import net.javaguides.employee.entity.Employee;
import net.javaguides.employee.exception.BadRequestException;
import net.javaguides.employee.exception.ResourceNotFoundException;
import net.javaguides.employee.repository.DepartmentRepository;
import net.javaguides.employee.repository.EmployeeRepository;
import net.javaguides.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = modelMapper.map(employeeDto, Employee.class);

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartmentId(departmentId);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("This employee does not belong to this department with id: " + departmentId);
        }

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartmentId(employee.getDepartment().getId());

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        return employees.stream().map((employee) -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long departmentId, Long employeeId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("This employee does not belong to this department with id: " + departmentId);
        }

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
        savedEmployeeDto.setDepartmentId(departmentId);

        return savedEmployeeDto;
    }

    @Override
    public void deleteEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (!employee.getDepartment().getId().equals(department.getId())) {
            throw new BadRequestException("This employee does not belong to this department with id: " + departmentId);
        }

        employeeRepository.delete(employee);
    }
}
