/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.dao;

/**
 *
 * @author James
 */
public class ERSPersistenceException extends Exception {

    public ERSPersistenceException(String string) {
        super(string);
    }

    public ERSPersistenceException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
