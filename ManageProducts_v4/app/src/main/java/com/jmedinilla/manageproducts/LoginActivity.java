package com.jmedinilla.manageproducts;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jmedinilla.manageproducts.interfaces.LoginPresenter;
import com.jmedinilla.manageproducts.preferences.AccountPreferencesImpl;
import com.jmedinilla.manageproducts.presenter.LoginPresenterImpl;

/**
 * Class created by JMedinilla on 2016-10-19
 * <p>
 * Login view
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    private LoginPresenterImpl presenter;

    private EditText edtUser;
    private EditText edtPass;
    private ViewGroup layout;
    private TextInputLayout tilUser;
    private TextInputLayout tilPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImpl(this);

        edtUser = (EditText) findViewById(R.id.edtLoginUser);
        edtPass = (EditText) findViewById(R.id.edtLoginPass);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        layout = (RelativeLayout) findViewById(R.id.activity_home);
        TextView txtForgot = (TextView) findViewById(R.id.login_forgot);
        Button btnOkLogin = (Button) findViewById(R.id.btnOkLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        setEditTextOnTextWatcher();

        Typeface font = Typeface.createFromAsset(getAssets(), "halloween.ttf");
        txtForgot.setTypeface(font);
        edtUser.setTypeface(font);
        edtPass.setTypeface(font);
        btnOkLogin.setTypeface(font);
        btnRegister.setTypeface(font);
    }

    /**
     * Login onClick method
     *
     * @param view Clicked button
     */
    public void getOnClickLoginActivity(View view) {
        switch (view.getId()) {
            case R.id.btnOkLogin:
                presenter.validateCredentialsLogin(edtUser.getText().toString(), edtPass.getText().toString());
                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    /**
     * Method that implements the TextChanged listeners on every EditText of the screen
     */
    private void setEditTextOnTextWatcher() {
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Method that reads the preferences to show the user his name and password
     */
    @Override
    protected void onResume() {
        super.onResume();
        AccountPreferencesImpl accountPreferencesImpl = new AccountPreferencesImpl(LoginActivity.this);
        edtUser.setText(accountPreferencesImpl.getUser());
        edtPass.setText(accountPreferencesImpl.getPassword());
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
            case R.id.tilUser:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_LONG).show();
                break;
            case R.id.tilPass:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * startActivity method from the interface
     */
    @Override
    public void startActivity() {
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }
}
