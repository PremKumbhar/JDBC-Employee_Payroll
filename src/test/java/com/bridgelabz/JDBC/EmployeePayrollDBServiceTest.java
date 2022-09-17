package com.bridgelabz.JDBC;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeePayrollDBServiceTest {
    @Test

    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {

        EmployeePayrollDBService employeePayrollDBService = EmployeePayrollDBService.getInstance();
        List<EmployeePayroll> retrivedEmpList = employeePayrollDBService.readData();

        Assertions.assertEquals(4, retrivedEmpList.size());
    }
}
