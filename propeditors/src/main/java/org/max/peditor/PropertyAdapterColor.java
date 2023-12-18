package org.max.peditor;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import org.max.peditor.editors.IPropertyEditor;
import org.max.peditor.editors.ITypeConverter;

import java.util.List;

public class PropertyAdapterColor extends APropertyAdapter<Color> {
    public PropertyAdapterColor(Context context, int layoutId, String key, String header, Color value, List<Object> items, int default_value_index) {
        super(context, layoutId, key, header, value, items, default_value_index);
    }

    @Override
    public View getView() {
        View view = super.getView();
        TextView tv = view.findViewWithTag(PROPERTY_VALUE_TAG);
        tv.setText( "          ");

        if (getValue() != null)
            tv.setBackgroundColor( getValue().toArgb() );

        tv.setOnClickListener(this);
        return view;
    }

    @Override
    public ITypeConverter<Color> getTypeConverter() {
        return value -> {
            Color color = null;
            assert value != null : "Parameter can not be null";
            if( value instanceof Integer )
                color = Color.valueOf( ((Integer) value).intValue() );
            else if( value instanceof String )
            {
                String strValue = (String) value;
                color = Color.valueOf( Color.parseColor( strValue ) );
            }
            else if( value instanceof Color )
                color = (Color) value;

            return color;
        };
    }

    @Override
    public IPropertyEditor<Color> getPropertyEditor(Context context) {
        return null;
    }
}