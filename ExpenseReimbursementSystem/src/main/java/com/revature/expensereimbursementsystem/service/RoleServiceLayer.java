/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.Role;
import java.util.List;

/**
 *
 * @author James
 */
public interface RoleServiceLayer {
    
    public void addRole(Role role) throws ERSPersistenceException;
    
    public void deleteRole(int roleId) throws ERSPersistenceException;
    
    public void updateRole(Role role) throws ERSPersistenceException;
    
    public Role getRoleByRoleId(int roleId) throws ERSPersistenceException;
    
    public Role getRoleByEmployeeId(int employeeId) throws ERSPersistenceException;
    
    public Role getRoleByUsername(String username) throws ERSPersistenceException;
    
    public List<Role> getAllRoles() throws ERSPersistenceException;
    
}
