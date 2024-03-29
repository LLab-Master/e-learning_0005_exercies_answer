package exercise3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitsDAO {
	private Connection con;

	public FruitsDAO(ConnectionManager cm) {
		super();
		this.con = cm.getConnection();
	}

	// record 全消去
	public int cleanRecords() {
		int result;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("delete from fruits");
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Delete 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	// レコード追加
	public int insertRecode(Fruits fruits) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("insert into fruits (id, name, price, season) values (?, ?, ?, ?)");
			psmt.setInt(1, fruits.getId());
			psmt.setString(2, fruits.getName());
			psmt.setInt(3, fruits.getPrice());
			psmt.setString(4, fruits.getSeason());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("INSERT 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	// 価格の更新
	public int updatePrice(String name, int price) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("update fruits set price = ? where name = ?");
			psmt.setString(2, name);
			psmt.setInt(1, price);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Update 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	// NAME でレコード削除
	public int deleteRecord(String name) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("delete from fruits where name = ?");
			psmt.setString(1, name);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Delete 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	// Select *
	public List<Fruits> selectAll() {
		return selectBy(con, "select * from fruits");
	}

	// Select(ID降順)
	public List<Fruits> selectDesc() {
		return selectBy(con, "select * from fruits order by id desc");
	}

	// Like 検索
	public List<Fruits> selectLike(String str) {
		List<Fruits> fruitsList = new ArrayList<Fruits>();
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("select * from fruits where name like ?");
			psmt.setString(1, str);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Fruits fruits = resultSetToFruits(rs);
				fruitsList.add(fruits);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECT 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return fruitsList;
	}

	// レコード登録数
	public int recordNum() {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("select count(*) from fruits");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECT 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return result;
	}

	// Select 系共通メソッド
	private List<Fruits> selectBy(Connection con, String str) {
		List<Fruits> fruitsList = new ArrayList<Fruits>();
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement(str);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Fruits fruits = resultSetToFruits(rs);
				fruitsList.add(fruits);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECT 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return fruitsList;
	}

	// ResultSet -> Fruits
	private Fruits resultSetToFruits(ResultSet rs) throws SQLException {
		int id = rs.getInt("ID");
		String name = rs.getString("NAME");
		int price = rs.getInt("PRICE");
		String season = rs.getString("SEASON");
		return new Fruits(id, name, price, season);
	}
}
