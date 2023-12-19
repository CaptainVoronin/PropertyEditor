package org.max.peditor.editors;

import android.content.Context;
import android.graphics.Color;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;

public class PropertyEditorColor extends APropertyEditor<Color> {
    public PropertyEditorColor(Context context, ITypeConverter<Color> converter) {
        super(context, converter);
    }

    @Override
    public void show() {
        MaterialColorPickerDialog.Builder dlg = new MaterialColorPickerDialog
                .Builder(getContext())
                .setTitle(getTitle())
                .setColorSwatch(ColorSwatch._900)
                .setColorShape(ColorShape.SQAURE);
        if (getValue() != null)
        {
            String hex= String.format("#%06X", (0xFFFFFF & getValue().toArgb()));
            dlg.setDefaultColor( hex );
        }
        else
        {
            dlg.setDefaultColor( "#FF000000" );
        }

        dlg.setColorListener((color, colorHex) -> {
            if (getListener() != null)
                getListener().onOkPressed(getTypeConverter().convertValue(Color.parseColor(colorHex)));

        });


        dlg.show();
    }
}
