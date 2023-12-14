package org.max.peditor;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

public abstract class APropertyEditor<T> implements IPropertyEditor<T> {

    private final Context context;
    private List<T> items;
    private final int default_value_index;
    private T value;

    private int layoutId;

    private String key;

    private String header;

    private String prompt;

    private IValidator<T> validator;

    View view;

    private IPropertyChangeListener<T> changeListener;

    public APropertyEditor(Context context,
                           int layoutId,
                           String key,
                           String header,
                           T value,
                           List<Object> items,
                           int default_value_index) {
        this.context = context;
        this.layoutId = layoutId;
        this.key = key;
        this.header = header;
        this.value = value;
        if (items != null)
            this.items = items.stream().map(item -> convertValue(item)).collect(Collectors.toList());

        this.default_value_index = default_value_index;
    }

    @Override
    public final View getView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(layoutId, null);
        TextView tv = view.findViewWithTag(PROPERTY_HEADER_TAG);
        tv.setText(header);
        tv = view.findViewWithTag(PROPERTY_VALUE_TAG);
        if (value != null)
            tv.setText(value.toString());

        tv.setOnClickListener(this);
        return view;
    }

    @Override
    public final T getValue() {
        return value;
    }

    @Override
    public final void setBeforeChangeListener(IValidator<T> validator) {
        this.validator = validator;
    }

    @Override
    public final void setChangeListener(IPropertyChangeListener<T> changeListener) {
        this.changeListener = changeListener;
    }

    public final Context getContext() {
        return context;
    }

    public final String getKey() {
        return key;
    }

    @Override
    public final void onClick(View v) {
        editValue();
    }

    @Override
    public boolean setValue(T newValue) {
        boolean result = false;
        if (validator != null) {
            if (validator.isValid(newValue)) {
                value = newValue;
                result = true;
            }
        } else {
            value = newValue;
            result = true;
        }
        if (result) {
            if (changeListener != null)
                changeListener.onChanged(value);
            TextView tv = view.findViewWithTag(PROPERTY_VALUE_TAG);
            tv.setText(value.toString());
        }
        return true;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public final List<T> getItems() {
        return items;
    }

    public int getDefault_value_index() {
        return default_value_index;
    }

    @Override
    public void editValue() {
        int index = -1;
        if (getValue() != null) {
            if (getItems() != null)
                for (int i = 0; i < getItems().size(); i++)
                    if (getValue().equals(getItems().get(i))) {
                        index = i;
                        break;
                    }
        } else if (getDefault_value_index() != IPropertyEditor.INVALID_DEFAULT_VALUE_INDEX)
            index = getDefault_value_index();

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getHeader());

        if (getItems() != null) {
            builder.setSingleChoiceItems(getItems().stream().map(Object::toString).toArray(String[]::new),
                    index,
                    (dialogInterface, i) -> {
                        setValue(convertValue(items.get(i)));
                        dialogInterface.cancel();
                    });

            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                dialog.cancel();
            });
        } else {
            final EditText input = getValueEditor();
            if (getValue() != null)
                input.setText(getValue().toString());

            builder.setView(input);
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                setValue(convertValue(input.getText()));
                dialog.cancel();
            });
        }

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
