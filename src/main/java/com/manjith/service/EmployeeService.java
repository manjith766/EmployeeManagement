package com.manjith.service;

import com.manjith.entity.Employee;
import com.manjith.exception.ResourceNotFoundException;
import com.manjith.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee create(Employee employee) {
        repository.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Email already exists");
                });
        return repository.save(employee);
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        repository.deleteById(id);
    }
}