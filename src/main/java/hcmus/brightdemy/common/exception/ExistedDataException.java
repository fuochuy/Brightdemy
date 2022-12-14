package hcmus.brightdemy.common.exception;

public class ExistedDataException extends RuntimeException {

    private static final long serialVersionUID = 2523304286935073416L;

    public ExistedDataException() {
        super();
    }

    public ExistedDataException(String message) {
        super(message);
    }

}
