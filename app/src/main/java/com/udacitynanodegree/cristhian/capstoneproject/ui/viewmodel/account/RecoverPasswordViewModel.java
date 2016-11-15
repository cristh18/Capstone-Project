package com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.app.IronHideApplication;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RecoverPasswordFragment;
import com.udacitynanodegree.cristhian.capstoneproject.utils.SnackBarUtil;

public class RecoverPasswordViewModel implements OnCompleteListener {

    private AccountActivity accountActivity;
    private RecoverPasswordFragment recoverPasswordFragment;

    public RecoverPasswordViewModel(AccountActivity accountActivity, RecoverPasswordFragment recoverPasswordFragment) {
        this.accountActivity = accountActivity;
        this.recoverPasswordFragment = recoverPasswordFragment;
    }

    public void recoverPassword(View view) {
        accountActivity.showProgressDialog(IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_logging_user));
        String email = recoverPasswordFragment.getRecoverPasswordBinding().editTextEmail.getEditText().getText().toString().trim();
        if (!TextUtils.isEmpty(email)) {
            IronHideApplication.getFirebaseAuth().sendPasswordResetEmail(email).addOnCompleteListener(this);
        }
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            recoverPasswordFragment.close();
            SnackBarUtil.showSnackBar(recoverPasswordFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_recover_password_successful), Snackbar.LENGTH_SHORT, false);
        } else {
            SnackBarUtil.showSnackBar(recoverPasswordFragment.getView(), IronHideApplication.getApp().getApplicationContext().getString(R.string.copy_recover_password_failed), Snackbar.LENGTH_SHORT, true);
        }
        accountActivity.dismissProgressDialog();
    }
}
