class NonexistantValueException extends Exception {
	public NonexistantValueException(String column, Object value) {
		super("Unable to find column [" + column+ "] with value [" + value.toString() + "]");
	}	
}
