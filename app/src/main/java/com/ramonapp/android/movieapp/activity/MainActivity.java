package com.ramonapp.android.movieapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ramonapp.android.movieapp.R;
import com.ramonapp.android.movieapp.dialog.WaitingDialog;
import com.ramonapp.android.movieapp.fragment.MovieListFragment;
import com.ramonapp.android.movieapp.interfaces.FragmentCallback;

public class MainActivity extends AppCompatActivity implements FragmentCallback{

    private Toolbar toolbarMain;
    private ImageButton menuBtn;
    private TextView titleTxt;

    private WaitingDialog waitingDialog;
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        menuBtn = (ImageButton) toolbarMain.findViewById(R.id.toolbar_menu_btn);
        titleTxt = (TextView) toolbarMain.findViewById(R.id.toolbar_title);

        setTitle(getString(R.string.app_name));

        waitingDialog = new WaitingDialog(this);

        fragment = MovieListFragment.newInstance();
        changeFragment(fragment,false);
    }


    private void changeFragment(Fragment fragment, boolean addToBackStack){
        String tag = fragment.getClass().getName();
        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if( oldFragment != null && oldFragment.isVisible() ){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        if(addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void setToolbarLayout(boolean isSub) {
        if(isSub){
            toolbarMain.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
        }else {
            toolbarMain.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    @Override
    public void setToolbarTitle(String title) {

    }

    @Override
    public void showWaiting(){
        waitingDialog.show();
    }

    @Override
    public void dismissWaiting(){
        waitingDialog.dismiss();
    }

    @Override
    public void setTitle(CharSequence title) {
        titleTxt.setText(title);
        super.setTitle(title);
    }
}
