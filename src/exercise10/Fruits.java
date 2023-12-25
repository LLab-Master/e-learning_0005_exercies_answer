package exercise10;

public class Fruits {
	int id;
	String name;
	int price;
	String season;

	public Fruits(int id, String name, int price, String season) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.season = season;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public void print() {
		System.out.printf("%2d %10s %5d %10s\n", this.getId(), this.getName(), this.getPrice(), this.getSeason());
	}

}
