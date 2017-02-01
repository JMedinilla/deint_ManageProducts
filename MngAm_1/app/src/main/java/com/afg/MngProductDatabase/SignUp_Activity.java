package com.afg.MngProductDatabase;

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

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.afg.MngProductDatabase.Presenter.SignUpPresenter;
import com.afg.MngProductDatabase.interfaces.ISignUp;

public class SignUp_Activity extends AppCompatActivity implements ISignUp.View {

    //region Private Variables
    private RadioGroup mRg;
    private EditText mEtEmpresa;
    private Spinner mSpnProvincia;
    private Spinner mSpnLocalidad;
    private TextInputLayout mTilMail;
    private TextInputLayout mTilUsername;
    private TextInputLayout mTilPassword;
    private SignUpPresenter mPresenter;
    private ViewGroup layout;
    private AdapterView.OnItemSelectedListener mSpinnerListener;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mRg = (RadioGroup)findViewById(R.id.rg_generos);
        mEtEmpresa = (EditText)findViewById(R.id.editText);
        mSpnProvincia = (Spinner)findViewById(R.id.spn_provincia);
        mSpnLocalidad = (Spinner)findViewById(R.id.spn_localidad);
        mTilMail = (TextInputLayout)findViewById(R.id.til_email);
        mTilUsername = (TextInputLayout)findViewById(R.id.til_username);
        mTilPassword = (TextInputLayout)findViewById(R.id.til_password2);
        mPresenter = new SignUpPresenter(this);
        layout = (ViewGroup)findViewById(R.id.activity_sign_up);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_Empresa)
                    mEtEmpresa.setVisibility(View.VISIBLE);
                else
                    mEtEmpresa.setVisibility(View.GONE);
            }
        });

        loadSpinnerProvincia();
    }

    /**
     * Setting adapter into "Provincia" Spinner.
     * Getting data from "provincias.xml" array.
     */
    private void loadSpinnerProvincia(){

        mSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner sp = (Spinner) view.getParent();
                switch (sp.getId()){
                    case R.id.spn_provincia:
                        cargarLocalidad(mSpnProvincia.getSelectedItemPosition());
                        break;
                    case R.id.spn_localidad:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, R.layout.support_simple_spinner_dropdown_item);
        mSpnProvincia.setOnItemSelectedListener(mSpinnerListener);
        mSpnProvincia.setAdapter(adapter);
        }


    /**
     * Setting adapter into "Localidad" Spinner.
     * Getting data from typed array. {@link TypedArray}
     * @param pos Position on mSpnProvincia spinner.
     */
    private void cargarLocalidad(int pos){
        TypedArray aLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] localidades = aLocalidades.getTextArray(pos);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SignUp_Activity.this, android.R.layout.simple_spinner_dropdown_item, localidades);
        mSpnLocalidad.setAdapter(adapter);
    }


    /**
     * Sign Up Method. SignUp button actions.
     * Take text from view and send it to presenter
     * @param v View from Button
     */
    public void registrarse(View v){

        //Recoger los datos de las vista y mandarlos al presentador.

        String mail = mTilMail.getEditText().getText().toString();
        String username = mTilUsername.getEditText().getText().toString();
        String pass = mTilPassword.getEditText().getText().toString();

        mPresenter.validateCredentials(username, pass, mail);
    }


    /**
     * Info method. Shows a Toast with Selected Item on Provincia Spinner
     */
    public void verLocalidad(){
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.double_message,
                mSpnProvincia.getSelectedItem(),
                mSpnLocalidad.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
    }


    /**
     * Set Message error into Viewa.
     * Uses @link Resources#getIdentifier(String, String, String)}
     * @param nameResource String name error on Errors.xml
     * @param View View which have errors.
     */
    @Override
    public void setMessageError(String nameResource, int View) {

        String messageError = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));

        switch (View) {
            case R.id.til_user: //Incorrect user case
                //mTilUser.setError(error);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.til_password: //Incorrect Password Case
                //mTilPass.setError(messageError);
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            case R.id.til_email:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
            case 0: //Correct Login
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                startActivity();
                break;
        }
    }

    /**
     * If imput text have no errors it starts activity.
     */
    public void startActivity(){
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
        finish();
    }
}
