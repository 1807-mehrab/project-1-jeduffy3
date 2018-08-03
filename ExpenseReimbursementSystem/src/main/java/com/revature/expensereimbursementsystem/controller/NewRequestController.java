/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.controller;

import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.Employee;
import com.revature.expensereimbursementsystem.dto.ReimbursementRequest;
import com.revature.expensereimbursementsystem.dto.Status;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayer;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.ReimbursementRequestServiceLayer;
import com.revature.expensereimbursementsystem.service.ReimbursementRequestServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.StatusServiceLayer;
import com.revature.expensereimbursementsystem.service.StatusServiceLayerImpl;
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
public class NewRequestController extends HttpServlet {

    private EmployeeServiceLayer employeeServiceLayer;
    private StatusServiceLayer statusServiceLayer;
    private ReimbursementRequestServiceLayer reimbursementRequestServiceLayer;
    
    public NewRequestController() {
        this.employeeServiceLayer = new EmployeeServiceLayerImpl();
        this.reimbursementRequestServiceLayer = new ReimbursementRequestServiceLayerImpl();
        this.statusServiceLayer = new StatusServiceLayerImpl();
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
            out.println("<title>Servlet NewRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewRequestController at " + request.getContextPath() + "</h1>");
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
        Status status = null;
        Employee requester = null;
        Employee approver = new Employee();
        ReimbursementRequest reimbursementRequest = null;
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        String description = request.getParameter("description");
        String amount = request.getParameter("request-amount");
        String receipt = "";

        try {
            requester = employeeServiceLayer.getEmployeeByUsername(username);
            status = statusServiceLayer.getStatusByStatus("Pending");
            reimbursementRequest = new ReimbursementRequest();
            reimbursementRequest.setDescription(description);
            reimbursementRequest.setAmount(amount);
            reimbursementRequest.setReceipt(receipt);
            reimbursementRequest.setRequester(requester);
            reimbursementRequest.setApprover(approver);
            reimbursementRequest.setStatus(status);
            reimbursementRequestServiceLayer.addReimbursementRequest(reimbursementRequest);
        } catch (ERSPersistenceException e) {
            System.out.println("Error");
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
