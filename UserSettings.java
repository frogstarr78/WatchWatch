class UserSettings extends Row {
	public String nameOrdering;
	public String ntpServer;
	public int employee;
	public boolean showSummary;

	public UserSettings() { 
		this(0, false, "last, first", "1.us.pool.ntp.org", 0);	
	}

	public UserSettings(int id, String nameOrdering, String ntpServer, boolean showSummary, int employee) {
		this.id = id;
		this.nameOrdering = nameOrdering;
		this.ntpServer = ntpServer;
		this.showSummary = showSummary;
		this.employee = employee;
	}

	public void setShowSummary(boolean showSummary) { this.showSummary = showSummary; }
	public String showSummary(){ return this.showSummary; }

	public void setEmployee(String lastName) {
		Employees employee = new Employees();
		employee = employee.findByID(employee);
//		this.department = department.getID();
	}
	public String getEmployee() {
		Employees employee = new Employees();
		employee.findByID(this.employee);
		if(employee.isValid()) {
			return employee.getLastName() + " " + employee.getFirstName();
		}
		return null;
	}
}
