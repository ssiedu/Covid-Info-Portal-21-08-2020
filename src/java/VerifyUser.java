import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypkg.Utility;

public class VerifyUser extends HttpServlet {
    private Connection con; private PreparedStatement ps1,ps2;
    
    public void init(){
        try{
            con=Utility.connect();
            ps1=con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            ps2=con.prepareStatement("SELECT * FROM stateadmins WHERE userid=? and password=?");
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
            System.out.println("Your Last Login Was : ");
            if(utype.equals("super-admin")){
                if(id.equals("sadmin") && pw.equals("ssi")){
                    //we want this request to be forwarded to saddashboard.jsp
                   //response.sendRedirect("sadmindashboard.jsp");
                    RequestDispatcher rd=request.getRequestDispatcher("sadmindashboard.jsp");
                    rd.forward(request, response);
                }else{
                    out.println("<html><body>");
                    out.println("<h3>Invalid Super Admin Account</h3>");
                    out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                    out.println("</body></html>");
                }
            }else if(utype.equals("state-admin")){
                //we will check the credentials from db and match with the inputs given by user
                try{
                ps2.setString(1, id);
                ps2.setString(2, pw);
                ResultSet rs=ps2.executeQuery();
                boolean found=rs.next();
                if(found){//credentials are correct (id/pw)
                    String status=rs.getString("status");
                    String uid=rs.getString("userid");
                //check the status
                if(status.equals("disabled")){
                //if-disabled-then-we-will-show-profile-completion-form
                out.println("<html>");
                out.println("<body>");
                out.println("<h3>Profile-Completion-Form</h3>");
                out.println("<form action=UpdateStateAdminProfile>");
                //userid,password,uname,email,address,mobile
                out.println("<pre>");
                out.println("Userid     : <input type=text name=uid value="+uid+">");
                out.println("Password   : <input type=password name=password>");
                out.println("Username   : <input type=text name=uname>");
                out.println("Address    : <input type=text name=address>");
                out.println("Email      : <input type=text name=email>");
                out.println("Mobile     : <input type=text name=mobile>");
                out.println("<input type=submit value=Update>");
                out.println("</pre>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                }else{
                //if-enabled-then-we-will-display-dashboard
                    response.sendRedirect("stadmindashboard.jsp");
                }
                }else{
                    //credentials are wrong
                    out.println("<html><body>");
                    out.println("<h3>Invalid State-Admin Account</h3>");
                    out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                    out.println("</body></html>");
                }
                }catch(Exception e){
                    out.println(e);
                }
            }else if(utype.equals("enduser")){
               //we need to authenticate using DB 
               try{
                   ps1.setString(1, id);
                   ps1.setString(2, pw);
                   ResultSet rs=ps1.executeQuery();
                   boolean found=rs.next(); 
                   if(found){
                       response.sendRedirect("userdashboard.jsp");
                   }else{
                    out.println("<html><body>");
                    out.println("<h3>Invalid User Account</h3>");
                    out.println("<h4><a href=index.jsp>Try-Again</a></h4>");
                    out.println("</body></html>");
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
