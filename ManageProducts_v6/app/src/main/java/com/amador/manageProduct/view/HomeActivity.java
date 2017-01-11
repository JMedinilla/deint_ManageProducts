package com.amador.manageProduct.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.ManageProductListener;

public class HomeActivity extends AppCompatActivity implements ManageProductListener, MultiListProductFragment.ListProductListener {


    private MultiListProductFragment listFragment;
    private ManageProductFragment productFormFragment;
    private FrameLayout parent;
    private static final String TAG_LIST_FRAGMENT = "list";
    private static final String TAG_FORM_PRODUCT = "frm";
    long back_pressed;
    boolean exitBool;
    Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);
        parent = (FrameLayout)findViewById(R.id.frameHome);
        listFragment = (MultiListProductFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        productFormFragment = (ManageProductFragment) getSupportFragmentManager().findFragmentByTag(TAG_FORM_PRODUCT);
        back_pressed = 0;
        exitBool = false;

        if(listFragment == null){

            listFragment = new MultiListProductFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frameHome, listFragment, TAG_LIST_FRAGMENT).commit();

        }

        if(productFormFragment == null){

            productFormFragment = new ManageProductFragment();
        }


    }

    @Override
    public void showManageProduct(Bundle bundle) {

        if(bundle == null){

            productFormFragment = new ManageProductFragment();

        }else {

            productFormFragment = ManageProductFragment.newInstance(bundle);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameHome, productFormFragment, TAG_FORM_PRODUCT);
        ft.addToBackStack(null);
        ft.commit();
        exitBool = false;
    }

    @Override
    public void onListProductListener(String msg) {

        Snackbar.make(parent, msg, Snackbar.LENGTH_LONG).show();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameHome, listFragment, TAG_LIST_FRAGMENT);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            if (back_pressed + 1000 > System.currentTimeMillis() && exitBool) {
                super.onBackPressed();
                toast.cancel();
            } else
            {
                toast = Toast.makeText(HomeActivity.this, "Pulsa dos veces seguidas para salir", Toast.LENGTH_SHORT);
                toast.show();
                back_pressed = System.currentTimeMillis();
                exitBool = true;
            }
        }
        else {
            super.onBackPressed();
        }
    }
}
