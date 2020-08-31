
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypkg.Utility;

public class StateAccountCreationServlet extends HttpServlet {

    private Connection con; private PreparedStatement ps;
    
    //called while loading
    public void init() {
        try{
        //Class.forName("com.mysql.jdbc.Driver");
        //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coviddata", "root", "root");
        con=Utility.connect();
        String sql = "INSERT INTO stateadmins VALUES(?,?,?,null,null,null,null,'disabled')";
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
        String userid = request.getParameter("uid");
        String password = request.getParameter("password");
        String state = request.getParameter("state");
        //process (store the values to DB)
        //now we will store the values into DB using jdbc
        try {
            ps.setString(1, userid);
            ps.setString(2, password);
            ps.setString(3, state);
            ps.executeUpdate();
         
            //response
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Account-Successfully-Created</h3>");
            out.println("<h4><a href=sadmindashboard.jsp>DashBoard</a></h4>");
            out.println("</body>");
            out.println("</html>");

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
