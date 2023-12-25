package exercise10;

import java.util.List;

public class fruitsSearch2 {

	public static void main(String[] args) {
		// Open connection
		ConnectionManager cm = new ConnectionManager();
		// DAO
		FruitsDAO fruitsDao = new FruitsDAO(cm);
		// (0) record clear
		fruitsDao.cleanRecords();
		
		// (1) Insert
		fruitsDao.insertRecode(new Fruits(1, "apple", 100, "winter"));
		fruitsDao.insertRecode(new Fruits(2, "grape", 500, "autumn"));
		fruitsDao.insertRecode(new Fruits(3, "orange", 298, "summer"));

		// (2) Select All
		printAll(fruitsDao.selectAll());

		// (3) Select order by
		printAll(fruitsDao.selectDesc());

		// (4) Select LIKE
		printAll(fruitsDao.selectLike("%p%"));

		// (5) Select count(*)
		System.out.println("Record num: " + fruitsDao.recordNum());
		System.out.println();

		// (6) Update
		fruitsDao.updatePrice("apple", 200);

		// (7) Delete
		fruitsDao.deleteRecord("orange");

		// 最後に確認
		printAll(fruitsDao.selectAll());

		// Close connection
		cm.closeConnection();
	}

	private static void printAll(List<Fruits> fruitsList) {
		for (Fruits fruits : fruitsList) {
			fruits.print();
		}
		System.out.println();
	}
}
