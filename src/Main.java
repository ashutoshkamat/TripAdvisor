import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;


public class Main {

public static void main(String[] args) throws SQLException, ClassNotFoundException {

	Scanner sc =new Scanner(System.in);
	String city = new String();
	System.out.println("Enter your city");
	city=sc.nextLine();
	
	Class.forName("com.mysql.jdbc.Driver");
	String constr = "jdbc:mysql://localhost/tourmanagement";
	Connection con = (Connection)DriverManager.getConnection(constr,"root","root");
	Statement sat=con.createStatement();
	String query = "select *  from hotels where city='"+city+"'";
	ResultSet rs=sat.executeQuery(query);
	Vector rate =new Vector();
	Vector cityv=new Vector();
	Integer i=new Integer(0);
	
	
	
	try {
			rs.next();
			if(rs.getString("name").equals(null))
			{
				throw new Exception();
			}
			rs.previous();	
			while(rs.next())
			{	
		
				cityv.add(rs.getString("name"));
				i=new Integer(rs.getInt("rate"));
				rate.add(i);
			}
		
			
			
			
			for(int j=0;j<rate.size();j++)
			{
				
				System.out.println(cityv.elementAt(j)+"  "+rate.elementAt(j));
			}
	}
	catch(Exception e)
	{
		System.out.println("City Not Found");
	}
	con.close();
	

}
}
