package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class QueryPerson2 {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o nome: ");
        String name = input.nextLine();
        input.close();

        String sql = "SELECT * FROM pessoas WHERE nome like ?";
        var preparedStatement = ConnectionSingleton
                .getConnection()
                .prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        ResultSet result = preparedStatement.executeQuery();

        while(result.next()) {
            System.out.println(result.getInt("codigo"));
            System.out.println(result.getString("nome"));
        }

        preparedStatement.close();
        ConnectionSingleton.closeConnection();
    }
}
