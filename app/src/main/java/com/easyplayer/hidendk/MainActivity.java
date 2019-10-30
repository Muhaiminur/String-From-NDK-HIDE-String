package com.easyplayer.hidendk;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.add_card)
    Button addCard;
    @BindView(R.id.remove_cart)
    Button removeCart;

    static int mNotifCount = 0;
    Button notifCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @OnClick({R.id.add_card, R.id.remove_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_card:
                mNotifCount++;
                setNotifCount(mNotifCount);
                break;
            case R.id.remove_cart:
                mNotifCount--;
                setNotifCount(mNotifCount);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        View count = menu.findItem(R.id.badge).getActionView();
        notifCount = count.findViewById(R.id.notif_count);
        notifCount.setText(String.valueOf(mNotifCount));

        MenuItem menuItem = menu.findItem(R.id.action_settings);
        menuItem.setIcon(buildCounterDrawable(R.drawable.ic_shopping_cart));


        return super.onCreateOptionsMenu(menu);
    }

    private void setNotifCount(int count){
        mNotifCount = count;
        invalidateOptionsMenu();
    }

    //create notification id
    private Drawable buildCounterDrawable(int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.cart_menuitem_layout, null);
        view.setBackgroundResource(backgroundImageId);
        if (mNotifCount == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = view.findViewById(R.id.count);
            textView.setText("" + mNotifCount+"");
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }
}
