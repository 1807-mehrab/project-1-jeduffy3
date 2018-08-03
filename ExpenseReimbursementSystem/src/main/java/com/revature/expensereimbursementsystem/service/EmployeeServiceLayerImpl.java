/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dao.EmployeeDao;
import com.revature.expensereimbursementsystem.dao.EmployeeDaoOracleSqlImpl;
import com.revature.expensereimbursementsystem.dto.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class EmployeeServiceLayerImpl implements EmployeeServiceLayer {

    private EmployeeDao employeeDao;
    private RoleServiceLayer roleServiceLayer;
    
    public EmployeeServiceLayerImpl() {
        this.employeeDao  = new EmployeeDaoOracleSqlImpl();
        this.roleServiceLayer = new RoleServiceLayerImpl();
    }

    @Override
    public void addEmployee(Employee employee) throws ERSPersistenceException {
        employeeDao.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) throws ERSPersistenceException {
        employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public void updateEmployee(Employee employee) throws ERSPersistenceException {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public Employee getEmployeeByEmployeeId(int employeeId) throws ERSPersistenceException {
        if (employeeDao.getEmployeeByEmployeeId(employeeId) == null) {
            return null;
        } else {
            return associateRoleWithEmployee(employeeDao.getEmployeeByEmployeeId(employeeId));
        }
    }

    @Override
    public Employee getEmployeeByUserId(int userId) throws ERSPersistenceException {
        if (employeeDao.getEmployeeByUserId(userId) == null) {
            return null;
        } else {
            return associateRoleWithEmployee(employeeDao.getEmployeeByUserId(userId));
        }
    }
    
    @Override
    public Employee getEmployeeByUsername(String username) throws ERSPersistenceException {
        if (employeeDao.getEmployeeByUsername(username) == null) {
            return null;
        } else {
            return associateRoleWithEmployee(employeeDao.getEmployeeByUsername(username));
        }
    }
    
    @Override
    public Employee getRequesterByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        if (employeeDao.getRequesterByReimbursementRequestId(reimbursementRequestId) == null) {
            return null;
        } else {
            return associateRoleWithEmployee(employeeDao.getRequesterByReimbursementRequestId(reimbursementRequestId));
        }
    }
    
    @Override
    public Employee getApproverByReimbursementRequestId(int reimbursementRequestId) throws ERSPersistenceException {
        if (employeeDao.getApproverByReimbursementRequestId(reimbursementRequestId) == null) {
            return null;
        } else {
            return associateRoleWithEmployee(employeeDao.getApproverByReimbursementRequestId(reimbursementRequestId));
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws ERSPersistenceException {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employeeDao.getAllEmployees()) {
            employeeList.add(associateRoleWithEmployee(employee));
        }
        return employeeList;
    }

    private Employee associateRoleWithEmployee(Employee employee) throws ERSPersistenceException {
        employee.setRole(roleServiceLayer.getRoleByEmployeeId(employee.getEmployeeId()));
        return employee;
    }
}
