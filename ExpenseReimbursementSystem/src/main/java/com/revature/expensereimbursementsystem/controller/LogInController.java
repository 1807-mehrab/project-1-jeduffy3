/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.controller;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.User;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayer;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.RoleServiceLayer;
import com.revature.expensereimbursementsystem.service.RoleServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.UserServiceLayer;
import com.revature.expensereimbursementsystem.service.UserServiceLayerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James
 */
public class LogInController extends HttpServlet {

    private UserServiceLayer userServiceLayer;
    private RoleServiceLayer roleServiceLayer;
    private EmployeeServiceLayer employeeServiceLayer;

    public LogInController() {
        this.userServiceLayer = new UserServiceLayerImpl();
        this.roleServiceLayer = new RoleServiceLayerImpl();
        this.employeeServiceLayer = new EmployeeServiceLayerImpl();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = null;
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        try {
            user = userServiceLayer.getUserByUsernameAndPassword(username, request.getParameter("password"));
            if (user != null) {
                session.setAttribute("username", username);
                if (roleServiceLayer.getRoleByUsername(username).getRole().equalsIgnoreCase("employee")) {
                    response.sendRedirect("employee-home.html");
                } else if (roleServiceLayer.getRoleByUsername(username).getRole().equalsIgnoreCase("manager")) {
                    response.sendRedirect("manager-home.html");
                }
            } else {
                System.out.println("Error");
            }
        } catch (ERSPersistenceException e) {
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
