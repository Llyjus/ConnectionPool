package llyjus.connectionpool;

public class Connection {
    public enum Status {
        IN_USE,
        AVAILABLE
    }

    private Status status;

    public Connection() {
        this.status = Status.IN_USE;
    }

    public void release() {
        this.status = Status.AVAILABLE;
    }

    public void acquire() {
    this.status = Status.IN_USE;
    }

    public Status getStatus() {
        return status;
    }


}
