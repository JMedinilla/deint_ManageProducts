package com.afg.MngProductDatabase.Fragments;


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
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.afg.MngProductDatabase.Adapter.ProductAdapter;
import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.Presenter.ProductPresenter;
import com.afg.MngProductDatabase.R;
import com.afg.MngProductDatabase.interfaces.IProduct;
import com.afg.MngProductDatabase.interfaces.IProductPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiSelectList_Fragment extends ListFragment implements IProduct, ProductPresenter.View {


    public static Product p;

    private ProductAdapter mAdapter;
    private ListView mList;
    private int mItemPos;
    private AdapterView<?> mItemParent;
    private TextView mEmpty;
    PopupMenu popup;
    ProductPresenter mChoicePresenteR;
    private IProductPresenter mPresenter;

    private IListProductListener mCallback;

    public interface IListProductListener{
        void showManageProduct(Bundle bundle);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        mAdapter = new ProductAdapter(getContext(), this);
        mPresenter = new ProductPresenter(this);
        mChoicePresenteR = new ProductPresenter(this);
        setRetainInstance(true);


        /**
         * Esta opcion le dice a la actitivity que este fragment tiene su propio menu y llama a todos
         * los métodos de los fragments.
         */
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        super.onCreateView(inflater, container, args);
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);
        mEmpty = (TextView)rootView.findViewById(android.R.id.empty);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = getListView();
        mList.setAdapter(mAdapter);
        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //final MultiChoiceListener mcl = new MultiChoiceListener(getContext(), mChoicePresenteR);
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
                //getListView().setItemChecked(i, mcl.isPosition(i));
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_listproduct, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void añadir(View v){
        mCallback.showManageProduct(null);
    }

    @Override
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

    @Override
    public void showMessage(String message, Product product) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mAdapter = null;
        mPresenter = null;
    }

}
