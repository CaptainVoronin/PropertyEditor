package org.max.peditor;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

import java.util.List;

public class PropertyEditorDouble extends APropertyEditor<Double> {
    public PropertyEditorDouble(Context context, int layoutId, String key, String header, Double value, List<Object> items, int default_value_index) {
        super(context, layoutId, key, header, value, items, default_value_index);
    }

    @Override
    public Double convertValue(Object value) {
        return Double.valueOf( value.toString() );
    }

    public EditText getValueEditor() {
        EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        return input;
    }
}