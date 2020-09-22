<%
    String state=request.getParameter("t1");
    String contact="";
    switch(state){
        case "mp":
            contact="31233434";
        break;
        case "mh":
            contact="23232322";
        break;
        case "rj":
            contact="54545333";
        break;
        default :
            contact="12345678";
        break;
    }
%>
<html>
    <body>
        <h3>HelpLine Number For Your State : <% out.println(state); %> </h1>
        <hr>
        Number:     <% out.println(contact);%>
        <hr>
        <a href="index.jsp">Home</a>
    </body>
</html>
