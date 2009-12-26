import java.sql.*;
import java.util.Date;
import java.text.DateFormat;

/*CREATE TABLE time_log (
	id SERIAL PRIMARY KEY,
	employee_id INTEGER REFERENCES employees (id) NOT NULL,
	date_time TIMESTAMP WITH time zone NOT NULL,
	action_type INTEGER REFERENCES actions (id),
	comment VARCHAR
);*/

public class TimeLog extends Row {
	public Employees employee;
	public int employeeID;
	public Date dateTime;
	public Actions action;
	public int actionType;
	public String comment;
	
	public String getTableName() { return "time_log"; }
	public TimeLog() throws SQLException, ClassNotFoundException { 
		this.id = 0;
		this.employeeID = 0;
		this.dateTime = new Date();
		this.action = null;
		this.actionType = 0;
		this.comment = "";
	}
	public void setInsertDataTime() {
//		double destinationTimestamp;
//		SntpClient sntpClient = SntpClient.main(new String("0.us.pool.ntp.org")[]);	
//		this.date_time = NtpMessage.timestampToString();
//		NtpMessage.timestampToString(msg.receiveTimestamp));
	}
	public void setDateTimeFromDatabase(Date dateTime) { this.dateTime = dateTime;	}
	public Date getDataTime() { return this.dateTime; }
	public String getTime() { 
		DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);	
		return format.format(this.dateTime.getTime()); 
	}

	public void setEmployeeID(int id) { this.employeeID = id; }
	public int getEmployeeID() { return this.employeeID; }

	public void setAction(Actions action) { this.action = action; }
	public void setActionType(int actionType) { this.actionType = actionType; }
	public void setActionType(String actionType) { 
	}
//	public void setActionType(String actionType) throws SQLException, ClassNotFoundException, NonexistantValueException {
//		this.setActionType(Actions.findByName(actionType));
//	}
//	public void setActionType(int actionType) throws SQLException, ClassNotFoundException, NonexistantValueException {
//		this.setActionType(new Actions(actionType));
//	}
	public Actions getAction() { return this.action; }
	public int getActionType() { 
//		if(this.actionType == 0) {
//
//		} else {
			return this.actionType;
//		}
	}
	public String getActionName() throws SQLException, ClassNotFoundException, NonexistantValueException { 
		Actions action = new Actions();
		SelectQueryBuilder query = new SelectQueryBuilder();
			query.columns = "action";
			query.tableName = action.getTableName();
			query.whereColumn = action.getPrimaryKey();
			query.whereValue = this.getActionType();
			query.limitValue = 1;
			
		ResultSet resultSet = query(query);

		if(Row.getNumRows(resultSet) == 0) {
			throw new NonexistantValueException("action", this.actionType);
		}
		return resultSet.getString("action");
	}

	public void setComment(String comment){ this.comment = comment; }
	public String getComment() { return this.comment; }

	public static TimeLog findLastEntryByEmployeeID(int employee_id) throws SQLException, ClassNotFoundException, NonexistantValueException {
		TimeLog self = new TimeLog();
		SelectQueryBuilder query = Row.selectQuery(self.getTableName(), "employee_id", employee_id, "employee_id", 1);
		ResultSet resultSet = self.query(query);
		
		if(Row.getNumRows(resultSet) == 0) {
			throw new NonexistantValueException("employee_id", employee_id);
		}
		self.setFromRow(resultSet);
		return self;
	}

	public void setFromRow(ResultSet databaseValues) throws SQLException, ClassNotFoundException, NonexistantValueException {
		this.setID(databaseValues.getInt(this.getPrimaryKey()));
		this.setEmployeeID(databaseValues.getInt("employee_id"));
		this.setDateTimeFromDatabase(databaseValues.getTimestamp("date_time"));
		this.setActionType(databaseValues.getInt("action_type"));
		this.setComment(databaseValues.getString("comment"));
	}
}
