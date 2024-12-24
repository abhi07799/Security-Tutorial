package com.securitytutorial.repository;

import com.securitytutorial.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel, Integer>
{
    Optional<EmployeeModel> findByEmployeeMail(String email);

    Optional<EmployeeModel> findByRole(String role);
}
