package ec.edu.espe.examen.execption;

public class EntityDeleteException extends RuntimeException {

    private Integer errorCode;
    private String entityName;

    public EntityDeleteException(String entityName, String message) {
        super(message);
        this.errorCode = 4;
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