package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;

public class QueryPerson1 {
    public static void main(String[] args) throws SQLException, IOException {
        String sql = "SELECT * FROM pessoas";
        ResultSet result = ConnectionSingleton.getStatement().executeQuery(sql);
        while(result.next()) {
            System.out.println(result.getInt("codigo"));
            System.out.println(result.getString("nome"));
        }
        ConnectionSingleton.closeConnection();
    }
}
