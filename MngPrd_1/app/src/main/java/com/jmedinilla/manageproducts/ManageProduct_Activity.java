package com.jmedinilla.manageproducts;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import com.jmedinilla.manageproducts.adapter.ProductAdapter;

public class ManageProduct_Activity extends ListActivity {

    //Adapter for the ListView
    //private ArrayAdapter<Product> adapter;
    //private ProductAdapter_A productAdapterA;
    //private ProductAdapter_B productAdapterB;
    private ProductAdapter productAdapter;
    private FloatingActionButton btnOpenAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_product_activity);

        btnOpenAdd = (FloatingActionButton)findViewById(R.id.btnOpenAdd);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnOpenAdd.setElevation(0);
        }

        /**
         * Case 1 -> General adapter
         * It uses one TextView and the toString() from Products for each row
         */
        /*
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ((ProductApplication)getApplication()).getProducts());
        getListView().setAdapter(adapter);
        */

        /**
         * Case 2 -> Custom adapter that inflates all the rows
         *
         * A -> Worst way to do it, because it prints all the views,
         * so it's really slow
         */
        /*
        productAdapterA = new ProductAdapter_A(ManageProduct_Activity.this);
        getListView().setAdapter(productAdapterA);
        */

        /**
         * Case 3 -> Custom adapter that inflates null rows
         *
         * B -> Faster than A, because it only draws the row when
         * the view is null
         */
        /*
        productAdapterB = new ProductAdapter_B(ManageProduct_Activity.this);
        getListView().setAdapter(productAdapterB);
        */

        /**
         * Case 4 -> Custom adapter with a holder
         *
         * C -> Best way, all rows share the same objects
         */
        productAdapter = new ProductAdapter(ManageProduct_Activity.this);
        getListView().setAdapter(productAdapter);

    }

    /**
     * OnClickListener method for 'manage_product_activity.xml' buttons
     * @param view Clicked button
     */
    public void getOnClickManageProducts(View view) {
        switch (view.getId()) {
            case R.id.btnOpenAdd:
                //The button just open a new Activity so the user can add a new product
                startActivity(new Intent(ManageProduct_Activity.this, ProductForm_Activity.class));
                break;
        }
    }

    /**
     * When the user close the Activity that adds new products this
     * onResume notifies the adapter so he can draw the new row
     */
    @Override
    protected void onResume() {
        super.onResume();
        //The adapter has to change the ListView
        productAdapter.notifyDataSetChanged();
    }
}
