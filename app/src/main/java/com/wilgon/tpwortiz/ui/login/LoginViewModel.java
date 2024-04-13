package com.wilgon.tpwortiz.ui.login;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public boolean authenticate(String username, String password) {
        // Verificamos las credenciales aquí
        return username.equals("wilson") && password.equals("ortiz");
    }
}


