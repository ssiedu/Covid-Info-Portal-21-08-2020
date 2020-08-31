<html>
    <body>
        <h3>State-Account-Creation-Form</h3>
        <hr>
        <form action="StateAccountCreationServlet" method="get">
            <table border="0">
            <tr>
                <td>Userid</td><td><input type="text" name="uid"/></td>
            </tr>
            <tr>
                <td>Password</td><td><input type="password" name="password"/></td>
            </tr>
            <tr>
                        <td>State</td><td><select name="state">
                                <option>AP</option>
                                <option>MP</option>
                                <option>MH</option>
                                <option>RJ</option>
                    </select></td>
            </tr>
            <tr>
                <td><input type="submit" value="Create-Account"/></td><td><input type="reset"/></td>
            </tr>
            </table>
        </form>
        <hr>
        <a href="index.jsp">Home</a>
    </body>
</html>
