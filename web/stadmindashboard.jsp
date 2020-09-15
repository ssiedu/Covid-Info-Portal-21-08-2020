<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body>
        <h3>State-Admin-Dashboard</h3>
        <hr>
        <pre>
        <a href="entrypage.jsp">Add-Info-For-Today</a>
        <a href="">Update-Info</a>
        <a href="StateDataServlet">View-Info-For-Own-State</a>
        <A href="AllDataServlet">View-Info-For-All-States</a>
        <a href="EndSession">Logout</a>
        </pre>
        <hr>
    </body>
</html>
