package com.wilgon.tpwortiz.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wilgon.tpwortiz.MainActivity;
import com.wilgon.tpwortiz.R;
import com.wilgon.tpwortiz.databinding.ActivityLoginBinding;

public class ActivityLogin extends AppCompatActivity {
    //declaraciones
    private LoginViewModel vm; //declara el view model
    private ActivityLoginBinding binding;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(LoginViewModel.class);

        //binding.btnLogin.setOnClickListener(view -> autenticar());
        //receiver = new MyReceiver();


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticar();
            }
        });

        registrarBroadcast();
    }





    private void registrarBroadcast() {

        this.receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void autenticar() {
        String username = binding.etUsuario.getText().toString();
        String password = binding.etContraseA.getText().toString();

        if (vm.authenticate(username, password)) {
            // Autenticación exitosa, abrir la siguiente actividad
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            // Iniciar la segunda actividad (en este caso, MainActivity)
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            startActivity(intent);

            // Finaliza esta actividad si no queremos volver a ella presionando el boton de volver
            finish();
        } else {
            // Autenticación fallida, mostramos mensaje de error
            Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

}
