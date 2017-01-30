package com.jmedinilla.manageproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jmedinilla.manageproducts.adapter.AdapterRecycler;
import com.jmedinilla.manageproducts.preferences.Account_Settings;
import com.jmedinilla.manageproducts.preferences.General_Settings;

/**
 * Class created by JMedinilla
 * <p>
 * Main Activity of the project with the list
 */
public class ManageProduct_Activity extends AppCompatActivity {
    private AdapterRecycler adapter;

    private static final int TYPE_NAME = 1;
    private static final int TYPE_BRAND = 2;
    private static final int TYPE_PRICE = 3;
    private static final int TYPE_STOCK = 4;

    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_product_activity);

        adapter = new AdapterRecycler(ManageProduct_Activity.this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView rcvProduct = (RecyclerView) findViewById(R.id.rcvProduct);

        if (rcvProduct != null) {
            rcvProduct.setLayoutManager(llm);
            rcvProduct.setAdapter(adapter);
        }
    }

    /**
     * Method that inflates the menu on the Activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manageproduct, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method that controls the pressed item on the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add_product:
                intent = new Intent(ManageProduct_Activity.this, ProductForm_Activity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
            case R.id.action_orderbyname_product:
                adapter.sortAlph(TYPE_NAME);
                break;
            case R.id.action_orderbybrand_product:
                adapter.sortAlph(TYPE_BRAND);
                break;
            case R.id.action_orderbyprice_product:
                adapter.sortAlph(TYPE_PRICE);
                break;
            case R.id.action_orderbystock_product:
                adapter.sortAlph(TYPE_STOCK);
                break;
            case R.id.action_setting_general:
                intent = new Intent(ManageProduct_Activity.this, General_Settings.class);
                startActivity(intent);
                break;
            case R.id.action_setting_account:
                intent = new Intent(ManageProduct_Activity.this, Account_Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_PRODUCT:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, R.string.addedProduct, Toast.LENGTH_SHORT).show();
                }
                break;
            case EDIT_PRODUCT:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.productAddedNotify();
    }
}
