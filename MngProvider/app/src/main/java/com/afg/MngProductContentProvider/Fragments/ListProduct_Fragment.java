package com.afg.MngProductContentProvider.Fragments;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.afg.MngProductContentProvider.Model.Product;
import com.afg.MngProductContentProvider.Adapter.ProductAdapter;
import com.afg.MngProductContentProvider.Presenter.ProductPresenter;
import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.interfaces.IProduct;

public class ListProduct_Fragment extends ListFragment implements IProduct, ProductPresenter.View{

    public static Product p;

    private ProductAdapter mAdapter;
    private ListView mList;
    private int mItemPos;
    private AdapterView<?> mItemParent;
    private TextView mEmpty;
    PopupMenu popup;
    private FloatingActionButton mFab;
    private ProgressDialog dialog;

    private IListProductListener mCallback;

    public static ListProduct_Fragment getInstance(Bundle args){
        ListProduct_Fragment f = new ListProduct_Fragment();
        f.setArguments(args);
        return  f;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        dialog = new ProgressDialog(getContext());
        mAdapter = new ProductAdapter(getContext(), this);
        setRetainInstance(true);


        /**
         * Esta opcion le dice a la actitivity que este fragment tiene su propio menu y llama a todos
         * los métodos de los fragments.
         */
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        super.onCreateView(inflater, container,  args);
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);
        mEmpty = (TextView)rootView.findViewById(android.R.id.empty);
        mFab = (FloatingActionButton)rootView.findViewById(R.id.fab_añadir);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listproduct, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = getListView();
        setListAdapter(mAdapter);


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle potatoe = new Bundle();
                potatoe.putParcelable(PRODUCT_KEY, (Product) parent.getItemAtPosition(i));
                mCallback.showManageProduct(potatoe);
            }
        });

        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItemParent = adapterView;
                mItemPos = i;

                popup  = new PopupMenu(getContext(), view);
                popup.setGravity(Gravity.END);
                popup.getMenuInflater().inflate(R.menu.delete_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        p = (Product) mItemParent.getItemAtPosition(mItemPos);
                        mCallback.showDeletePopUp(p);
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showManageProduct(null);
            }
        });
    }

    public void showProduct() {
        mAdapter.updateListProduct();

    }

    private  void hideList(boolean hide){
        mList.setVisibility(hide ? View.VISIBLE : View.GONE);
        mEmpty.setVisibility(hide ? View.VISIBLE : View.GONE);

    }

    public void showEmptyState(boolean show){
        hideList(show);


    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    public void showMessage(String message, final Product product){

        Snackbar.make(getView(), "Producto Eliminado", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override //Aqui eliminamos si o si, si clicka en undo, volvemos a añadir.
                    public void onClick(View view) {
                        mCallback.undoDeleting(product);
                    }
                }).show();
    }

    @Override
    public void showProgressDialog() {
        dialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        dialog.dismiss();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mAdapter = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (IListProductListener)activity;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort_product:
                mAdapter.sortAlphabetically();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface IListProductListener{
        void showManageProduct(Bundle bundle);
        void showDeletePopUp(Product product);
        void undoDeleting(Product product);
    }
}