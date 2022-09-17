package com.bridgelabz.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    private static EmployeePayrollDBService employeePayrollDBService;
    private EmployeePayrollDBService(){}

    public static EmployeePayrollDBService getInstance(){
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();
        return employeePayrollDBService;
    }

    private String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private String user = "root";
    private String password = "Prem@7738";

    private Connection getConnection() throws SQLException {
        System.out.println("Connecting to database:" + jdbcURL);
        Connection connection = DriverManager.getConnection(jdbcURL, user, password);
        System.out.println("Connection is successful!!!!!!" + connection);
        return connection;
    }

    public List<EmployeePayroll> readData() {
        String sql = "SELECT * FROM employee_payroll; ";
        List<EmployeePayroll> employeePayrollList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement() ;
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayrollList = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    private List<EmployeePayroll> getEmployeePayrollData(ResultSet resultSet) {
        List<EmployeePayroll> employeePayrollList = new ArrayList<>();
        try{
            while (resultSet.next()){
                employeePayrollList.add(new EmployeePayroll(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getDouble(3)));
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return employeePayrollList;
    }
}
