import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypkg.Utility;

public class VerifyUser extends HttpServlet {
    private Connection con; private PreparedStatement ps1;
    
    public void init(){
        try{
            con=Utility.connect();
            ps1=con.prepareStatement("SELECT * FROM users where email=? AND password=?");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out=response.getWriter();
            //VerifyUser?uid=aaa%40gmail.com&password=ssi&utype=enduser
            String id=request.getParameter("uid");
            String pw=request.getParameter("password");
            String utype=request.getParameter("utype");
            
            if(utype.equals("super-admin")){
                if(id.equals("sadmin") && pw.equals("ssi")){
                    out.println("Welcome Super Admin");
                }else{
                    out.println("Invalid Super Admin Account");
                }
            }else if(utype.equals("state-admin")){
                out.println("Welcome State Admin");
            }else if(utype.equals("enduser")){
               //we need to authenticate using DB 
               try{
                   ps1.setString(1, id);
                   ps1.setString(2, pw);
                   ResultSet rs=ps1.executeQuery();
                   boolean found=rs.next(); 
                   if(found){
                       out.println("WELCOME END USER");
                   }else{
                       out.println("INVALID END USER ACCOUNT");
                   }
               }catch(Exception e){
                   out.println(e);
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
