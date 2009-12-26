The example code in this directory is taken from the book "JDBC(TM) API Tutorial
and Reference, Third Edition," by Maydene Fisher, Jon Ellis, and Jonathan Bruce.
Most of the examples are from the tutorials.

Before you can run the example code, you will need to do the following:

1. Unzip the file containing the sample code.

	   unzip codeExamples.zip

2. Substitute your driver's JDBC URL for the generic JDBC URL 
   that appears in the code. In other words, put your driver's JDBC URL 
   between the quotation marks in the follwoing line:

        String url = "jdbc:mySubprotocol:myDataSource";

   The documentation for your driver should give you this URL.

3. Substitute the driver's full class name for "myDriver.ClassName" in
   the following line:

			Class.forName("myDriver.ClassName");

4. Substitute the username and password you use for your database 
   in the following:

           "myLogin", "myPassword"


Two of the .java files contain method(s) that are used in other files and cannot
be run by themselves:

	- PrintColumnTypes.java defines a method used in PrintColumns.java
	- DataType.java defines methods used in CreateNewTable.java



Note that the following code samples have been compiled but not executed because
at the time of testing, not all drivers implemented all of the features used in the 
code:

	  CreateRef.java
	  CreateStores.java
	  InsertStores.java
	  AutoGenKeys.java
	  GetParamMetaData.java

Note also that the syntax for creating SQL99 types can vary from one DBMS to
another, so you should be sure to check the documentation to see the exact
syntax your DBMS expects.



