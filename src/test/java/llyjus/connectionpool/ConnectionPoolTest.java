package llyjus.connectionpool;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ConnectionPoolTest {

    @Test
    void shouldRejectZeroCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ConnectionPool(0)
        );
    }

    @Test
    void shouldRejectCapacityOf65536() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ConnectionPool(65536)
        );
    }

    @Test
    void testEdgeCapacity() {
        assertDoesNotThrow(() -> new ConnectionPool(1));
        assertDoesNotThrow(() -> new ConnectionPool(65535));
    }

    @Test
    void shouldCreateConnectionWhenPoolIsEmpty() {
    ConnectionPool pool = new ConnectionPool(5);

    Connection connection = pool.request();

    assertNotNull(connection);
}

    @Test
    void shouldReturnConnectionInUseState() {
        ConnectionPool pool = new ConnectionPool(5);

        Connection connection = pool.request();

        assertEquals(Connection.Status.IN_USE, connection.getStatus());
    }

    @Test
    void shouldCreateDifferentConnectionsForMultipleRequests() {
        ConnectionPool pool = new ConnectionPool(5);

        Connection first = pool.request();
        Connection second = pool.request();

        assertNotSame(first, second);
    }


    @Test
    void fullPull() {
        ConnectionPool pool = new ConnectionPool(1);

        Connection first = pool.request();

        assertNotNull(first);

        assertThrows(
                IllegalStateException.class,
                pool::request
        );
    }

    @Test
    void shouldReuseReleasedConnection() {
        ConnectionPool pool = new ConnectionPool(1);

        Connection first = pool.request();

        pool.release(first);

        Connection second = pool.request();

        assertSame(first, second);
    }


    @Test
    void shouldReturnPoolCapacity() {
        ConnectionPool pool = new ConnectionPool(5);

        assertEquals(5, pool.getCapacity());
    }

    @Test
    void shouldReturnZeroAvailableCountWhenPoolIsEmpty() {
        ConnectionPool pool = new ConnectionPool(5);

        assertEquals(0, pool.getAvailableCount());
    }

    @Test
    void shouldReturnZeroInUseConnectionsWhenPoolIsEmpty() {
        ConnectionPool pool = new ConnectionPool(5);

        assertEquals(0, pool.getInUseCount());
    }

    @Test
    void shouldReturnOneInUseConnections() {
        ConnectionPool pool = new ConnectionPool(5);

        Connection connection = pool.request();
        assertEquals(1, pool.getInUseCount());
    }

    @Test
    void shouldReturnMultipleInUseConnections() {
        ConnectionPool pool = new ConnectionPool(5);

        pool.request();
        pool.request();
        pool.request();

        assertEquals(3, pool.getInUseCount());
}
}
