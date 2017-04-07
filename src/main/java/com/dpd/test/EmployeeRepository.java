package com.dpd.test;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by txc68 on 4/6/2017.
 */
public interface EmployeeRepository extends CrudRepository <Employee, Long> {
}
