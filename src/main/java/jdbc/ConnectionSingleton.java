package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionSingleton {
    static Connection connection;

    static public Connection getConnection() throws SQLException, IOException {
        if (connection != null) {
            return connection;
        }
        Properties prop = getProperties();
        String host = prop.getProperty("db.host");
        String user = prop.getProperty("db.user");
        String password = prop.getProperty("db.password");
        connection = DriverManager.getConnection(host, user, password);
        return connection;
    }

    static public Statement getStatement() throws SQLException, IOException {
        return getConnection().createStatement();
    }

    static public void closeConnection() throws SQLException {
        connection.close();
    }

    static private Properties getProperties() throws IOException {
        Properties prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/main/resources/connection.properties";
        System.out.println(path);
        InputStream in = new FileInputStream(new File(path));
        prop.load(in);
        return prop;
    }
}
