package exercise2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class fruitsSearch {

	public static void main(String[] args) {
//        try {
//            Class.forName("org.h2.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new IllegalStateException("ドライバのロードに失敗しました");
//        }
        
        Connection con = null;
        try {
        	con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/h2db/test", "sa", "");
            
            // (0) テーブルクリア
            updateBy(con, "delete from fruits");
            // (1) insert
            setFruit(con, 1, "apple", 100, "winter");
            setFruit(con, 2, "grape", 500, "autumn");
            setFruit(con, 3, "orange", 298, "summer");
            // (2) select *
            selectBy(con, "select * from fruits");
            // (3) select order by
            selectBy(con, "select * from fruits order by id desc");
            // (4)  select like;
            selectBy(con, "select * from fruits where name like '%p%'");
            // (5) select count(*) from fruits;
            PreparedStatement psmt = con.prepareStatement("select count(*) from fruits");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
            	System.out.println("レコード数 " + rs.getInt(1));
            }
            System.out.println();
            // (5) Update
            updateBy(con, "update fruits set price = 200 where name = 'apple'");
            // (6) Delete
            updateBy(con, "delete from fruits where name = 'orange'");
            // 最後に確認
            selectBy(con, "select * from fruits");          
        } catch (SQLException e) {       // 接続失敗時
            e.printStackTrace();
        } finally {                                    // 最後にデータベースを切断
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {  // 切断失敗時
                e.printStackTrace();
            }
        }
	}
	
	public static void setFruit(Connection con,
			int id, String name, int price, String season) throws SQLException {
        PreparedStatement psmt = con.prepareStatement(
        		"insert into fruits (id, name, price, season) values (?, ?, ?, ?)");
        psmt.setInt(1, id);
        psmt.setString(2, name);
        psmt.setInt(3, price);
        psmt.setString(4, season);
        int st = psmt.executeUpdate();
        if (st == 0) {
            System.out.println("変更できませんでした");
        }
	}
	
	public static void selectBy(Connection con, String str) throws SQLException {
		PreparedStatement psmt = con.prepareStatement(str);
		ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
        	System.out.println(rs.getInt("ID") + " " + rs.getString("NAME") + " "
        			+ rs.getInt("PRICE") + " " + rs.getString("SEASON"));
        }
        System.out.println();
	}
	
	public static void updateBy(Connection con, String str) throws SQLException {
        PreparedStatement psmt = con.prepareStatement(str);
        int st = psmt.executeUpdate();
        if (st == 0) {
        	System.out.println("失敗" + str);
        }
		
	}
}
