package com.amador.manageProduct.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.IValidateAccount;
import com.amador.manageProduct.presenter.LoginPresenter;

/**
 * @author Amador Fernandez Gonzalez
 *         Clase para el login de la aplicacion que implementa:
 * @see com.amador.manageProduct.interfaces.IValidateAccount.View
 */
public class LoginActivity extends AppCompatActivity implements IValidateAccount.View {


    private LoginPresenter presenter;
    private EditText edtPwd;
    private EditText edtUser;
    private TextInputLayout tilPwd, tilUser;
    private TextView txvForget;
    private Button btnLogin;
    private ViewGroup layout;
    private final String TAG ="TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        presenter = new LoginPresenter(LoginActivity.this);
        edtUser =  (EditText) findViewById(R.id.edtLoginUser);
        edtPwd =  (EditText) findViewById(R.id.edtLoginPass);
        tilUser = (TextInputLayout)findViewById(R.id.tilUser);
        tilPwd = (TextInputLayout)findViewById(R.id.tilPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txvForget = (TextView)findViewById(R.id.txvSignUp);
        layout = (RelativeLayout)findViewById(R.id.activity_home);
        Typeface font = Typeface.createFromAsset(getAssets(), "mi_font.ttf");
        txvForget.setTypeface(font);

        txvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


        //region TextChanged
        edtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO: Implement
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUser.setError(null);
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
                tilPwd.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.validateCredentialsLogin(edtUser.getText().toString(), edtPwd.getText().toString());
            }
        });

        //endregion

    }


    /**
     * Metodo para lanzar errores a la interfaz de usuario
     *
     * @param nameResource nombre del recurso para su localizacion en el mapa
     * @param view         id del recurso por el que se sacara el mensaje
     */
    public void setMessageError(String nameResource, int view) {

        String messageError = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));

        switch (view){
            case R.id.tilUser:
                tilUser.setError(messageError);
                break;
            case R.id.tilPass:
                tilPwd.setError(messageError);
                break;
            case 0:
                login();
                break;
        }
    }


    /**
     * Metodo para lanzar la actividad general de la aplicacion
     * */
    private void login(){
        Intent intent = new Intent(this, ManageProductFragment.class);
        startActivity(intent);
        finish();
    }

    /**
     * Metodo para lanzar la interfaz de registro
     */
    public void registrarse(){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);

    }

    /**
     * Metodo para lanzar la actividad general de la aplicacion
     * */
    public void startActivity() {
        Intent intent = new Intent(LoginActivity.this, ManageProductFragment.class);
        startActivity(intent);
    }
}
