import java.sql.*;

class Employees extends Row {
	public String firstName, lastName;
	public char middleInitial;
	public int department;
	public boolean isManager = false;
	public boolean disabled;
	public PassCode passCode;

	public Employees() throws SQLException, ClassNotFoundException { 
		this.firstName = "";
		this.lastName = "";
		this.middleInitial = '\0';
		this.disabled = false;
		this.department = 0;
		this.isManager = false;
		this.passCode = null;
	}

	public String getTableName() { return "employees"; }

	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getFirstName(){ return this.firstName; }

	public void setMiddleInitial(char middleInitial) { this.middleInitial = middleInitial; }
	public char getMiddleInitial(){ return this.middleInitial; }

	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getLastName(){ return this.lastName; }

	public void setAsManager(){ this.isManager = true;	}

	public void disable() { }

	public boolean isDisabled() {
		return this.disabled;
	}

	public String getPassword() throws SQLException, ClassNotFoundException {
		PassCode passCode = PassCode.getEmployeePassCode(this.getID());
		return passCode.getCode();
	}

	public void setDepartment(int department) {
		this.department = department;
//		Departments department = new Departments();
//		department.setID(department_id);
//		department.refresh();
//		this.department = department;
	}
	public int getDepartment() {
		return this.department;
	}

	public TimeLog getLastAction() throws SQLException, ClassNotFoundException, NonexistantValueException {
		TimeLog timeLog = TimeLog.findLastEntryByEmployeeID(this.getID());
		return timeLog;
	}

	public void setToBreak(boolean onBreak) throws SQLException, ClassNotFoundException, NonexistantValueException {
		TimeLog timeLog = new TimeLog();
			timeLog.setEmployeeID(this.getID());
			timeLog.setInsertDataTime();

		if(onBreak == true)	{
			timeLog.setActionType("break");
		} else {
			timeLog.setActionType("in");
		}

		timeLog.save();
	}

	public void clockOn () throws SQLException, ClassNotFoundException, NonexistantValueException {
		TimeLog timeLog = new TimeLog();
	}
	public void clockOff () {
	}

	public boolean isClockedOn() throws SQLException, ClassNotFoundException, NonexistantValueException {
		return (getLastAction().getActionName().equals("in"));
	}
	public boolean isOnBreak() throws SQLException, ClassNotFoundException, NonexistantValueException {
		return (getLastAction().getActionName().equals("break"));
	}

	public static Employees[] findByName(String name) throws SQLException, ClassNotFoundException, NonexistantValueException {
		Employees self = new Employees();
		ResultSet resultSet = self.query(Row.selectQuery(self.getTableName(), "first_name || ' ' || last_name", name));
		if(Row.getNumRows(resultSet) == 0) {
			throw new NonexistantValueException("first_name/last_name", name);
		}
		Employees[] employees = new Employees[Row.getNumRows(resultSet)];
		Employees e;
		do {
			e = new Employees();
			e.setFromRow(resultSet);
			employees[resultSet.getRow()-1] = e;
		} while(resultSet.next());
		return employees;
	}

	public static Employees findOneByName(String name) throws SQLException, ClassNotFoundException, NonexistantValueException {
		return Employees.findByName(name)[0];	
	}
	public void setFromRow(ResultSet databaseValues) throws SQLException, ClassNotFoundException, NonexistantValueException {
		this.setID(databaseValues.getInt("id"));
		this.setFirstName(databaseValues.getString("first_name"));
		String middleInitial;
		if((middleInitial = databaseValues.getString("middle_initial")) != null) {
			this.setMiddleInitial(middleInitial.charAt(0));
		}
		this.setLastName(databaseValues.getString("last_name"));
		this.setDepartment(databaseValues.getInt("department_id"));
		
		if(databaseValues.getBoolean("is_manager") == true) {
			this.setAsManager();
		}
//			ResultSetMetaData meta = databaseValues.getMetaData();
//			for(int i=1; i<meta.getColumnCount(); i++) {
//				if(meta.getColumnTypeName(i) == "String") {
//					this.set(meta.getColumnName(i), databaseValues.getString(i));
//				}
//			}
	}
}
