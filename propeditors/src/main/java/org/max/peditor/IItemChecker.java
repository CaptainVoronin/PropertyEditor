package org.max.peditor;

public interface IItemChecker<T extends Object> {
    T check(Object item );
}
