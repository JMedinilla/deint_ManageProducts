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

import com.afg.MngProductDatabase.Model.Pharmacy;
import com.afg.MngProductDatabase.R;

/**
 * Created by amador on 29/01/17.
 */

public class ManagePharmacy_Fragment extends Fragment {

    private EditText edtName, edtEmail, edtCif, edtPhone, edtAddress;
    private Button btnOk;
    private IReturnAction listener;
    private Pharmacy pharmacy;
    private int mode;

  public   interface IReturnAction{

        void onAddActionFinished(Pharmacy pharmacy);

        void onUpdateActionFinished(Pharmacy pharmacy);
    }

    public void setOnActionFinishedListener(IReturnAction listener){

        this.listener = listener;
    }

    public static ManagePharmacy_Fragment newInstance(Pharmacy pharmacy, int mode) {

        Bundle args = new Bundle();
        args.putInt(ListPharmacy_Fragment.RECOVERY_MODE, mode);
        args.putParcelable(ListPharmacy_Fragment.RECOVERY_PHARMACY, pharmacy);
        ManagePharmacy_Fragment fragment = new ManagePharmacy_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manage_pharmacy, container, false);
        pharmacy = getArguments().getParcelable(ListPharmacy_Fragment.RECOVERY_PHARMACY);
        mode = getArguments().getInt(ListPharmacy_Fragment.RECOVERY_MODE);
        edtAddress = (EditText)rootView.findViewById(R.id.edtAddressPharmacy);
        edtEmail = (EditText)rootView.findViewById(R.id.edtEmailPharmacy);
        edtName = (EditText)rootView.findViewById(R.id.edtNamePharmacy);
        edtCif = (EditText)rootView.findViewById(R.id.edtCifPharmacy);
        edtPhone = (EditText)rootView.findViewById(R.id.edtPhonePharmacy);
        btnOk = (Button)rootView.findViewById(R.id.btnManagePharmacy);

        edtName.setText(pharmacy.getName());
        edtPhone.setText(pharmacy.getPhone());
        edtCif.setText(pharmacy.getCif());
        edtEmail.setText(pharmacy.getEmail());
        edtAddress.setText(pharmacy.getAddress());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //No valido los campos porque no se trara de eso
                pharmacy.setEmail(edtEmail.getText().toString());
                pharmacy.setName(edtName.getText().toString());
                pharmacy.setAddress(edtAddress.getText().toString());
                pharmacy.setCif(edtCif.getText().toString());
                pharmacy.setPhone(edtPhone.getText().toString());

                if(mode == ListPharmacy_Fragment.MODE_NEW) {

                    listener.onAddActionFinished(pharmacy);

                }else {

                    listener.onUpdateActionFinished(pharmacy);
                }
            }
        });

        return rootView;
    }
}
