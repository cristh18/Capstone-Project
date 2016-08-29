package com.udacitynanodegree.cristhian.capstoneproject.interfaces;

public interface LoginListener {

    void onSignIn(String userEmail, String userPassword);
    void onGoogleSignIn();
    void onForgotPassword();
    void onSignUp();
}

