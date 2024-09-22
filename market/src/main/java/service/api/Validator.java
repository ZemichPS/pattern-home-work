package service.api;

import java.util.Objects;

public abstract class Validator<T> {

    private Validator<T> nextValidator;

    public Validator<T> linkWith(Validator<T> nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public abstract void validate(T t);

    public void checkNext(T t) {
        if (Objects.isNull(nextValidator)) return;
        nextValidator.validate(t);
    }
}
