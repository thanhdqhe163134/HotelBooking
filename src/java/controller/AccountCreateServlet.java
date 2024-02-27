package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.AccountDAO;
import util.PasswordUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "AccountCreateServlet", value = "/register")
public class AccountCreateServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("account-create.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try {
            String hashPassword = PasswordUtil.generateStrongPasswordHash(password);

            if (accountDAO.existsByUsernameOrEmail(username, email)) {
                request.setAttribute("message", "Username or Email already exist.");
                request.getRequestDispatcher("register").forward(request, response);
            } else {
                accountDAO.createAccount(username, hashPassword, email);
                request.setAttribute("message", "Account created successfully");
                response.sendRedirect("login");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServletException(e);
        }
    }
}
