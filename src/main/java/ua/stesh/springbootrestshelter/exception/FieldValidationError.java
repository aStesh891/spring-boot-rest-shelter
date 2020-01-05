package ua.stesh.springbootrestshelter.exception;

public class FieldValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public void setObject(String object) {
        this.object = object;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
