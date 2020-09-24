<%
    String sql="SELECT * FROM covidinfo";
    java.sql.Connection con=mypkg.Utility.connect();
    java.sql.PreparedStatement ps=con.prepareStatement(sql);
    java.sql.ResultSet rs=ps.executeQuery();
%>
<html>
    <body>
        <h3>Covid Data</h3>
        <hr>
        <table border="2">
            <tr>
                <th>Sno</th><th>State</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th>
            </tr>
 <%
            while(rs.next()){
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                String s4=rs.getString(4);
                String s5=rs.getString(5);
                //String s6=rs.getString(6);
 %>
            <tr>
                <td><%=s1%></td>
                <td><%=s2%></td>
                <td><%=s3%></td>
                <td><%=s4%></td>
                <td><%=s5%></td>
                <td><%=rs.getString(6)%></td>
             </tr>
<%
    }
%>
        </table>
        <hr>
        <a href="userdashboard.jsp">Dashboard</a>
    </body>
</html>
<%
    con.close();
%>