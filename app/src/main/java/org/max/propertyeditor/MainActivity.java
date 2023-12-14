package org.max.propertyeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONException;
import org.json.JSONObject;
import org.max.peditor.IPropertyEditor;
import org.max.peditor.IValidator;
import org.max.peditor.PropertyHolder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout view = findViewById( R.id.propertyView );
        PropertyHolder pHolder = new PropertyHolder( this, view, R.raw.props, R.layout.prop_editor_static_layout);
        try {
            pHolder.createViews();
            IPropertyEditor<Double> pe = (IPropertyEditor<Double>) pHolder.getByKey( "limit_amount");
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