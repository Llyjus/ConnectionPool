package llyjus.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private final int capacity;
    private final List<Connection> connections;
    private int totalConnections = 0;

    // Check if the capacity is valid (greater than 0 and less than 65536)
    public ConnectionPool(int capacity) {
        if (capacity <= 0 || capacity >= 65536) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.connections = new ArrayList<>();
    }

    // Request a connection from the pool
    public Connection request() {

        // Check for available connections
        for (Connection connection : connections) {
            if (connection.getStatus() == Connection.Status.AVAILABLE) {
                connection.acquire();
                totalConnections++;
                return connection;
            }
        }

        // Raise an exception if the pool is full
        if (connections.size() >= capacity) {
            throw new IllegalStateException("Connection pool is full");
        }

        Connection connection = new Connection();
        connections.add(connection);
        totalConnections++;

        return connection;
    }

    // Release the connection
    public void release(Connection connection) {
        connection.release();
    }

    public int getCapacity() {
        return capacity;
    }

    // Get the current number of available connections in the pool
    public int getAvailableCount() {
        int count = 0;
        for (Connection connection : connections) {
            if (connection.getStatus() == Connection.Status.AVAILABLE) {
                count++;
            }
        }
        return count;
    }

    // Get the current number of in-use connections in the pool
    public int getInUseCount() {
        int count = 0;
        for (Connection connection : connections) {
            if (connection.getStatus() == Connection.Status.IN_USE) {
                count++;
            }
        }
        return count;
    }

    // Get the total number of connections in the pool
    public int getTotalConnections() {
        return totalConnections;
    }
}