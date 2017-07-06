package no.phasfjo.infrastructure.database;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by phasf on 07.11.2016.
 */
public class H2PingServiceIT {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static Weld weld;
    private static WeldContainer container;

    @BeforeClass
    public static void init() {
        weld = new Weld();
        container = weld.initialize();
    }

    @AfterClass
    public static void close() {
        weld.shutdown();
    }
    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldPingDatabase() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:WebshopDemo;create=true", "admin", "sa");
        // TODO REPLACE WITH SCRIPT!
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS Book(id INT(3), title VARCHAR(255), description VARCHAR(255), author VARCHAR(255) );");
        connection.createStatement().execute("INSERT INTO Book(id, title,description,author) VALUES (1, 'Mannen', 'Handler om en mann som finner seg selv', 'Per Mathisen');");
        connection.createStatement().executeQuery("SELECT * FROM Book");
        connection.createStatement().execute("DROP TABLE Book");
        connection.close();
    }

    @Test
    public void shouldPingDatabaseWithDispose() throws Exception {
        H2PingService pingService = container.instance().select(H2PingService.class).get();
        pingService.ping();
    }
}
