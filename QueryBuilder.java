abstract class QueryBuilder {

	public String columns;
	public String tableName;
	public String whereColumn;
	public Object whereValue;
	public String orderByColumn;
	public int limitValue = 0;

	public abstract String toString();
	public boolean hasTableName() { return (tableName != null); }
	public boolean hasWhereColumn() { return (whereColumn != null); }
	public boolean hasWhereValue() { return (whereValue != null); }
	public boolean hasOrderByColumn() { return (orderByColumn != null); }
	public boolean hasLimit() { return (limitValue != 0); }
}
