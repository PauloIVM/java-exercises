package jdbc;
import java.io.IOException;
import java.sql.SQLException;

public class CreatePeopleTable {
    public static void main(String[] args) throws SQLException, IOException {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
                "codigo INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(80) NOT NULL" +
                ")";
        ConnectionSingleton.getStatement().execute(sql);
        ConnectionSingleton.closeConnection();
    }
}
