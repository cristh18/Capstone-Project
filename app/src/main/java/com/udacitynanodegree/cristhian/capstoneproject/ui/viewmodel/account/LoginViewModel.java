package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.LoginFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterUserFragment;
import com.udacitynanodegree.cristhian.capstoneproject.utils.SnackBarUtil;

public class LoginViewModel {

    private AccountActivity accountActivity;
    private LoginFragment loginFragment;

    public LoginViewModel(LoginFragment loginFragment, AccountActivity accountActivity) {
        this.accountActivity = accountActivity;
        this.loginFragment = loginFragment;
    }

    public void signIn(View view) {
        String userEmail = loginFragment.getSignInBinding().editTextEmail.getEditText().getText().toString();
        String userPassword = loginFragment.getSignInBinding().editTextPassword.getEditText().getText().toString();
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
            accountActivity.showProgressDialog(IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_logging_user));
            IronHideApplication.getFirebaseAuth().signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(accountActivity, task -> {
                if (task.isSuccessful()) {
                    accountActivity.goToMainActivity();
                } else {
                    SnackBarUtil.showSnackBar(loginFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_sign_in_failed), Snackbar.LENGTH_SHORT, true);
                }
                accountActivity.dismissProgressDialog();
            });
        } else {
            SnackBarUtil.showSnackBar(loginFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_error_register_user), Snackbar.LENGTH_SHORT, true);
        }
    }

    public void googleSignIn(View view) {
        Toast.makeText(IronHideApplication.getApp().getApplicationContext(), "GOOGLE SIGN IN", Toast.LENGTH_SHORT).show();
    }

    public void forgotPassword(View view) {
        Toast.makeText(IronHideApplication.getApp().getApplicationContext(), "FORGOT PASSWORD", Toast.LENGTH_SHORT).show();
        accountActivity.showFragment(new RecoverPasswordFragment());
    }

    public void signUp(View view) {
        Toast.makeText(IronHideApplication.getApp().getApplicationContext(), "SIGN UP", Toast.LENGTH_SHORT).show();
        accountActivity.showFragment(new RegisterUserFragment());
    }
}
