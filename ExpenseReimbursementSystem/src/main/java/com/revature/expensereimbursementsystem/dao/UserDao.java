/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

import com.revature.expensereimbursementsystem.dto.User;
import java.util.List;

/**
 *
 * @author James
 */
public interface UserDao {
    
    public void addUser(User user) throws ERSPersistenceException;
    
    public void deleteUser(int userId) throws ERSPersistenceException;
    
    public void updateUser(User user) throws ERSPersistenceException;
    
    public User getUserByUserId(int userId) throws ERSPersistenceException;
    
    public List<User> getAllUsers() throws ERSPersistenceException;
    
}
