package llyjus.connectionpool;

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
}


