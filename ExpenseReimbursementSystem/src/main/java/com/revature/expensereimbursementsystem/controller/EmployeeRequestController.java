/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayer;
import com.revature.expensereimbursementsystem.service.EmployeeServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.ReimbursementRequestServiceLayer;
import com.revature.expensereimbursementsystem.service.ReimbursementRequestServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.RoleServiceLayer;
import com.revature.expensereimbursementsystem.service.RoleServiceLayerImpl;
import com.revature.expensereimbursementsystem.service.StatusServiceLayer;
import com.revature.expensereimbursementsystem.service.StatusServiceLayerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James
 */
@WebServlet(name = "EmployeeRequestController", urlPatterns = {"/EmployeeRequestController"})
public class EmployeeRequestController extends HttpServlet {

    private StatusServiceLayer statusServiceLayer;
    private RoleServiceLayer roleServiceLayer;
    private EmployeeServiceLayer employeeServiceLayer;
    private ReimbursementRequestServiceLayer reimbursementRequestServiceLayer;

    public EmployeeRequestController() {
        this.statusServiceLayer = new StatusServiceLayerImpl();
        this.roleServiceLayer = new RoleServiceLayerImpl();
        this.employeeServiceLayer = new EmployeeServiceLayerImpl();
        this.reimbursementRequestServiceLayer = new ReimbursementRequestServiceLayerImpl();
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
            out.println("<title>Servlet EmployeeRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeRequestController at " + request.getContextPath() + "</h1>");
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
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        PrintWriter printWriter = response.getWriter();
        String[] reimbursementRequests = new String[2];
        try {
            reimbursementRequests[0] = mapper.writeValueAsString(reimbursementRequestServiceLayer.getAllPendingRequestsByEmployeeId(employeeServiceLayer.getEmployeeByUsername(username).getEmployeeId()));
            reimbursementRequests[1] = mapper.writeValueAsString(reimbursementRequestServiceLayer.getAllResolvedRequestsByEmployeeId(employeeServiceLayer.getEmployeeByUsername(username).getEmployeeId()));
            printWriter.print(Arrays.toString(reimbursementRequests));
            printWriter.close();
        } catch (ERSPersistenceException e) {
            System.out.println("error");
        }
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
