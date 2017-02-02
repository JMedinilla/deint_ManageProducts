package com.afg.MngProductDatabase.Fragments;

/*
 * Copyright (c) 2017 José Luis del Pino Gallardo.
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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.R;

/**
 * Created by usuario on 2/02/17.
 */

public class ManageCategory_Fragment extends Fragment {

    private EditText edtName;
    private Button btnOk;
    private int mode;
    private IReturAction callack;

    public interface IReturAction{

        void onActionOK(Category category, int modeAction);
    }

    public void setOnActionOkListener(IReturAction callBack){

        this.callack = callBack;
    }

     public static ManageCategory_Fragment newInstance(Category category, int mode) {

        Bundle args = new Bundle();
         args.putParcelable(ListCategory_Fragment.RECOVERY_CATEGORY, category);
         args.putInt(ListCategory_Fragment.RECOVERY_MODE_OPEN, mode);
        ManageCategory_Fragment fragment = new ManageCategory_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manage_category, container, false);
        edtName = (EditText)rootView.findViewById(R.id.edtNameCategory);
        btnOk = (Button)rootView.findViewById(R.id.btnAddCategory);
        final Category c = getArguments().getParcelable(ListCategory_Fragment.RECOVERY_CATEGORY);
        mode = getArguments().getInt(ListCategory_Fragment.RECOVERY_MODE_OPEN);

        edtName.setText(c.getName());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c.setName(edtName.getText().toString());
                callack.onActionOK(c, mode);
            }
        });

        return rootView;
    }
}
