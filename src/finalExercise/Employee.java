package finalExercise;

import java.sql.Date;

public class Employee {
	int id;
	String name;
	int age;
	Date hierDate;
	
	public Employee(int id, String name, int age, Date hierDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.hierDate = hierDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getHierDate() {
		return hierDate;
	}
	public void setHierDate(Date hierDate) {
		this.hierDate = hierDate;
	}
	
	public void print() {
		System.out.printf("%3d %10s %4d %10s\n", 
				this.getId(), this.getName(), this.getAge(), this.getHierDate());
	}
}
