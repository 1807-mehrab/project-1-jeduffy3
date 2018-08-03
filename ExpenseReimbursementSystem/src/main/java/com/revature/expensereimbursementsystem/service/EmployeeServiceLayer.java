/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.Employee;
import java.util.List;

/**
 *
 * @author James
 */
public interface EmployeeServiceLayer {
    
    public void addEmployee(Employee employee) throws ERSPersistenceException;
    
    public void deleteEmployee(int employeeId) throws ERSPersistenceException;
    
    public void updateEmployee(Employee employee) throws ERSPersistenceException;
    
    public Employee getEmployeeByEmployeeId(int employeeId) throws ERSPersistenceException;
    
    public Employee getEmployeeByUserId(int userId) throws ERSPersistenceException;
    
    public Employee getEmployeeByUsername(String username) throws ERSPersistenceException;
    
    public Employee getRequesterByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException;
    
    public Employee getApproverByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException;
    
    public List<Employee> getAllEmployees() throws ERSPersistenceException;
    
}
