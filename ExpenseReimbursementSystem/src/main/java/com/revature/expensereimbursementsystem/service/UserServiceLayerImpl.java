/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.service;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dao.UserDao;
import com.revature.expensereimbursementsystem.dao.UserDaoOracleSqlImpl;
import com.revature.expensereimbursementsystem.dto.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class UserServiceLayerImpl implements UserServiceLayer {

    private UserDao userDao;
    private EmployeeServiceLayer employeeServiceLayer;

    public UserServiceLayerImpl() {
        this.userDao = new UserDaoOracleSqlImpl();
        this.employeeServiceLayer = new EmployeeServiceLayerImpl();
    }
    
    @Override
    public void addUser(User user) throws ERSPersistenceException {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int userId) throws ERSPersistenceException {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) throws ERSPersistenceException {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByUserId(int userId) throws ERSPersistenceException {
        if(userDao.getUserByUserId(userId) == null) {
            return null;
        } else {
            return associateEmployeeWithUser(userDao.getUserByUserId(userId));
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws ERSPersistenceException {
        User user = null;
        for (User currentUser : userDao.getAllUsers()) {
            if ((currentUser.getUsername().equals(username)) && (currentUser.getPassword().equals(password))) {
                user = currentUser;
            }
        }
        return user;
    }
    
    @Override
    public List<User> getAllUsers() throws ERSPersistenceException {
        List<User> userList = new ArrayList<>();
        for (User user : userDao.getAllUsers()) {
            userList.add(associateEmployeeWithUser(user));
        }
        return userList;
    }
    
    private User associateEmployeeWithUser(User user) throws ERSPersistenceException {
        user.setEmployee(employeeServiceLayer.getEmployeeByUserId(user.getUserId()));
        return user;
    }
    
}
