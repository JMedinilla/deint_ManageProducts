package com.jmedinilla.manageproducts;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jmedinilla.manageproducts.interfaces.ILoginMvp;
import com.jmedinilla.manageproducts.presenter.LoginPresenter;

/**
 * Class crerated by JMedinilla on 2016-10-19
 *
 * View for the Login MVP
 */
public class Login_Activity extends AppCompatActivity implements ILoginMvp.View {

    //Objects
    private ILoginMvp.Presenter loginMvp;
    //Components
    private EditText edtUser;
    private EditText edtPass;
    private TextInputLayout tilUser;
    private TextInputLayout tilPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //Objects
        loginMvp = new LoginPresenter(this);
        //Components
        edtUser = (EditText)findViewById(R.id.edtLoginUser);
        edtPass = (EditText)findViewById(R.id.edtLoginPass);
        tilUser = (TextInputLayout)findViewById(R.id.tilUser);
        tilPass = (TextInputLayout)findViewById(R.id.tilPass);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        /**
         * When the user clicks the Login button, the user and password have to be evaluated
         * before executing the next Activity
         */
        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (loginMvp.validateCredentials(edtUser.getText().toString(), edtPass.getText().toString())) {
                        startActivity(new Intent(Login_Activity.this, ManageProduct_Activity.class));
                    }
                }
            });
        }

        setEditTextOnTextWatcher();
    }

    /**
     * Method that implements the TextChanged listeners on every EditText of the screen
     */
    private void setEditTextOnTextWatcher() {
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilPass.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
    }

    /**
     * Method that sends the error so the user can see them
     * @param message Error to show
     * @param idView The component with the error
     */
    @Override
    public void setLoginMessageError(String message, int idView) {
        switch (idView) {
            case R.id.edtLoginUser:
                tilUser.setError(message);
                break;
            case R.id.edtLoginPass:
                tilPass.setError(message);
                break;
        }
    }
}
