package org.max.peditor;

import android.content.Context;
import android.text.InputType;

import org.max.peditor.editors.IPropertyEditor;
import org.max.peditor.editors.PropertyEditorSimpleType;

import java.util.List;

public class PropertyAdapterString extends APropertyAdapter<String> {

    public PropertyAdapterString(Context context, int layoutId, String key, String header, String value, List<Object> items, int default_value_index) {
        super(context, layoutId, key, header, value, items, default_value_index);
    }

    @Override
    public String convertValue(Object value) {
        return value.toString();
    }

    @Override
    public IPropertyEditor<String> getPropertyEditor(Context context)
    {
        return new PropertyEditorSimpleType<>(context, InputType.TYPE_CLASS_TEXT);
    }

}