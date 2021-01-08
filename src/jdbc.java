import java.sql.*;
public class jdbc {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			//鍔犺浇MYSQL JDBC椹卞姩绋嬪簭 聽聽
			//Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		}
		catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true","root","WAMM0609dd");
			//java杩欎釜绌哄～鍐欑殑鏄綘鑷繁璁剧殑瀵嗙爜
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from websites");
			//user 涓轰綘琛ㄧ殑鍚嶇О锛屽彲浠ュ湪MySQL鍛戒护琛岀敤show tables锛涙樉绀�
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}
		catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}	
	}
}
