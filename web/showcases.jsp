<%!
    public int x;
    public int deathPercentage(int totalCases, int totalDeaths){
        int dp=totalDeaths*100/totalCases;
        return dp;
    }
%>
<%
    int y;
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
                <th>Sno</th><th>State</th><th>Date</th><th>Total</th><th>Active</th><th>Deaths</th><th>%</th>
            </tr>
 <%
            while(rs.next()){
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                int s4=rs.getInt(4);  //total
                String s5=rs.getString(5);
                String s6=rs.getString(6);  //deaths
                //int per=deathPercentage(s4,s6);
 %>             
            <tr>
                <td><%=s1%></td>
                <td><%=s2%></td>
                <td><%=s3%></td>
                <td><%=s4%></td>
                <td><%=s5%></td>
                <td><%=s6%></td>
                <td><%=deathPercentage(s4,Integer.parseInt(s6))%></td>
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