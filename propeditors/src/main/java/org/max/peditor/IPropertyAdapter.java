package org.max.peditor;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import org.max.peditor.editors.IPropertyEditor;
import org.max.peditor.editors.ITypeConverter;

import java.util.List;

public interface IPropertyAdapter<T> extends View.OnClickListener {

    String PROPERTY_HEADER_TAG = "property_header";
    String PROPERTY_VALUE_TAG = "property_value";
    String PROPERTY_PROMPT_TAG = "property_prompt";
    String PR_DEFAULT_VALUE_INDEX = "default_value_index";
    String PR_KEY_VALUE = "value";

    int INVALID_DEFAULT_VALUE_INDEX = -1;

    View getView();

    T getValue();

    void setBeforeChangeListener(IValidator<T> validator);

    void setChangeListener(IPropertyChangeListener<T> listener);

    String getKey();

    String getHeader();

    void editValue();

    boolean setValue( T value );

    ITypeConverter<T> getTypeConverter( );

    List<T> getItems();

    void setSelectedItemIndex( int index );

    IPropertyEditor<T> getPropertyEditor(Context context );

    void setCustomPropertyEditor( IPropertyEditor<T> editor );
}
