package models;

public class UpdateStatusException extends Exception {
    @Override
    public String getMessage() {
        return "Update order status failed";
    }
}
