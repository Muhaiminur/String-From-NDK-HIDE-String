# String-From-NDK-HIDE-String
Welcome to the String-From-NDK-HIDE-String wiki!

***

# Demonstration

![What We are going to do.](https://github.com/Muhaiminur/String-From-NDK-HIDE-String/blob/master/Documentation/example.gif)

# Add and Enable Ndk

***

* Create native-lib.cpp file under cpp folder

### For returning String
``` 
extern "C" JNIEXPORT jstring JNICALL
Java_com_easyplayer_hidendk_MainActivity_apikey2(JNIEnv *env, jobject /* this */) {
    std::string hello2 = "Hello from G - TECH";
    return env->NewStringUTF(hello2.c_str());
}
```
### For Method Constructor
```
extern "C" JNIEXPORT jint JNICALL
Java_com_easyplayer_hidendk_MainActivity_apikey3(JNIEnv *env, jobject /* this */,jint num) {
    std::string hello2 = "Hello from G - TECH";
    return num*2;
}
```

***

# In Main Android Class
### Add Library
```
static {
        System.loadLibrary("native-lib");
    }
```
### Define Method
```
public native String apikey2();
public native int apikey3(int i);
```
### Finally Use Like This
```
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
```
![Example One](https://github.com/Muhaiminur/String-From-NDK-HIDE-String/blob/master/Documentation/example1%20(1).png)
![Example Two](https://github.com/Muhaiminur/String-From-NDK-HIDE-String/blob/master/Documentation/example1%20(2).png)
![Example Three](https://github.com/Muhaiminur/String-From-NDK-HIDE-String/blob/master/Documentation/example1%20(3).png)

You Can Download [ HERE](https://github.com/Muhaiminur/String-From-NDK-HIDE-String/blob/master/Documentation/app-debug.apk)
