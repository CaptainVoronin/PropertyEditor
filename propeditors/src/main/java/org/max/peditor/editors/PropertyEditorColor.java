package org.max.peditor.editors;

import android.content.Context;
import android.graphics.Color;

import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;

public class PropertyEditorColor extends APropertyEditor<Color> {
    public PropertyEditorColor(Context context, ITypeConverter<Color> converter) {
        super(context, converter);
    }

    @Override
    public void show() {
        MaterialColorPickerDialog.Builder dlg = new MaterialColorPickerDialog
                .Builder(getContext())
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE);
        if (getValue() != null)
            dlg.setDefaultColor(getValue().toString());
        dlg.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int i, String s) {

            }
        });

        dlg.setColorListener((color, colorHex) -> {
            if (getListener() != null)
                getListener().onOkPressed(getTypeConverter().convertValue(Color.parseColor(colorHex)));

        });


        dlg.show();
    }
}
