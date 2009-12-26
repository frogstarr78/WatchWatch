class SelectQueryBuilder extends QueryBuilder {
	public String orderByOrder = "DESC";
	public SelectQueryBuilder () {
		this( null, null, null, null, null, 0);
	}
	public SelectQueryBuilder (String column, String fromTable, String whereColumn, Object isValue, String orderByColumn, int limitValue) {
		this.columns = columns;
		this.tableName = tableName;
		this.whereColumn = whereColumn;
		this.whereValue = whereValue;
		this.orderByColumn = orderByColumn;
		this.limitValue = limitValue;
	}
	public String toString() {
		String queryString = "SELECT ";
		queryString += columns;
		if(hasTableName())
			queryString += " FROM " + tableName;
		if (hasWhereColumn())
			queryString += " WHERE " + whereColumn;
		if (hasWhereValue())
			queryString += " = $dbv$" + whereValue + "$dbv$";
		if (hasOrderByColumn())
			queryString += " ORDER BY " + orderByColumn + " " + orderByOrder;
		if (hasLimit())
			queryString += " LIMIT " + limitValue;
		return queryString;
	}
}
