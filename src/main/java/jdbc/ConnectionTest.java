package jdbc;
import java.io.IOException;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws SQLException, IOException {
        ConnectionSingleton.getConnection();
        System.out.println("Connection ok!");
        ConnectionSingleton.closeConnection();
    }
}
