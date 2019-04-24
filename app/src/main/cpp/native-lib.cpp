#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_easyplayer_hidendk_MainActivity_apikey(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from G Technologies";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_easyplayer_hidendk_MainActivity_apikey2(JNIEnv *env, jobject /* this */) {
    std::string hello2 = "Hello from G - TECH";
    return env->NewStringUTF(hello2.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_easyplayer_hidendk_MainActivity_apikey3(JNIEnv *env, jobject /* this */,jint num) {
    std::string hello2 = "Hello from G - TECH";
    return num*2;
}