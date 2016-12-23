package com.jmedinilla.manageproducts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jmedinilla.manageproducts.adapter.AdapterList;
import com.jmedinilla.manageproducts.interfaces.IProduct;
import com.jmedinilla.manageproducts.model.Product;
import com.jmedinilla.manageproducts.preferences.Account_Settings;
import com.jmedinilla.manageproducts.preferences.General_Settings;

/**
 * Class created by JMedinilla
 * <p>
 * Main Activity of the project with the list
 */
public class ManageProduct_Activity extends AppCompatActivity {
    private AdapterList adapterList;
    private ListView rcvProductList;
    private CoordinatorLayout layout;

    private int posEdited;
    private int lastPosLong;

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

        posEdited = -1;
        lastPosLong = -1;

        rcvProductList = (ListView) findViewById(R.id.rcvProductList);
        adapterList = new AdapterList(ManageProduct_Activity.this);
        layout = (CoordinatorLayout) findViewById(R.id.coordinatorListActivity);

        if (rcvProductList != null) {
            rcvProductList.setAdapter(adapterList);

            rcvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(IProduct.PRODUCT_KEY, (Product) parent.getItemAtPosition(position));
                    posEdited = position;
                    Intent intent = new Intent(ManageProduct_Activity.this, ProductForm_Activity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, EDIT_PRODUCT);
                }
            });
        }

        registerForContextMenu(rcvProductList);

        rcvProductList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lastPosLong = position;
                return false;
            }
        });
    }

    public void getOnClickListActivity(View view) {
        switch (view.getId()) {
            case R.id.btnAddForm:
                Intent intent = new Intent(ManageProduct_Activity.this, ProductForm_Activity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
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
            case R.id.action_orderbyname_product:
                adapterList.sortAlph(TYPE_NAME);
                break;
            case R.id.action_orderbybrand_product:
                adapterList.sortAlph(TYPE_BRAND);
                break;
            case R.id.action_orderbyprice_product:
                adapterList.sortAlph(TYPE_PRICE);
                break;
            case R.id.action_orderbystock_product:
                adapterList.sortAlph(TYPE_STOCK);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.context_delete:
                throwDialogDelete();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void throwDialogDelete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ManageProduct_Activity.this);
        builder.setCancelable(false);
        builder.setTitle(R.string.title_delete);
        builder.setMessage(R.string.message_delete);
        builder.setPositiveButton(R.string.delete_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AdapterList) rcvProductList.getAdapter()).removePosition(lastPosLong);
                Snackbar.make(layout, R.string.product_deleted, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.delete_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(layout, R.string.action_canceled, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_PRODUCT:
                if (resultCode == RESULT_OK) {
                    Product product = data.getExtras().getParcelable(IProduct.NEW_PRODUCT_KEY);
                    ((AdapterList) rcvProductList.getAdapter()).addProduct(product);
                    Snackbar.make(layout, R.string.addedProduct, Snackbar.LENGTH_SHORT).show();
                }
                break;
            case EDIT_PRODUCT:
                if (resultCode == RESULT_OK) {
                    Product product_before = data.getExtras().getParcelable(IProduct.PRODUCT_KEY);
                    Product product_after = data.getExtras().getParcelable(IProduct.NEW_PRODUCT_KEY);
                    ((AdapterList) rcvProductList.getAdapter()).removeProduct(product_before);
                    ((AdapterList) rcvProductList.getAdapter()).addProductPosition(product_after, posEdited);
                    Snackbar.make(layout, R.string.editedProduct, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterList.notifyDataSetChanged();
    }
}
