import java.sql.*;

class PassCode extends Row {
	public String code;
	public Employees employee;

	public PassCode() throws SQLException, ClassNotFoundException {
	}

	public String getPrimaryKey() { return "id"; }
	public String getTableName() { return "\"pass-code\""; }

	public void setCode(String code) { this.code = code; }
	public String getCode(){ return this.code; }

	public void setEmployee(int employee_id) throws SQLException, ClassNotFoundException {
	}
	public Employees getEmployee() { return this.employee; }

	public static PassCode getEmployeePassCode(int employee_id) throws SQLException, ClassNotFoundException {
		PassCode self = new PassCode();
		ResultSet resultSet = self.query(Row.selectQuery(self.getTableName(), "employee_id", employee_id));
		self.setFromRow(resultSet);
		return self;
	}

	public void setFromRow(ResultSet databaseValues) throws SQLException, ClassNotFoundException {
		this.setID(databaseValues.getInt("id"));
		this.setCode(databaseValues.getString("code"));
		this.setEmployee(databaseValues.getInt("employee_id"));
	}
}
