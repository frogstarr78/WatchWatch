class Classifications extends Row {
/*CREATE TABLE classifications (
	id SERIAL PRIMARY KEY,
	classification VARCHAR NOT NULL,
	pay_type INTEGER REFERENCES pay_types(id) NOT NULL*/
	public String classification;
	public int payType;

	public Classifications() {
		this(0, "", 0);	
	}

	public Classifications(int id, String classification, int payType) {
		this.id = id;
		this.classification = classification;
		this.payType = payType;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getClassification(){
		return this.classification;
	}

	public String getPayType() {
		PayTypes payType = new PayTypes();
		payType.findByID(this.payType);
		if(payType.isValid()) {
			return payType.getType();
		}
		return null;
	}
}
