package org.max.peditor.editors;

public interface ITypeConverter<T> {
    T convertValue( Object value );
}
