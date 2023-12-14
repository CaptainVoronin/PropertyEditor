package org.max.peditor;

public interface IValidator<T> {
    boolean isValid(T value);
}
