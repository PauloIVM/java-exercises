package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        DAO dao = new DAO();
        String sql = "INSERT INTO pessoas (nome) VALUES (?)";
        dao.include(sql, "João da Silva 23");
        dao.include(sql, "João da Silva 24");
        dao.include(sql, "João da Silva 25");
    }

    public int include(String sql, Object... attributes) throws SQLException, IOException {
        PreparedStatement stmt = getConnection().prepareStatement(
            sql,
            PreparedStatement.RETURN_GENERATED_KEYS
        );

        this.addAttributes(stmt, attributes);
        if (stmt.executeUpdate() > 0) {
            ResultSet result = stmt.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        }
        return -1;
    }

    private void addAttributes(PreparedStatement stmt, Object[] attributes) throws SQLException {
        int index = 1;
        for (Object attribute: attributes) {
            if (attribute instanceof String) {
                stmt.setString(index, (String) attribute);
            }
            if (attribute instanceof Integer) {
                stmt.setInt(index, (Integer) attribute);
            }
            index++;
        }
    }

    private Connection getConnection() throws SQLException, IOException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        connection = ConnectionSingleton.getConnection();
        return connection;
    }
}
