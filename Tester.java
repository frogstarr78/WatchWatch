import java.sql.*;
import java.security.*;

public class Tester {

	/**
	 * @param args
	 */

	public static void write(Object what) {
		System.out.print(what);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		write(MD5Password.encode("a"));
/*		try {
//			Employees[] employees = Employees.findByName("Rick");
			Employees[] employees = Employees.findByName("Scott Noel-Hemming");
			for(int i=0; i<employees.length; i++) {
				write("name '" + employees[i].getFirstName() + "'" + '\n');
			}
		} catch (SQLException e) {
			write("SQL Error " + e.toString());
		} catch (ClassNotFoundException ce){
			write("Class Error " + ce.toString());
		} catch (UsernameNotFoundException ue) {
			write("Username Error " + ue.toString());
		}
//		for(int i=0; i< employees.length; i++) {
//			write(employees[i].getFirstName());	
//			write(employees[i].getLastName());	
//		}
		
		Connection dbConn;

		try{
			Class.forName("org.postgresql.Driver");
			try{
				String host = "127.0.0.1";
				String db = "temps_rondin";
				String url = "jdbc:postgresql://" + host + "/" + db + "";
				String user = "scott";
				String password = "Un1-Ball";
				dbConn = DriverManager.getConnection(url, user, password);
				Statement st = dbConn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM classifications");
				while (rs.next()) {
					ResultSetMetaData meta = rs.getMetaData();
					for(int i=1; i<meta.getColumnCount(); i++){
//						System.out.println("(" + meta.getColumnTypeName(i) + ") " + meta.getColumnName(i) + " = " + rs.getString(i));
					}
				}
				rs.close();
				st.close();
			} catch(SQLException e) {
				System.out.println("Error while connecting to database");	
				System.out.println(e);	
			}
		} catch(ClassNotFoundException e){
			System.out.println("Couldn't find the class");	
			System.out.println(e);
		}*/
	}

}
