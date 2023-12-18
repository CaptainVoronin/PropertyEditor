package org.max.propertyeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.max.peditor.IPropertyAdapter;
import org.max.peditor.PropertySet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById( R.id.propertyView );
        PropertySet pHolder = new PropertySet( this, view, R.raw.props, R.layout.prop_editor_static_layout);
        try {
            pHolder.createViews();
            IPropertyAdapter<Double> pe = (IPropertyAdapter<Double>) pHolder.getByKey( "limit_amount");
            if( pe!= null )
            {
                pe.setChangeListener( (value-> Log.d( "asdasd", value.toString() )) );
                pe.setBeforeChangeListener(value -> value > 1);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}