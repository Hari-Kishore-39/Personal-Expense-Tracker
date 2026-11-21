import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCategories {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/expense_tracker";
		String userName = "root";
		String password = "root";
		
		String sql = "SELECT * FROM categories";
		
		try {
			
			Connection con = DriverManager.getConnection(url,userName,password);
			System.out.println("DataBase Connected");
			
			PreparedStatement ps = con.prepareStatement(sql);
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				
				System.out.println(id+" | "+name+" | "+type);
			}
			
			rs.close();
			ps.close();
			con.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

}
