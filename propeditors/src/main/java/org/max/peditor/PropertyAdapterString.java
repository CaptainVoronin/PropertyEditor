package org.max.peditor;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

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
    public EditText getValueEditor() {
        EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        return input;
    }
}