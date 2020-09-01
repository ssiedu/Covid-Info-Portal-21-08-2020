
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypkg.Utility;

public class UpdateStateAdminProfile extends HttpServlet {

    private Connection con; private PreparedStatement ps;
    
    //called while loading
    public void init() {
        try{
        //Class.forName("com.mysql.jdbc.Driver");
        //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coviddata", "root", "root");
        con=Utility.connect();
        String sql = "UPDATE stateadmins SET password=?, uname=?, email=?, address=?, mobile=?, status='enabled' WHERE userid=?";
        ps = con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //called just before unloading
    public void destroy() {
        try{
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        //request-read
        //?uid=aaa%40gmail.com&password=ssi&name=abc&address=indore&mobile=9094599445
        String userid = request.getParameter("uid");
        String password = request.getParameter("password");
        String uname = request.getParameter("uname");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        String email=request.getParameter("email");
        //process (store the values to DB)
        //now we will store the values into DB using jdbc
        try {
            //password=?, uname=?, email=?, address=?, mobile=?, status='enabled' WHERE userid=?";
            ps.setString(1, password);
            ps.setString(2, uname);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, mobile);
            ps.setString(6, userid);
            ps.executeUpdate();
            response.sendRedirect("stadmindashboard.jsp");
        } catch (Exception e) {
            out.println(e);
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
