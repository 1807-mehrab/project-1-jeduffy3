/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.expensereimbursementsystem.dao.ERSPersistenceException;
import com.revature.expensereimbursementsystem.dto.Employee;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author James
 */
public class ManagerRequestController extends HttpServlet {

    private StatusServiceLayer statusServiceLayer;
    private RoleServiceLayer roleServiceLayer;
    private EmployeeServiceLayer employeeServiceLayer;
    private ReimbursementRequestServiceLayer reimbursementRequestServiceLayer;

    public ManagerRequestController() {
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
            out.println("<title>Servlet ManagerRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerRequestController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/plain");
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        PrintWriter printWriter = response.getWriter();
        String[] reimbursementRequests = new String[2];
        try {
            for (Employee employee : employeeServiceLayer.getAllEmployees()) {
                reimbursementRequests[0] = mapper.writeValueAsString(reimbursementRequestServiceLayer.getAllPendingRequestsByEmployeeId(employee.getEmployeeId()));
                reimbursementRequests[1] = mapper.writeValueAsString(reimbursementRequestServiceLayer.getAllResolvedRequestsByEmployeeId(employee.getEmployeeId()));
                printWriter.print(Arrays.toString(reimbursementRequests));
                printWriter.close();
            }

        } catch (ERSPersistenceException ex) {
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
        processRequest(request, response);
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
