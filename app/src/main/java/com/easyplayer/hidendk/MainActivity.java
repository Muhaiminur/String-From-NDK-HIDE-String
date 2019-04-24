package com.easyplayer.hidendk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(apikey());

        final TextView example_two = findViewById(R.id.example_two);
        example_two.setText(apikey2());

        final EditText input = findViewById(R.id.input);

        Button result = findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!TextUtils.isEmpty(input.getText())) {

                    Log.d("Result", apikey3(22) + "");
                    example_two.setText(apikey3(Integer.parseInt(input.getText().toString())) + "");
                } else {
                    Toast.makeText(MainActivity.this, "No Input", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String apikey();

    public native String apikey2();

    public native int apikey3(int i);
}
