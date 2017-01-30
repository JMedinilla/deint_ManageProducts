package com.jmedinilla.manageproducts;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.jmedinilla.manageproducts.interfaces.RegisterPresenter;
import com.jmedinilla.manageproducts.presenter.RegisterPresenterImpl;

/**
 * Class created by JMedinilla on 2016-11-4
 * <p>
 * Sign up view
 */
public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.View {
    private RegisterPresenterImpl presenter;

    private TextInputLayout tilRegisterName;
    private TextInputLayout tilRegisterPassword;
    private TextInputLayout tilRegisterMail;
    private TextInputLayout tilRegisterBusinessName;
    private EditText edtRegisterName;
    private EditText edtRegisterPassword;
    private EditText edtRegisterMail;
    private EditText edtRegisterBusiness;
    private Spinner spinLocality;
    private Spinner spinMunicipality;
    private ViewGroup layout;

    private int spinnerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenterImpl(this);

        spinnerSelected = 0;
        tilRegisterName = (TextInputLayout) findViewById(R.id.tilRegisterUser);
        tilRegisterPassword = (TextInputLayout) findViewById(R.id.tilRegisterPassword);
        tilRegisterMail = (TextInputLayout) findViewById(R.id.tilRegisterMail);
        edtRegisterName = (EditText) findViewById(R.id.edtRegisterUser);
        edtRegisterPassword = (EditText) findViewById(R.id.edtRegisterPassword);
        edtRegisterMail = (EditText) findViewById(R.id.edtRegisterMail);
        edtRegisterBusiness = (EditText) findViewById(R.id.edtRegisterBusinessName);
        tilRegisterBusinessName = (TextInputLayout) findViewById(R.id.tilRegisterBusinessName);
        RadioGroup rdgrType = (RadioGroup) findViewById(R.id.rdgrType);
        spinLocality = (Spinner) findViewById(R.id.spinLocality);
        spinMunicipality = (Spinner) findViewById(R.id.spinMunicipality);
        layout = (RelativeLayout) findViewById(R.id.activity_home);
        addTextWatchermethod();

        rdgrType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbRegisterBusiness:
                        showCompany(true);
                        break;
                    case R.id.rbRegisterPersonMale:
                        showCompany(false);
                        break;
                    case R.id.rbRegisterPersonFemale:
                        showCompany(false);
                        break;
                }
            }
        });

        spinnerLoader();
    }

    /**
     * Method that fills the spinners
     */
    private void spinnerLoader() {
        ArrayAdapter<CharSequence> adapterLoc = ArrayAdapter.createFromResource(
                this, R.array.provincias, android.R.layout.simple_spinner_dropdown_item);
        spinLocality.setAdapter(adapterLoc);

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spinLocality:
                        TypedArray typedArray = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                        CharSequence[] res = typedArray.getTextArray(position);
                        ArrayAdapter<CharSequence> adap = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, res);
                        spinMunicipality.setAdapter(adap);
                        break;
                    case R.id.spinMunicipality:
                        spinnerSelected++;
                        if (spinnerSelected > 1) {
                            showMunicipality();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinLocality.setOnItemSelectedListener(onItemSelectedListener);
        spinMunicipality.setOnItemSelectedListener(onItemSelectedListener);
    }

    /**
     * After selecting both Locality and Municipality, it's shown to the user
     */
    private void showMunicipality() {
        Snackbar.make(findViewById(R.id.register_activity),
                getString(
                        R.string.message,
                        spinLocality.getSelectedItem().toString(),
                        spinMunicipality.getSelectedItem().toString()
                ),
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Sign up onClick method
     *
     * @param view Clicked button
     */
    public void signup(View view) {
        String mail = edtRegisterMail.getText().toString();
        String username = edtRegisterName.getText().toString();
        String pass = edtRegisterPassword.getText().toString();

        presenter.validateCredentials(username, pass, mail);
    }

    /**
     * Method that shows or not the Business name EditText
     *
     * @param show Visible or Gone
     */
    private void showCompany(boolean show) {
        tilRegisterBusinessName.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Method that contains all addTextChangedListener asignations
     */
    private void addTextWatchermethod() {
        edtRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilRegisterName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtRegisterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilRegisterPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtRegisterMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilRegisterMail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtRegisterBusiness.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilRegisterBusinessName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * Method that show the errors in the correct TextInputLayout
     * <p>
     * It reads from the errors map with the error name given by the presenter
     *
     * @param errorName Error name
     * @param viewId    TextInputLayout with the error
     */
    @Override
    public void setMessageError(String errorName, int viewId) {
        String messageError = getResources().getString(getResources().getIdentifier(errorName, "string", getPackageName()));

        switch (viewId) {
            case R.id.tilRegisterUser:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.tilRegisterPassword:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.tilRegisterMail:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * startActivity method from the interface
     */
    @Override
    public void startActivity() {
        Intent i = new Intent(RegisterActivity.this, ListFragment.class);
        startActivity(i);
    }
}
