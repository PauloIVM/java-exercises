package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertPerson {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o nome: ");
        String name = input.nextLine();
        input.close();

        // INFO: SQL Injection:
        // String sql = "INSERT INTO pessoas (nome) VALUES ('" + name + "')";

        String sql = "INSERT INTO pessoas (nome) VALUES (?)";

        var preparedStatement = ConnectionSingleton
            .getConnection()
            .prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.execute();

        preparedStatement.close();
        ConnectionSingleton.closeConnection();
    }
}
