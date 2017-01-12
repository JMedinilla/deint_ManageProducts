package com.amador.manageProduct.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        setupDrawerContent();
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);


        parent = (FrameLayout) findViewById(R.id.frameHome);
        listFragment = (MultiListProductFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        productFormFragment = (ManageProductFragment) getSupportFragmentManager().findFragmentByTag(TAG_FORM_PRODUCT);
        back_pressed = 0;
        exitBool = false;

        if (listFragment == null) {

            listFragment = new MultiListProductFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frameHome, listFragment, TAG_LIST_FRAGMENT).commit();

        }

        if (productFormFragment == null) {

            productFormFragment = new ManageProductFragment();
        }


    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public void showManageProduct(Bundle bundle) {

        if (bundle == null) {

            productFormFragment = new ManageProductFragment();

        } else {

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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
        else {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            if (count == 0) {
                if (back_pressed + 1000 > System.currentTimeMillis() && exitBool) {
                    super.onBackPressed();
                    toast.cancel();
                } else {
                    toast = Toast.makeText(HomeActivity.this, "Pulsa dos veces seguidas para salir", Toast.LENGTH_SHORT);
                    toast.show();
                    back_pressed = System.currentTimeMillis();
                    exitBool = true;
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.menu_nav_home:
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_nav_products:
                        Toast.makeText(HomeActivity.this, "Products", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_nav_pharmacy:
                        Toast.makeText(HomeActivity.this, "Pharmacy", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_nav_invoice:
                        Toast.makeText(HomeActivity.this, "Invoice", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_nav_configuration:
                        Toast.makeText(HomeActivity.this, "Configuration", Toast.LENGTH_SHORT).show();
                        item.setChecked(false);
                        break;
                    case R.id.menu_nav_exit:
                        Toast.makeText(HomeActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        item.setChecked(false);
                        break;
                    default:
                        item.setChecked(false);
                        break;
                }
                setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
