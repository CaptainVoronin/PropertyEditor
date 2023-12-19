package org.max.preditor;

public interface IValidator<T> {
    boolean isValid(T value);
}
