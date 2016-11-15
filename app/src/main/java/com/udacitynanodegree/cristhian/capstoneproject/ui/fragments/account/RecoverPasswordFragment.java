package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentRecoverPasswordBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.FragmentView;
import com.udacitynanodegree.cristhian.capstoneproject.ui.activities.AccountActivity;
import com.udacitynanodegree.cristhian.capstoneproject.ui.viewmodel.account.RecoverPasswordViewModel;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets.HeaderMainView;
import com.udacitynanodegree.cristhian.capstoneproject.utils.KeyboardUtil;

public class RecoverPasswordFragment extends FragmentView implements
        HeaderMainView.HeaderMainListener,
        View.OnClickListener,
        TextView.OnEditorActionListener,
        View.OnKeyListener {

    private FragmentRecoverPasswordBinding recoverPasswordBinding;
    private RecoverPasswordListener recoverPasswordListener;
    private RecoverPasswordViewModel recoverPasswordViewModel;
    private EditText editTextEmail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recoverPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recover_password, container, false);
        init();
        return recoverPasswordBinding.getRoot();
    }

    private void init() {
        editTextEmail = recoverPasswordBinding.editTextEmail.getEditText();
        recoverPasswordViewModel = new RecoverPasswordViewModel((AccountActivity) getActivity(), this);
        recoverPasswordBinding.setViewModel(recoverPasswordViewModel);
        initListeners();
    }


    private void initListeners() {
        recoverPasswordBinding.headerMainViewRecoverPassword.setHeaderMainListener(this);
        editTextEmail.setOnEditorActionListener(this);
        editTextEmail.setOnKeyListener(this);
        recoverPasswordBinding.buttonRecoverPassword.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recoverPasswordListener = (RecoverPasswordListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getName() {
        return RecoverPasswordFragment.class.getName();
    }

    @Override
    public void onClickBackHeader() {
        KeyboardUtil.hideKeyboard(getContext(), recoverPasswordBinding.getRoot());
        close();
    }

    @Override
    public void onClickRightHeader() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_recover_password:
                recoverPasswordListener.onRecoverPassword();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            executeRecoverPassword();
            return true;
        }
        return false;
    }

    private void executeRecoverPassword() {
        recoverPasswordViewModel.recoverPassword(editTextEmail);
        KeyboardUtil.hideKeyboard(getContext(), recoverPasswordBinding.getRoot());
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                keyCode == EditorInfo.IME_ACTION_DONE ||
                event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            executeRecoverPassword();
            return true;
        }
        return false;
    }

    public interface RecoverPasswordListener {
        void onRecoverPassword();
    }

    public FragmentRecoverPasswordBinding getRecoverPasswordBinding() {
        return recoverPasswordBinding;
    }
}
