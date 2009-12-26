import java.sql.*;

public class Actions extends Row {
	public String action;

	public String getTableName() { return "\"actions\""; }
	public Actions () throws SQLException, ClassNotFoundException { 
		super(0, "");	
	}
	public Actions (int id) throws SQLException, ClassNotFoundException {
		SelectQueryBuilder query = new SelectQueryBuilder();
			query.columns = "action";
			query.tableName = this.getTableName();
			query.whereColumn = this.getPrimaryKey();
			query.whereValue = id;
			query.limitValue = 1;
			this.setFromRow(this.query(query));
	}
	public Actions (int id, String action) throws SQLException, ClassNotFoundException { 
		this.id = id;
		this.action = action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return this.action;
	}

	public static Actions findByName(String action) throws SQLException, ClassNotFoundException, NonexistantValueException {
		Actions self = new Actions();
		ResultSet resultSet = self.query(Row.selectQuery(self.getTableName(), "action", action));
		if(Row.getNumRows(resultSet) == 0) {
			throw new NonexistantValueException("action", action);
		}
		self.setFromRow(resultSet);
		return self;
	}

	public void setFromRow(ResultSet databaseValues) throws SQLException {
		this.setID(databaseValues.getInt(this.getPrimaryKey()));
		this.setAction(databaseValues.getString("action"));
	}
}
