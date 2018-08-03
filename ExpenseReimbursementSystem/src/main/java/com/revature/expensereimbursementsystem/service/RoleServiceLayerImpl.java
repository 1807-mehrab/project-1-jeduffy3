/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dao.RoleDao;
import com.revature.expensereimbursementsystem.dao.RoleDaoOracleSqlImpl;
import com.revature.expensereimbursementsystem.dto.Role;
import java.util.List;

/**
 *
 * @author James
 */
public class RoleServiceLayerImpl implements RoleServiceLayer {

    private RoleDao roleDao;

    public RoleServiceLayerImpl() {
        this.roleDao = new RoleDaoOracleSqlImpl();
    }
    
    @Override
    public void addRole(Role role) throws ERSPersistenceException {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(int roleId) throws ERSPersistenceException {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void updateRole(Role role) throws ERSPersistenceException {
        roleDao.updateRole(role);
    }

    @Override
    public Role getRoleByRoleId(int roleId) throws ERSPersistenceException {
        return roleDao.getRoleByRoleId(roleId);
    }
    
    @Override
    public Role getRoleByEmployeeId(int employeeId) throws ERSPersistenceException {
        return roleDao.getRoleByEmployeeId(employeeId);
    }

    @Override
    public Role getRoleByUsername(String username) throws ERSPersistenceException {
        return roleDao.getRoleByUsername(username);
    }
    
    @Override
    public List<Role> getAllRoles() throws ERSPersistenceException {
        return roleDao.getAllRoles();
    }
    
}
