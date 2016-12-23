package com.jmedinilla.manageproducts;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jmedinilla.manageproducts.adapter.AdapterList;
import com.jmedinilla.manageproducts.interfaces.IProduct;
import com.jmedinilla.manageproducts.interfaces.ProductPresenter;
import com.jmedinilla.manageproducts.model.Product;
import com.jmedinilla.manageproducts.presenter.ProductPresenterImpl;

import java.util.List;

/**
 * Class created by JMedinilla
 * <p>
 * Main Activity of the project with the list
 */
public class ListFragment extends Fragment implements ProductPresenter.View {
    private static final int TYPE_NAME = 1;
    private static final int TYPE_BRAND = 2;
    private static final int TYPE_PRICE = 3;
    private static final int TYPE_STOCK = 4;

    private AdapterList adapterList;
    private ListView rcvProductList;
    private FloatingActionButton btnAdd;
    private TextView txtEmpty;
    private CoordinatorLayout layout;
    private ListProductListener mCallback;
    private ProductPresenter presenter;

    public interface ListProductListener {
        void showManageProduct(Bundle bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterList = new AdapterList(getContext());
        presenter = new ProductPresenterImpl(this);

        setRetainInstance(true);
        /*
        Esta opción le dice a la Activity que el Fragment tiene su propio
        menú y llama al método callback onCreateOptionsMenu()
         */
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapterList = null;
        presenter = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getContext().toString() + " must implement ListProductListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rcvProductList = (ListView) view.findViewById(R.id.rcvProductList);
        layout = (CoordinatorLayout) view.findViewById(R.id.coordinatorListActivity);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAddForm);
        txtEmpty = (TextView) view.findViewById(R.id.list_empty);

        if (rcvProductList != null) {
            rcvProductList.setAdapter(adapterList);
            rcvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(IProduct.PRODUCT_KEY, (Product) parent.getItemAtPosition(position));
                    mCallback.showManageProduct(bundle);
                }
            });
        }

        if (rcvProductList != null) {
            registerForContextMenu(rcvProductList);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.showManageProduct(null);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Method that controls the pressed item on the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("List options");
        getActivity().getMenuInflater().inflate(R.menu.context_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.context_delete:
                throwDialogDelete(info);
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void throwDialogDelete(final AdapterView.AdapterContextMenuInfo info) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(R.string.title_delete);
        builder.setMessage(R.string.message_delete);
        builder.setPositiveButton(R.string.delete_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteProduct((Product) rcvProductList.getItemAtPosition(info.position));
                /*
                ((AdapterList) rcvProductList.getAdapter()).removePosition(info.position);
                */
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

    public void showProducts(List<Product> products) {
        adapterList.updateProduct(products);
    }

    private void hideList(boolean hide) {
        rcvProductList.setVisibility(hide ? View.GONE : View.VISIBLE);
        txtEmpty.setVisibility(hide ? View.VISIBLE : View.GONE);
    }

    public void showEmptyText(boolean show) {
        hideList(show);
    }

    public void showMessage(String message) {

    }
}
