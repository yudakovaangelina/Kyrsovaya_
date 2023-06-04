package com.mirea.kt.android.kyrsovaya_yudakova;


public interface OnStudentLoaded {
    void onSuccessLoad(String text, String avaUrl);
    void onFailed(String textError);
}