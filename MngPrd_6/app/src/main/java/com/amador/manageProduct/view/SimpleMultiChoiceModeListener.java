package com.amador.manageProduct.view;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import com.amador.manageProduct.R;
import com.amador.manageProduct.presenter.ProductPresenterimpl;

class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {
    private Context context;
    private SparseBooleanArray chechedItems;
    private ProductPresenterimpl presenterimpl;
    private View view;
    private int statusBarColor;
    private int cont;

    SimpleMultiChoiceModeListener(Context context, ProductPresenterimpl presenterimpl, View view) {
        this.context = context;
        this.presenterimpl = presenterimpl;
        this.view = view;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        this.cont = 0;
        //Se ejecuta siempre que un elemento sea seleccionado o deseleccionado en la lista
        chechedItems.put(position, checked);
        for (int i = 0; i < chechedItems.size(); i++) {
            if (chechedItems.valueAt(i)) {
                this.cont++;
            }
        }
        mode.setTitle(String.valueOf(cont));
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        view.setVisibility(View.INVISIBLE);
        //En este método se está creado la barra contextual
        //Se debe cambiar el color de la barra de notificaciones
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.context_menu_list, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        chechedItems = new SparseBooleanArray();
        //Se actualiza la barra contextual
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor = ((AppCompatActivity)context).getWindow().getStatusBarColor();
            ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorError));
        }
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        //Se ejecuta cuando se pulsa en uno de los elementos del menú
        switch (item.getItemId()) {
            case R.id.context_delete:
                presenterimpl.deleteSelectedProducts();
                break;
        }
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        view.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity)context).getWindow().setStatusBarColor(statusBarColor);
        }

        this.cont = 0;
        this.chechedItems = null;
    }
}
