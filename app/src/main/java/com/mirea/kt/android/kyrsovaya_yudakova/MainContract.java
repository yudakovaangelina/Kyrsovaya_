package com.mirea.kt.android.kyrsovaya_yudakova;

public class MainContract {
    interface MainView{
        void showLoading();
        void hideLoading();
        void showMessage(int resId);
        void showMessage(String message);
        void showError(int restId);
        void showError(String errorMessage);
        void showStudentInfo(String text, String avaUrl);
    }
    interface MainPresenter{
        void onViewCreated();
        void onClickGetInfoButton(String name, String group);
        void onViewDestroyed();
    }
    interface MainRepository{
        void loadStudentInfo(String lgn, String pwd,String g, OnStudentLoaded sil);
    }
}
