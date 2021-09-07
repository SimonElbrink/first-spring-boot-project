package se.lexicon.firstspringbootproject.exceptions;

public class InvalidNameException extends RuntimeException{

    public InvalidNameException(String message) {
        super(message);
    }
}
