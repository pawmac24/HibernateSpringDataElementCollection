package com.pm.hibernate.service;

import com.pm.hibernate.dto.SampleDTO;
import com.pm.hibernate.model.Employee;
import com.pm.hibernate.model.Phone;
import com.pm.hibernate.repository.TestRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by pmackiewicz on 2016-02-03.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    public final static Logger logger = Logger.getLogger(TestService.class);

    @Autowired
    private TestRepository testRepository;

    @Override
    public SampleDTO get() {
        logger.info("===get===");
        Employee employee = new Employee("Bob", "Way", new BigDecimal(100.50).setScale(2));
        employee.addPhone( new Phone("home", "613", "792-0001"));
        employee.addPhone( new Phone("work", "613", "494-1234"));
        testRepository.save(employee);
        logger.info("(1)" + employee);
        employee.setFirstName("Bobik");
        employee.setLastName("Wayek");
        logger.info("(2)" + employee);

        Employee employee2 = new Employee("Joe", "Smith", new BigDecimal(300.00).setScale(2));
        employee2.addPhone( new Phone("work", "416", "892-0005"));
        testRepository.save(employee2);
        logger.info("(3)" + employee2);

        Employee employeeFind = testRepository.findOne(employee.getId());
        logger.info("(4)" + employeeFind);
        Employee employeeFind2 = testRepository.findOne(employee2.getId());
        logger.info("(5)" + employeeFind2);

        employeeFind.setFirstName("Bobas");
        employeeFind.setLastName("Wayas");
        logger.info("(6)" + employeeFind);
        testRepository.delete(employeeFind);
        testRepository.delete(employeeFind2);

        return new SampleDTO();
    }

    @Override
    public SampleDTO find() {
        logger.info("===find===");
        testRepository.findAll();
        return new SampleDTO();
    }

    @Override
    public SampleDTO delete() {
        logger.info("===delete===");
        testRepository.deleteAll();
        return new SampleDTO();
    }
}
