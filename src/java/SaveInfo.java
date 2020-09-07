
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mypkg.Utility;

public class SaveInfo extends HttpServlet {

    private Connection con; private PreparedStatement ps;
    
    //called while loading
    public void init() {
        try{
        con=Utility.connect();
        String sql = "INSERT INTO covidinfo(idate,state,total,active,deaths,userid) VALUES(now(),?,?,?,?,?)";
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
        //?state=MP&userid=mpuser&total=100&active=50&deaths=25
        String userid = request.getParameter("userid");
        String state = request.getParameter("state");
        int total = Integer.parseInt(request.getParameter("total"));
        int active = Integer.parseInt(request.getParameter("active"));
        int deaths = Integer.parseInt(request.getParameter("deaths"));
        //java.util.Date dt=new java.util.Date(); //current-date
        //long val=dt.getTime();
        //java.sql.Date idate=new java.sql.Date(val);
        //process (store the values to DB)
        //now we will store the values into DB using jdbc
        try {
            //ps.setDate(1, idate);
            ps.setString(1, state);
            ps.setInt(2,total);
            ps.setInt(3,active);
            ps.setInt(4,deaths);
            ps.setString(5,userid);
            ps.executeUpdate();
         
            //response
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Information-Submitted-Successfully</h3>");
            out.println("<h4><a href=stadmindashboard.jsp>Dashboard</a></h4>");
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
