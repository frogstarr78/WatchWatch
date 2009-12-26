public class PayTypes extends Row {
	public String type;

	public PayTypes (int id, String type) {
		this.id = id;
		this.type = type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}

}
