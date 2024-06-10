package models;

public class DuplicateCarIdException extends Exception {
    @Override
    public String getMessage() {
        return "Duplicate car id";
    }
}
