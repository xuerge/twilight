package com.xuerge.twilight;

public class StateMachineException extends RuntimeException {
    public StateMachineException(Throwable cause) {
        super(cause);
    }

    public StateMachineException(String message) {
        super(message);
    }
}
