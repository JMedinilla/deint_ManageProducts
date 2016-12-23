package com.jmedinilla.manageproducts;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jmedinilla.manageproducts.preferences.Account_Settings;
import com.jmedinilla.manageproducts.preferences.General_Settings;

public class HomeActivity extends AppCompatActivity implements ListFragment.ListProductListener {

    private ListFragment listFragment;
    //private ProductFormFragment productFormFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.framehome, new ListFragment());
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    /**
     * Method that controls the pressed item on the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_setting_account:
                intent = new Intent(HomeActivity.this, Account_Settings.class);
                startActivity(intent);
                break;
            case R.id.action_setting_general:
                intent = new Intent(HomeActivity.this, General_Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showManageProduct(Bundle bundle) {/*
        productFormFragment = new ProductFormFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, productFormFragment);
        ft.addToBackStack(null);
        ft.commit();*/
    }

    public void showListProduct() {
        /*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome, listFragment);
        ft.addToBackStack(null);
        ft.commit();*/
    }
}
