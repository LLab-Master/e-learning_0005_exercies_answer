package finalExercise;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private Connection con;
	private int lastID = 0;

	public EmployeeDAO(ConnectionManager cm) {
		super();
		this.con = cm.getConnection();
	}

	public List<Employee> selectAll() {
		List<Employee> employeeList = new ArrayList<Employee>();
		PreparedStatement psmt = null;
		try {
			psmt = this.con.prepareStatement("select * from employee");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Date hierDate = rs.getDate("HIER_DATE");
				Employee employee = new Employee(id, name, age, hierDate);
				employeeList.add(employee);
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
		return employeeList;
	}

	public int insertEmployee(Employee employee) {
		int result = 0;
		PreparedStatement psmt = null;

		try {
			psmt = this.con.prepareStatement("insert into employee (id, name, age, hier_date) values (?, ?, ?, ?)");
			psmt.setInt(1, this.getNewID());
			psmt.setString(2, employee.getName());
			psmt.setInt(3, employee.getAge());
			psmt.setDate(4, employee.getHierDate());
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

	public int getNewID() {
		// Return new id.
		// lastID is auto-incremented.
//		int newID = 0;
//		if (this.lastID == 0) {
//			// lastID の初期化
//			this.lastID = this.selectMaxID();
//		}
//		// update lastID
//		newID = this.lastID + 1;
//		this.lastID = newID;
//
//		return newID;
		
		return selectMaxID() + 1;
	}

	public int selectMaxID() {
		int maxID = 0;
		PreparedStatement psmt = null;
		try {
			psmt = this.con.prepareStatement("select max(id) from employee");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				maxID = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECT MAX 失敗", e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("PreparedStatement close失敗", e);
			}
		}
		return maxID;
	}

	public Employee selectEmployeeByID(int id) {
		Employee employee = null;
		PreparedStatement psmt = null;
		try {
			psmt = this.con.prepareStatement("select * from employee where id = ?");
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Date hierDate = rs.getDate("HIER_DATE");
				employee = new Employee(id, name, age, hierDate);
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
		return employee;
	}

	public int updateEmployee(Employee employee) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = this.con.prepareStatement("update employee set name = ?, age = ?, hier_date = ? where id = ?");
			psmt.setInt(4, employee.getId());
			psmt.setString(1, employee.getName());
			psmt.setInt(2, employee.getAge());
			psmt.setDate(3, employee.getHierDate());
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

	public int deleteEmployeeByID(int id) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			psmt = this.con.prepareStatement("delete from employee where id = ?");
			psmt.setInt(1, id);
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
}
