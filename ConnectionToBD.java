package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionToBD {

	public ConnectionToBD() {
		// TODO Auto-generated constructor stub
	}
	public static Connection connecter() {
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();

		}
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/Transactions", "user",
					"admin");

		} catch (SQLException e) {
			String st = "Connection Failed!";
			  JOptionPane.showMessageDialog(null,st);

			//System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			;
		}
		if (connection != null) {
			//String st1 ="You made it, take control your database now!";
			//JOptionPane.showMessageDialog(null,st1);
			//System.out.println("You made it, take control your database now!");
		} else {
			String st2 ="Failed to make connection!";
			JOptionPane.showMessageDialog(null,st2);
			//System.out.println("Failed to make connection!");
		}
       return connection;
		
	}
	public static int IncrementIDTrans()
	{
        Connection c=null;
        try 
        {
        	
        	c = connecter();
        	// Creating Statement for query execution
            Statement stmt = c.createStatement();
            // creating Query String                
            String query = "select max(id) from transaction";
            // excecuting query
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            int id = rs.getInt(0);	
			JOptionPane.showMessageDialog(null,String.valueOf(id));

            }
        	
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         }
        
		return 0;
		
	    
	}
	

}
