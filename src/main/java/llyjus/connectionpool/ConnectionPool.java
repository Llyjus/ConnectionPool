package llyjus.connectionpool;

public class ConnectionPool {

    public ConnectionPool(int capacity) {
        if (capacity <= 0 || capacity >= 65536) {
            throw new IllegalArgumentException();
        }
    }

    public Connection request() {
        return new Connection();
    }
}