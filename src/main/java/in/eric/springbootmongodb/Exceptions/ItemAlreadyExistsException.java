package in.eric.springbootmongodb.Exceptions;

public class ItemAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

}

