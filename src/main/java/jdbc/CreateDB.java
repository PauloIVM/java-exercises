package jdbc;
import java.io.IOException;
import java.sql.SQLException;

public class CreateDB {
    public static void main(String[] args) throws SQLException, IOException {
        ConnectionSingleton
            .getStatement()
            .execute("CREATE DATABASE IF NOT EXISTS curso_java");
        ConnectionSingleton.closeConnection();
    }
}
