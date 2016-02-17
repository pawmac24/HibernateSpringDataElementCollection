package com.pm.hibernate.repository;

import com.pm.hibernate.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pmackiewicz on 2016-02-03.
 */
@Repository
public interface TestRepository extends JpaRepository<Employee, Long> {
}
