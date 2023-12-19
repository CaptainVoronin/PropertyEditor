package org.max.propertyeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import org.json.JSONException;
import org.max.preditor.PropertySet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById( R.id.propertyView );
        PropertySet pHolder = new PropertySet( this, view, R.raw.props, R.layout.prop_editor_static_layout);
        try {
            pHolder.createViews();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}