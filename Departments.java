public class Departments extends Row {
	public String department;

	public Departments(){
		super(0, "");
	}

	public Departments (int id, String department) {
		this.id = id;
		this.department = department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartment() {
		return this.department;
	}
}
