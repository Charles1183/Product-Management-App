/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import edu.uncc.data.ProductIO;
import edu.uncc.data.ProductTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author patrick
 */
public class ProductManagementServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if( "signup".equals( request.getParameter("action")) ){
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductManagementServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
                }
            }
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String path = this.getServletContext().getRealPath("/WEB-INF/products.txt");
        String action = request.getParameter("action");
        
        if(action.equals("view_products"))
        {
            List<Product> prod = ProductTable.retrieveAllProducts();

            request.setAttribute("productData", prod);
            getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
        }
        
         if(action.equals("update")){
            String code = request.getParameter("code");
            String desc = request.getParameter("desc");
            String priceString = request.getParameter("price");
            double price = Double.parseDouble(priceString);
            Product product = new Product();
            
            product.setCode(code); 
            product.setDescription(desc); 
            product.setPrice(price);

            request.setAttribute("productData", product);
            
            getServletContext().getRequestDispatcher("/product.jsp").forward(request,response);
        }
        
        if(action.equals("delete")){
            String code = request.getParameter("code");
            String desc = request.getParameter("desc");
            String priceString = request.getParameter("price");
            double price = Double.parseDouble(priceString);
            Product product = new Product();
            
            product.setCode(code); 
            product.setDescription(desc); 
            product.setPrice(price);

            request.setAttribute("productData", product);
            
            getServletContext().getRequestDispatcher("/confirmDelete.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String path = this.getServletContext().getRealPath("/WEB-INF/products.txt");
        String action = request.getParameter("action");
                
        if(action.equals("view_products"))
        {
            //List<Product> prod = ProductTable.retrieveAllProducts();

            request.setAttribute("productData", ProductTable.retrieveAllProducts());//prod);
            getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
        }
        
        if(action.equals("add_product")){
            
            getServletContext().getRequestDispatcher("/product.jsp").forward(request,response);
        }
        
        if(action.equals("update_product")){
            String addCode = request.getParameter("code");
            String addDesc = request.getParameter("desc");
            String addPriceString = request.getParameter("price");
            
            String errorList = "";

            if( addCode.equals("") ) { errorList += "Code "; }
            if( addDesc.equals("") ) { errorList += "Description "; }
            if( addPriceString.equals("") ) { errorList += "Price ";}
            
            
            if ( errorList.equals("") ) {
                
                if (ProductTable.exists(addCode)){
                    String updateDesc = request.getParameter("desc");
                    String updatePriceString = request.getParameter("price");
                    double updatePrice = Double.parseDouble(updatePriceString);
                    
                    Product product = new Product();
                    
                    product.setCode(addCode);
                    product.setDescription(updateDesc);
                    product.setPrice(updatePrice);
                    
                    ProductTable.updateProduct(product);
                    
                    List<Product> prod = ProductTable.retrieveAllProducts();
                    request.setAttribute("productData", prod);
                    
                    getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
                }else{
                    double addPrice = Double.parseDouble(addPriceString);
                    Product product = new Product();
                    product.setCode(addCode);
                    product.setDescription(addDesc);
                    product.setPrice(addPrice);
               
                    ProductTable.insertProduct(product);
                    
                    List<Product> prod = ProductTable.retrieveAllProducts();
                    request.setAttribute("productData", prod);
                    
                    getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
                }
                
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
                    out.println("<p>Product already exists.</p>");
                    out.println("<p>One or more field(s)are blank.</p>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }
        
        if(action.equals("yesDelete")){
            String code = request.getParameter("code");
            String desc = request.getParameter("desc");
            String priceString = request.getParameter("price");
            double price = Double.parseDouble(priceString);
            
            Product product = new Product();
            
            product.setCode(code); 
            product.setDescription(desc); 
            product.setPrice(price);
            
            ProductTable.deleteProduct(product);
            
            List<Product> prod = ProductTable.retrieveAllProducts();

            request.setAttribute("productData", prod);
            getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
            
        } else if(action.equals("noDelete")){
            
            List<Product> prod = ProductTable.retrieveAllProducts();

            request.setAttribute("productData", prod);
            getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
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
    }

}
