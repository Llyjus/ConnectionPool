package llyjus.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final int capacity;
    private final List<Connection> connections;

    public ConnectionPool(int capacity) {
        if (capacity <= 0 || capacity >= 65536) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.connections = new ArrayList<>();
    }

    public Connection request() {
        if (connections.size() >= capacity) {
            throw new IllegalStateException("Connection pool is full");
        }

        Connection connection = new Connection();
        connections.add(connection);

        return connection;
    }

    public void release(Connection connection) {
    }
}