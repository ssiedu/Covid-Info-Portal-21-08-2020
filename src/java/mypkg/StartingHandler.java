package mypkg;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartingHandler implements ServletContextListener {
    
    private Connection con;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //we need to establish a db connection
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coviddata", "root", "root");
            //how can we make this con object available for the whole applicatin?
            //we are storing this con object to context , so that servlets can access it from context obj
            ServletContext context=sce.getServletContext();
            //storing connection obj to context
            context.setAttribute("dbcon", con);
            
            System.out.println("Connected Successfully In Context Initialized..."+con);
        }catch(Exception e){
            System.out.println("Exception In Connecting ................"+e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //we need to close the db connection
        try{
            System.out.println("Connection Closed Successfully In Context Destroyed.....");
            con.close();
        }catch(Exception e){
            System.out.println("Exception In Connecting ................"+e);
        }
    }
}
