<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body>
        <h3>Submit-Info-For-Today</h3>
        <hr>
        <form action="SaveInfo">
            <pre>
            TotalCases  <input type="text" name="total"/>
            ActiveCases <input type="text" name="active"/>
            TotalDeaths <input type="text" name="deaths"/>
                        <input type="submit" value="Submit"/>
            </pre>
        </form>
        <hr>
        <a href="stadmindashboard.jsp">Dashboard</a>
    </body>
</html>
