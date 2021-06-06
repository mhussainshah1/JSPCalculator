package com.example.JSPCalculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = {"", "/"})
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String principalAmount = request.getParameter("principalamount");
        String interestPercentage = request.getParameter("interest");
        String years = request.getParameter("years");
        String compoundingPeriod = request.getParameter("compoundingPeriod");
        String error;

        if (principalAmount == null || interestPercentage == null || years == null || compoundingPeriod == null
                || principalAmount.isBlank() || interestPercentage.isBlank() || years.isBlank() || compoundingPeriod.isBlank()) {

            error = "One or more of the input boxes were blank. Try again.";
            request.setAttribute("error", error);
        } else {

            double result = Utils.calculateCompoundInterest(
                                                Double.parseDouble(principalAmount),
                                                (Double.parseDouble(interestPercentage) / 100),
                                                Integer.parseInt(years),
                                                Integer.parseInt(compoundingPeriod));
            request.setAttribute("result", result);
        }
        request.setAttribute("principal" ,principalAmount);
        request.setAttribute("interest",interestPercentage);
        request.setAttribute("years",years);
        request.setAttribute("compoundingPeriod",compoundingPeriod);
        doGet(request, response);
    }
}
