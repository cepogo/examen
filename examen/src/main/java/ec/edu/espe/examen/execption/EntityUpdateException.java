package ec.edu.espe.examen.execption;

public class EntityUpdateException extends RuntimeException {

    private Integer errorCode;
    private String entityName;

    public EntityUpdateException(String entityName, String message) {
        super(message);
        this.errorCode = 3;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return "errorCode=" + errorCode + ", entityName=" + entityName + ", message=" + super.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
} 