package com.afg.MngProductDatabase;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

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

import com.afg.MngProductDatabase.Fragments.Home_Fragment;
import com.afg.MngProductDatabase.Fragments.ListProduct_Fragment;
import com.afg.MngProductDatabase.Fragments.ManagePharmacy_Fragment;
import com.afg.MngProductDatabase.Fragments.ManageProduct_Fragment;
import com.afg.MngProductDatabase.Fragments.ListPharmacy_Fragment;
import com.afg.MngProductDatabase.Fragments.Sales_Fragment;
import com.afg.MngProductDatabase.Model.Pharmacy;
import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.Presenter.ProductPresenter;
import com.afg.MngProductDatabase.utils.DialogoConfirmacion;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.IListProductListener,
        ManageProduct_Fragment.IManageListener, ListPharmacy_Fragment.IListPharmacyFragment {

    private ProductPresenter mPresenter;
    private ListProduct_Fragment mListProduct;
    private ManageProduct_Fragment mManageProduct;
    private Home_Fragment mHome;
    private ListPharmacy_Fragment mPharmacy;
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
        mPharmacy = new ListPharmacy_Fragment();
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
        else {
            newProduct.setID(oldProduct.getID());
            mPresenter.updateProduct(newProduct);
        }

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

    @Override
    public void showManagePharmacy(Pharmacy pharmacy, int mode) {

        final ManagePharmacy_Fragment fragment = ManagePharmacy_Fragment.newInstance(pharmacy, mode);

        fragment.setOnActionFinishedListener(new ManagePharmacy_Fragment.IReturnAction() {
            @Override
            public void onAddActionFinished(Pharmacy pharmacy) {

            //    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                mPharmacy.addPharmacy(pharmacy);
                getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onUpdateActionFinished(Pharmacy pharmacy) {

             //   getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                mPharmacy.updatePharmacy(pharmacy);
                getSupportFragmentManager().popBackStack();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_frameHome, fragment, "manage_pharmacy").addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreatePharmacy(Pharmacy pharmacy) {


    }
}
