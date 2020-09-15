import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllDataServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //this servlet will show the data of that state whose admin is current logged-in
        //from where the name of state will come? (we can read it from session)
        PrintWriter out=response.getWriter();
        //fetching data from Database
        String sql="SELECT * FROM covidinfo";
        try{
            Connection con=mypkg.Utility.connect();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            out.println("<html>");
            out.println("<body>");
            out.println("<hr>");
            out.println("<h3>Data For All States</h3>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<th>Sno</th><th>State</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th>");
            out.println("</tr>");
            int sumTotal=0;
            int sumActive=0;
            int sumDeaths=0;
            while(rs.next()){
                String sno=rs.getString("sno");
                String state=rs.getString("state");
                String date=rs.getString("idate");
                int total=rs.getInt("total");   sumTotal=sumTotal+total;
                int active=rs.getInt("active"); sumActive=sumActive+active;
                int deaths=rs.getInt("deaths"); sumDeaths=sumDeaths+deaths;
                out.println("<tr>");
                out.println("<td>"+sno+"</td>");
                out.println("<td>"+state+"</td>");
                out.println("<td>"+date+"</td>");
                out.println("<td align=right>"+total+"</td>");
                out.println("<td align=right>"+active+"</td>");
                out.println("<td align=right>"+deaths+"</td>");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<td></td><td></td><td></td>");
            out.println("<td align=right>"+sumTotal+"</td>");
            out.println("<td align=right>"+sumActive+"</td>");
            out.println("<td align=right>"+sumDeaths+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<hr>");
            out.println("<h4><a href=stadmindashboard.jsp>Dashboard</a><h4>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
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
