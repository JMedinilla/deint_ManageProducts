package com.amador.manageProduct.view;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.ISignUp;
import com.amador.manageProduct.presenter.SingUpPresenter;

/**
 * @author Amador Fernandez Gonzalez
 *         Clase para la interfaz de registro de la aplicacion
 */
public class SignUpActivity extends AppCompatActivity implements ISignUp.View {

    private Spinner spCounty;
    private Spinner spCity;
    private Button btnSignup;
    private EditText edtNameUser, edtEmailUser, edtPwd, edtUserBusiness;
    private CheckBox checkBoxSendUpdate;
    private RadioGroup typeClient;
    private TextInputLayout tilNameUser, tilPwdUser, tilEmail, tilNameCompany;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private SingUpPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);


        presenter = new SingUpPresenter(this);
        spCounty = (Spinner) findViewById(R.id.spnProvincia);
        spCity = (Spinner) findViewById(R.id.spnLocalidad);
        btnSignup = (Button)findViewById(R.id.btnSignupOk);
        edtNameUser = (EditText)findViewById(R.id.edtUsername);
        edtPwd = (EditText)findViewById(R.id.edtUserPassword);
        edtEmailUser = (EditText)findViewById(R.id.edtEmail);
        edtUserBusiness = (EditText)findViewById(R.id.edtCompany);
        checkBoxSendUpdate = (CheckBox)findViewById(R.id.chb_newsletters);
        tilEmail = (TextInputLayout)findViewById(R.id.tilEmail);
        tilPwdUser = (TextInputLayout)findViewById(R.id.tilUserPassword);
        tilNameUser = (TextInputLayout)findViewById(R.id.tilUsername);



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenter.validateCredentials(edtNameUser.getText().toString(), edtPwd.getText().toString(),
                        edtEmailUser.getText().toString());

            }
        });


        tilNameCompany = (TextInputLayout) findViewById(R.id.tilCompany);
        typeClient = (RadioGroup) findViewById(R.id.rgCompany);
        typeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtClient:
                        showCompany(false);
                        break;
                    case R.id.rbtCompany:
                        showCompany(true);
                        break;
                }
            }
        });

        loadSpinnerCounty();

        edtNameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tilNameUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tilPwdUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtEmailUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    /**
     *Metodo para mostrar o no el campo para el registro del nombre de la empresa del usuario
     * @param b true si se desea mostrar, false si no.
     * */
    private void showCompany(boolean b) {
        tilNameCompany.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    /**
     * Metodo para lanzar la actividad general de la aplicacion si las credenciales son validas
     *
     * */
    public void signup(View view) {


        presenter.validateCredentials(edtNameUser.getText().toString(), edtPwd.getText().toString(),
                edtEmailUser.getText().toString());

    }

    /**
     * Metodo para cargar las provicias y sus localidades haciendo uso de
     * @link {{@link #loadSpinnerCity(int pos)}}
     * */
    private void loadSpinnerCounty() {

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                if(view != null){

                    CheckedTextView item = (CheckedTextView)view;
                    Spinner spinner = (Spinner)item.getParent();
                    switch (spinner.getId()) {
                        case R.id.spnProvincia:
                            loadSpinnerCity(position);
                            break;
                        case R.id.spnLocalidad:
                            break;
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };


      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SignUpActivity.this,
               R.array.provincias, android.R.layout.simple_spinner_dropdown_item);
        spCounty.setOnItemSelectedListener(spinnerListener);
        spCounty.setAdapter(adapter);


    }

    /**
     * Carga las localidas segun la provincia inidicada
     * @param pos posicion del nombre del array que contiene las provincias
     * */
    private void loadSpinnerCity(int pos) {

        TypedArray arraylocalities = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] localities = arraylocalities.getTextArray(pos);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SignUpActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                localities);

        spCity.setAdapter(adapter);
    }


    /**
     * Metodo para lanzar los mensages de error
     * @param error Referencia al array donde se encuentra el texto para el mensage
     * @param view id del wigged por donde ha de salir el mensage
     * */
    @Override
    public void setMessageError(String error, int view) {


        String messageError = getResources().getString(getResources().getIdentifier(error, "string", getPackageName()));

        switch (view){
            case R.id.tilUser:
                //tilUser.setError(error);
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            case R.id.tilPass:
                //tilPwd.setError(messageError);
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            case R.id.tilEmail:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * Metodo para lanzar la actividad para registrar productos
     * */
    public void startActivity(){

        Intent intent = new Intent(this, ManageProductFragment.class);
        startActivity(intent);
        finish();
    }
}
