import java.sql.*;
import java.util.Hashtable;
import java.util.ArrayList;

public abstract class Row implements MetaDescription {
	public int id;
	protected String host = "127.0.0.1";
//	protected String host = "68.113.7.135";
	protected String db = "temps_rondin";
	protected String url = "jdbc:postgresql://" + host + "/" + db + "";
	protected String user = "scott";
	protected String password = "Un1-Ball";
	public Connection dbConn;
	protected Statement queryStatement;
	protected ResultSet resultSet;
	protected Hashtable hash;
	public static ArrayList columns = new ArrayList(1);
//	public ID id;
//	public varcharColumn department;

	public String getPrimaryKey() { return "id"; }
	public abstract String getTableName();
	public abstract void setFromRow(ResultSet databaseValues)
		throws java.sql.SQLException, ClassNotFoundException, NonexistantValueException; 
	public Row () throws SQLException, ClassNotFoundException {
		this(0, "");

		Class.forName("org.postgresql.Driver");
		dbConn = DriverManager.getConnection(url, user, password);
	}
	public Row (int id, String column) {
//	public Row (ID id, varcharColumn department) {
	}

	public void setID(int id) { this.id = id; }
	public int getID() { return this.id; }

	public void save() {
	}

	public boolean isValid() {
		return (this.id != 0);
	}

	public static SelectQueryBuilder selectQuery(String fromTable, String whereColumn, Object isValue, String orderByColumn, int limitValue)
		throws SQLException {
			SelectQueryBuilder query = new SelectQueryBuilder();
				query.columns = "*";
				query.tableName = fromTable;
				query.whereColumn = whereColumn;
				query.whereValue = isValue;
				query.orderByColumn = orderByColumn;
				query.limitValue = limitValue;
			return query;
	}
	public static SelectQueryBuilder selectQuery(String fromTable, String whereColumn, Object isValue)
		throws SQLException {
			SelectQueryBuilder query = new SelectQueryBuilder();
				query.columns = "*";
				query.tableName = fromTable;
				query.whereColumn = whereColumn;
				query.whereValue = isValue;
			return query;
	}

	public ResultSet query(String query) throws SQLException {
		queryStatement = dbConn.createStatement(ResultSet.FETCH_UNKNOWN, ResultSet.CONCUR_READ_ONLY);
		resultSet = queryStatement.executeQuery(query.toString());
		resultSet.first();
		return resultSet;
	}
	public ResultSet query(SelectQueryBuilder query) throws SQLException {
		return this.query(query.toString());
	}
	public static String getSimpleSelectQuery(String column, String fromTable, String whereColumn, Object isValue) {
		return "SELECT \"" + column + "\" FROM \"" + fromTable + "\" WHERE " + whereColumn + " = '" + isValue + "';";
	}

	public static int getNumRows(ResultSet resultSet) throws SQLException {
		resultSet.last();
		int result = resultSet.getRow();
		resultSet.first();
		return result;
	}
}
