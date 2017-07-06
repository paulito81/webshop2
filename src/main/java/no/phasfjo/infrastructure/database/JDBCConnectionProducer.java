package no.phasfjo.infrastructure.database;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by phasf on 07.11.2016.
 */

public class JDBCConnectionProducer {

    @Produces
    private Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:mem:WebshopDemo;create=true", "admin", "sa");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private void closeConnection(@Disposes Connection connection) throws Exception {
        connection.close();
    }
}
