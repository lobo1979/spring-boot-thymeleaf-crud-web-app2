package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Department;


import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    void saveDepartment(Department department);

    Department getDepartmentById(long id);

    void deleteDepartmentById(long id);


}
