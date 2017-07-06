package no.phasfjo.infrastructure.database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by phasf on 07.11.2016.
 */

public class H2PingService {
    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private Connection connection;

    // ======================================
    // =          Business methods          =
    // ======================================

    public void ping() throws SQLException {
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS Book(id INT(3), title VARCHAR(255), description VARCHAR(255), author VARCHAR(255) );");
        connection.createStatement().execute("INSERT INTO Book(id, title,description,author) VALUES (1, 'Mannen', 'Handler om en manne som finner seg selv', 'Per Mathisen');");
        connection.createStatement().executeQuery("SELECT * FROM Book");
        connection.createStatement().execute("DROP TABLE Book");
    }
}
