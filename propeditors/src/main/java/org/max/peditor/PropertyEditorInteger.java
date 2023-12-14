package org.max.peditor;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

import java.util.List;

public class PropertyEditorInteger extends APropertyEditor<Integer>{
    public PropertyEditorInteger(Context context, int layoutId, String key, String header, Integer value, List<Object> items, int default_value_index) {
        super(context, layoutId, key, header, value, items, default_value_index);
    }

    @Override
    public Integer convertValue(Object value) {
        return Integer.valueOf( value.toString() );
    }

    public EditText getValueEditor() {
        EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        return input;
    }
}