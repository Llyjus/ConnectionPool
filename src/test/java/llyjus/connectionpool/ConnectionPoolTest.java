package llyjus.connectionpool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConnectionPoolTest {

    @Test
    void shouldRejectZeroCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ConnectionPool(0)
        );
    }
}
