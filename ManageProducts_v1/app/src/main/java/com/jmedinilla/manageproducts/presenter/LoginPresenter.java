package com.jmedinilla.manageproducts.presenter;

import android.content.Context;
import android.text.TextUtils;
import com.jmedinilla.manageproducts.interfaces.ILoginMvp;
import com.jmedinilla.manageproducts.R;

/**
 * Class creater by JMedinilla on 2016-10-19
 *
 * Presenter for the Login MVP
 */
public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    /**
     * Method that verifies that if user meets the requirements
     * @param user Username
     * @param pass User password
     * @return True of False if it's a valid user
     */
    @Override
    public boolean validateCredentials(String user, String pass) {
        boolean result = false;
        //User cannot be empty
        if (TextUtils.isEmpty(user)) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_data_user_empty), R.id.edtLoginUser);
        }
        //Password cannot be empty
        else if (TextUtils.isEmpty(pass)) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_data_pass_empty), R.id.edtLoginPass);
        }
        //User has to be 6 or more characters long
        else if (user.length() <=5) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_user_length), R.id.edtLoginUser);
        }
        //Password has to be 8 or more characters long
        else if (pass.length() <=7) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_password_length), R.id.edtLoginPass);
        }
        //Password needs 1 or more numbers
        else if (!pass.matches("^.*[0-9]+.*$")) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_password_digit), R.id.edtLoginPass);
        }
        //Password needs 1 or more lowercase and uppercase characters
        else if (!pass.matches("^.*(?=.*[a-z])(?=.*[A-Z]).*$")) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_password_case), R.id.edtLoginPass);
        }
        //User can be 16 or less characters long
        else if (user.length() > 16) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_long_user), R.id.edtLoginUser);
        }
        //Password can be 40 or less characters long
        else if (pass.length() > 40) {
            view.setLoginMessageError(((Context)view).getResources().getString(R.string.loginerror_long_pass), R.id.edtLoginPass);
        }
        //The user and the password meets the requirements
        else {
            result = true;
        }
        return result;
    }
}
