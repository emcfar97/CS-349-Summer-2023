import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database {

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSetMetaData metaData = null;
    private static ResultSet resultSet = null;
    private static Vector<Vector> results;
    private static String[] credentials = new String[3];
    private static String userId;

    public static Vector<Vector> readDataBase(String sql, String[] args) throws Exception {

        results = new Vector<Vector>();
        
        try {
            // load the MySQL driver, and setup connection with database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                credentials[0], credentials[1], credentials[2]
                );

            // prepare statement and populate any parameters
            preparedStatement = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }
            
            // execute statement
            if (sql.startsWith("SELECT")) {

                resultSet = preparedStatement.executeQuery();
                metaData = resultSet.getMetaData();
                
                while (resultSet.next()) {

                    Vector<Object> row = getRow(metaData, resultSet);
                    results.add(row);
                }
            }
            else if (sql.startsWith("INSERT")) {
                
                Vector<Object> row = new Vector<Object>();
                row.add(preparedStatement.executeUpdate());
                results.add(row);
            }
            else if (sql.startsWith("UPDATE")) {
                
                Vector<Object> row = new Vector<Object>();
                row.add(preparedStatement.executeUpdate());
                results.add(row);
            }
            else if (sql.startsWith("DELETE")) {
                
                Vector<Object> row = new Vector<Object>();
                row.add(preparedStatement.executeUpdate());
                results.add(row);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return results;
    }
    public static Vector<Object> getRow(ResultSetMetaData metaData, ResultSet resultRow) {

        Vector<Object> row = new Vector<Object>();

        try {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                switch(metaData.getColumnType(i)) {
                    case -1: case 1: case 93:

                        row.add(resultRow.getString(metaData.getColumnName(i)));
                        break;
                    case -5: case 4:

                        row.add(resultRow.getInt(metaData.getColumnName(i)));
                        break;
                    case 8:

                        row.add(resultRow.getDouble(metaData.getColumnName(i)));
                        break;
                    case 16:

                        row.add(resultRow.getBoolean(metaData.getColumnName(i)));
                        break;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        return row;
    }
    public static void setUserId(Object ID) {
        userId = String.valueOf(ID);
    }    
    public static String getUserId() {
        return userId;
    }
    public static void setCredentials() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(
                new FileReader("src\\credentials.ini")
            );

			for (int i = 0; i < credentials.length; i++) {
				credentials[i] = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}