package org.max.peditor;

import android.content.Context;
import android.text.InputType;

import org.max.peditor.editors.IPropertyEditor;
import org.max.peditor.editors.ITypeConverter;
import org.max.peditor.editors.PropertyEditorSimpleType;

import java.util.List;

public class PropertyAdapterDouble extends APropertyAdapter<Double> {
    public PropertyAdapterDouble(Context context, int layoutId, String key, String header, Double value, List<Object> items, int default_value_index) {
        super(context, layoutId, key, header, value, items, default_value_index);
    }

    @Override
    public ITypeConverter<Double> getTypeConverter() {
        return value-> Double.valueOf( value.toString() );
    }

    @Override
    public IPropertyEditor<Double> getPropertyEditor(Context context)
    {
        return new PropertyEditorSimpleType<>(context, getTypeConverter(), InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL );
    }

}