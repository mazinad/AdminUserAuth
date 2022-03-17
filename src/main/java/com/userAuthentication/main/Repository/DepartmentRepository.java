package com.userAuthentication.main.Repository;

import com.userAuthentication.main.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
