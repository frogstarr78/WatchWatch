
interface MetaDescription {
	public String getTableName();
	public String getPrimaryKey();
	public void setFromRow(java.sql.ResultSet databaseValues) 
		throws java.sql.SQLException, ClassNotFoundException, NonexistantValueException; 
}
