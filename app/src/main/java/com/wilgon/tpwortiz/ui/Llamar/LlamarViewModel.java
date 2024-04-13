package com.wilgon.tpwortiz.ui.Llamar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LlamarViewModel extends ViewModel {

    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void realizarLlamada(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            mensajeError.setValue("Ingrese un número válido");
        } else {

        }
    }
}
