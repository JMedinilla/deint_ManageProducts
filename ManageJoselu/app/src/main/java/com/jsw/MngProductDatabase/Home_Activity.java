package com.jsw.MngProductDatabase;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.jsw.MngProductDatabase.Fragments.Home_Fragment;
import com.jsw.MngProductDatabase.Fragments.ListProduct_Fragment;
import com.jsw.MngProductDatabase.Fragments.ManageProduct_Fragment;
import com.jsw.MngProductDatabase.Fragments.Pharmacy_Fragment;
import com.jsw.MngProductDatabase.Fragments.Sales_Fragment;
import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.Presenter.ProductPresenter;
import com.jsw.MngProductDatabase.utils.DialogoConfirmacion;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.IListProductListener, ManageProduct_Fragment.IManageListener {

    ProductPresenter mPresenter;
    private ListProduct_Fragment mListProduct;
    private ManageProduct_Fragment mManageProduct;
    private Home_Fragment mHome;
    private Pharmacy_Fragment mPharmacy;
    private Sales_Fragment mSales;
    private boolean salir = false;
    private FrameLayout fl_home;
    private Bundle mPotatoe;
    private Toolbar mToolbar;
    private NavigationView mNav;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);
        fl_home = (FrameLayout)findViewById(R.id.fl_frameHome);
        mListProduct = ListProduct_Fragment.getInstance(null);
        mHome = new Home_Fragment();
        mPharmacy = new Pharmacy_Fragment();
        mSales = new Sales_Fragment();
        mPresenter = new ProductPresenter(mListProduct);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mNav = (NavigationView)findViewById(R.id.navigation_view);
        mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        showHome();
    }

    @Override
    public void onBackPressed() {

        if(mDrawer.isDrawerOpen(GravityCompat.START))
            mDrawer.closeDrawer(GravityCompat.START);

        else if(getSupportFragmentManager().getBackStackEntryCount() > 0 || salir)
            super.onBackPressed();
        else {
            salir = true;
            Snackbar.make(fl_home, "Press back again to exit", Snackbar.LENGTH_LONG)
                    .addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            salir = false;
                        }
                    }).show();
        }

    }

    @Override
    public void showManageProduct(Bundle bundle) {
        mManageProduct = ManageProduct_Fragment.getInstance(bundle);
        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fl_frameHome, mManageProduct);
        fr.addToBackStack("Manage");
        fr.commit();
    }

    @Override
    public void showDeletePopUp(Product product) {
        mPotatoe = new Bundle();
        mPotatoe.putParcelable("product", product);
        DialogoConfirmacion dialog = new DialogoConfirmacion();
        dialog.setArguments(mPotatoe);
        dialog.setPresenter(mPresenter);
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void undoDeleting(Product product) {
        mPresenter.addProduct(product);
    }

    @Override
    public void saveProduct(Product oldProduct, Product newProduct) {
        if(oldProduct == null)
            mPresenter.addProduct(newProduct);
        else
            mPresenter.updateProduct(newProduct);

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameHome, mHome, "Home").commit();
    }

    public void showPharmacy() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameHome, mPharmacy, "Pharmacy").commit();
    }

    public void showSales() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameHome, mSales, "Sales").commit();
    }

    public void showList() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameHome, mListProduct, "List").commit();
    }

    public void setupDrawerContent(){
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId()){
                    case R.id.action_home:
                        showHome();
                        break;
                    case R.id.action_list:
                        showList();
                        break;
                    case R.id.pharmacy:
                        showPharmacy();
                        break;
                    case R.id.action_sales:
                        showSales();
                        break;
                    default:
                        item.setChecked(false);
                        break;
                }
                mDrawer.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
