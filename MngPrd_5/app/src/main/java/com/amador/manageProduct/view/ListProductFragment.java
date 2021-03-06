package com.amador.manageProduct.view;

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

import com.amador.manageProduct.R;
import com.amador.manageProduct.adapter.ProductAdapter;
import com.amador.manageProduct.interfaces.ManagePresenter;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.interfaces.ProductPresenter;
import com.amador.manageProduct.presenter.ProductPresenterimpl;

import java.util.List;

public class ListProductFragment extends Fragment implements ManagePresenter.View  {

    public static final String PRODUCT_KEY = "product";
    private ProductAdapter adapter;
    private ListView listProduct;
    private CoordinatorLayout layout;
    private TextView emptyProduct;
    private FloatingActionButton btnAddProduct;
    private boolean click = false;
    private ListProductListener mCallback;
    ProductPresenter presenter;

    public interface ListProductListener{

        void showManageProduct(Bundle bundle);
    }

    public static ListProductFragment newInstance(Bundle arguments) {

        Bundle args = new Bundle();

        ListProductFragment fragment = new ListProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRetainInstance(true);
        setHasOptionsMenu(true);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_manage_product, null);
        layout = (CoordinatorLayout)rootView.findViewById(R.id.parentList);
        btnAddProduct = (FloatingActionButton)rootView.findViewById(R.id.floatButonAdd);
        adapter = new ProductAdapter(getContext());
        presenter = new ProductPresenterimpl(this);
        listProduct = (ListView)rootView.findViewById(R.id.list);
        listProduct.setAdapter(adapter);

        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                mCallback.showManageProduct(bundle);
            }
        });

       registerForContextMenu(listProduct);


        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.showManageProduct(null);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){

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
                presenter.deleteProduct((Product) listProduct.getItemAtPosition(info.position));
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_manage_product, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_orderbyname_product:
                adapter.orderBy(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProducts(List<Product> products) {
        adapter.updateProduct(products);
    }

    @Override
    public void showMessageDelete(final Product product) {
        Snackbar.make(layout, "Producto eliminado", Snackbar.LENGTH_LONG)
                .setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.addProduct(product);
                    }
                }).show();

        //Otra visión a la hora de hacerlo (en lugar de eliminar siempre y añadir
        // si se pulsa en deshacer)

        //En esta otra solución, lo que se hace es no eliminar, sino dar el aviso
        //y hacerlo después de que termine la visualización del Snackbar, dando por
        //hecho, por ejemplo, que si se ha dejado que desaparezca o se ha lanzado
        //para que desaparezca, es que hay que eliminarlo realmente

        //SETCALLBACK (realizar una llamada a un método callback de un Snackbar)
        //incluso si el Snackbar se ha eliminado mediante Swipe
                /*
        .setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (event != DISMISS_EVENT_ACTION) {
                    presenter.deleteProductSnack(product);
                }
            }
        })*/
    }

    private void hideList(boolean hide){


    }

    public void showEmptyState(){

    }

    @Override
    public void showMessage(String message) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        presenter = null;
        mCallback = null;
    }
}
