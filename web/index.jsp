<html>
    <body>
        <h3>Covid-Information-Portal</h3>
        <hr>
        <form action="VerifyUser" method="get">
            <table border="0">
            <tr>
                <td>Email/Uid</td><td><input type="text" name="uid"/></td>
            </tr>
            <tr>
                <td>Password</td><td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>UserType</td><td><select name="utype"><option>enduser</option><option>state-admin</option><option>super-admin</option></select></td>
            </tr>
             <tr>
                 <td>Remember-Me</td><td><input type="checkbox" name="save" value="yest"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Login"/></td><td><input type="reset"/></td>
            </tr>
            </table>
        </form>
        <hr>
        <a href="registration.jsp">New-User-Registration</a><br>
        <a href="helpline.jsp">Covid-Helpline-Nos</a>
    </body>
</html>
