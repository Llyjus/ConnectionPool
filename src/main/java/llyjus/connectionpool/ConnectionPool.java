package llyjus.connectionpool;

public class ConnectionPool {

    public ConnectionPool(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
    }
}