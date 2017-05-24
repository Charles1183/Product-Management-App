/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import edu.uncc.data.UserTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zachery
 */
public class MembershipControllerServlet extends HttpServlet {
    boolean loggedIn = false;
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
        
        if( "signup".equals( request.getParameter("action")) ) { 
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request,response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet MembershipControllerServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<p>Error! The action parameter is required, only signup value is valid</p>");
                out.println("Action is " + request.getParameter("action") );   
                out.println("</body>");
                out.println("</html>");
            } 
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
    //doGet() is used to process href links and checking if the user is logged in
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        Cookie[] cookies = request.getCookies();
        //Check for all aviable cookies for the site
        //If no cookies are found then loggedIn will be set to false
        //Cookies should be equal to null when the browser first 
        //visits the site after it has been started up.
        if(cookies!=null){
            for (Cookie cookie: cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    break;
                }
                if(!cookie.getName().equals("JSESSIONID")){
                    loggedIn = false;
                }
            }
        }else if(cookies==null){loggedIn = false;}
        //This session object will be used to check if the user has logged in
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", loggedIn);
        session.setMaxInactiveInterval(-1);
        //Assigning the session attribute to a boolean value 
        boolean logIn = (boolean) session.getAttribute("loggedIn");
        //If the user chooses to logout the loggedIn value is set to false
        //telling the system that the user is not logged in 
        if(logIn){
            if(action.equals("logout")){
                loggedIn = false;
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
            }else{getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);}
        }else{getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //doPost() handles user creation, username and password validation, and
    //requests not sent using href links.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        //Get the path of the application's WEB-INF to add the users.txt file
        //to the path.
        String path = this.getServletContext().getRealPath("jdbc:mysql://localhost:3306/Shop");
        String action = request.getParameter("action");
        //System.out.println("PATH = "+path);
        
        //Validates the email and password created by the user in the sign.jsp
        //If all feilds are fillout correctly then the user info is saved 
        //to the user.txt file
        if(action.equals("signup")){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            
            //Used to validate the user's email format
            String emailFormat = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,})$";
            Pattern pattern = Pattern.compile(emailFormat);
            Matcher checker = pattern.matcher(email);
            
            //If the email and password does not fit the correct formatting
            //then they are set to empty
            if(!checker.matches()){ //!checker.matches()
                email = null;
            }
            if(password.length()<=8){
                password = null;
            }

            //Used to hold all empty fields in the sign form
            String errorList = "";

            if( firstName == null ) { errorList += "firstName "; }
            if( lastName == null ) { errorList += "lastName "; }
            if( email == null ) { errorList += "email "; }
            if( password == null ) { errorList += "password "; }
        
            //If all fields are correctly fillout the user info is 
            //added to the user.txt file and sent to the login page
            if ( errorList.equals("") ) {
                User userData = new User(firstName, lastName, email, password);
            
                UserTable.addRecord(userData);
                //HttpSession session = request.getSession();
                request.setAttribute("userData", userData);
                //session.setAttribute("userData", userData);
                
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
                
            } else {
                //Error message if one or more feilds are not 
                //correctly filled out
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Membership</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error! "+errorList+"</h1>");
                    out.println("<p>"+errorList+" field is blank or in invalid entry.</p>");
                    out.println("<p>Email must be a valid email address.</p>");
                    out.println("<p>Password must be at longer then 8 characters.</p>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }
        
        //Takes the login info from the login.jsp and compares 
        //the info against the users.txt file
         if(action.equals("view_products")){
            if(!loggedIn){
                //User email, username and password
                String emailLogIn = request.getParameter("username");
                String passwordLogIn = request.getParameter("pass");
                User userFound = UserTable.getUser(emailLogIn);
                if(passwordLogIn.equals(userFound.getPassword())){
                    loggedIn=true;
                
                }
                System.out.print("User Password = "+userFound.getPassword());
            }
            //If the user info is found in the users.txt file 
            //they are sent to the products page
            if(loggedIn){
                getServletContext().getRequestDispatcher("/productManagement").forward(request,response);
            }else{
                //If user info is not found or incorrect
               getServletContext().getRequestDispatcher("/signup.jsp").forward(request,response);
            } 
        }
            if(action.equals("update_product")){
                if(loggedIn){
                    getServletContext().getRequestDispatcher("/productManagement").forward(request,response);
                }else{
                    //If user info is not found or incorrect
                   getServletContext().getRequestDispatcher("/signup.jsp").forward(request,response);
                } 
            }

            if(action.equals("yesDelete")||action.equals("noDelete")){
                if(loggedIn){
                    getServletContext().getRequestDispatcher("/productManagement").forward(request,response);
                }else{
                    //If user info is not found or incorrect
                   getServletContext().getRequestDispatcher("/signup.jsp").forward(request,response);
                } 
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
