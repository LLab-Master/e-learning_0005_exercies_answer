package finalExercise;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {

	public static void main(String[] args) {
		// Open connection
		ConnectionManager cm = new ConnectionManager();		
		// DAO
		EmployeeDAO employeeDao = new EmployeeDAO(cm);
		// 処理本体
		mainLoop: while (true) {
			System.out.println("1.社員一覧");
			System.out.println("2.社員登録");
			System.out.println("3.社員情報更新");
			System.out.println("4.社員削除");
			System.out.println("9.終了");
			System.out.print("操作を選択してください-->");
			int keyin = new Scanner(System.in).nextInt();
			// 処理
			switch (keyin) {
			case 1:
				listAll(employeeDao);
				break;
			case 2:
				addEmployee(employeeDao);
				break;
			case 3:
				updateEmployee(employeeDao);
				break;
			case 4:
				deleteEmployee(employeeDao);
				break;
			case 9:
				System.out.println("終了します");
				break mainLoop;
			default:
				System.out.println("正しい番号を入力してください");
				break;
			}
		}
		cm.closeConnection();
	}

	private static void listAll(EmployeeDAO employeeDAO) {
		printAllEmployee(employeeDAO.selectAll());
	}
	
	private static void addEmployee(EmployeeDAO employeeDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("社員登録");
		System.out.print("社員名を入力してください-->");
		String name = scanner.nextLine();
		System.out.print("年齢を入力してください-->");
		int age = Integer.parseInt(scanner.nextLine());
		System.out.print("入社日を入力してください-->");
		Date hierDate = Date.valueOf(scanner.nextLine());
				
		Employee employee = new Employee(-1, name, age, hierDate); // id はダミー
		employeeDAO.insertEmployee(employee);
		
		System.out.println("登録が完了しました");	
	}
	
	private static void updateEmployee(EmployeeDAO employeeDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("社員情報更新");
		System.out.print("更新するIDを入力してください-->");
		int id = Integer.parseInt(scanner.nextLine());
		Employee employee = employeeDAO.selectEmployeeByID(id);
		printOneEmployee(employee);
		
		System.out.print("社員名を入力してください-->");
		String name = scanner.nextLine();
		System.out.print("年齢を入力してください-->");
		int age = Integer.parseInt(scanner.nextLine());
		System.out.print("入社日を入力してください-->");
		Date hierDate = Date.valueOf(scanner.nextLine());
				
		Employee new_employee = new Employee(id, name, age, hierDate);
		employeeDAO.updateEmployee(new_employee);
		
		System.out.println("更新が完了しました");	
		
	}
	
	private static void deleteEmployee(EmployeeDAO employeeDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("社員削除");
		System.out.print("更新するIDを入力してください-->");
		int id = Integer.parseInt(scanner.nextLine());
		Employee employee = employeeDAO.selectEmployeeByID(id);
		printOneEmployee(employee);
		
		System.out.println("よろしいですか？ (Y/N)-->");
		String keyin = scanner.nextLine();
		if (keyin.equals("y") || keyin.equals("Y")){
			employeeDAO.deleteEmployeeByID(id);
			System.out.println("削除が完了しました");	
		} else {
			System.out.println("削除していません");	
		}		
	}
	
	private static void printAllEmployee(List<Employee> employeeList) {
		System.out.printf("%3s %10s %4s %10s\n", "ID", "氏名", "年齢", "入社日");
		System.out.println("-----------------------------------");
		for (Employee employee : employeeList) {
			employee.print();
		}
		System.out.println();
	}

	private static void printOneEmployee(Employee employee) {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee);
		printAllEmployee(employeeList);
	}
}
