package com.wilgon.tpwortiz.ui.Llamar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wilgon.tpwortiz.databinding.FragmentLlamarBinding;

public class LlamarFragment extends Fragment {

    private FragmentLlamarBinding binding;
    private LlamarViewModel llamarViewModel;

    private static final int REQUEST_CALL = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        llamarViewModel =
                new ViewModelProvider(this).get(LlamarViewModel.class);

        binding = FragmentLlamarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editText = binding.etLlamar;
        final Button button = binding.btnLlamar;



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = editText.getText().toString();
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    realizarLlamada();
                }
            }
        });

        llamarViewModel.getMensajeError().observe(getViewLifecycleOwner(), mensajeError -> {
            if (mensajeError != null) {
                mostrarMensajeError(mensajeError);
            }
        });

        return root;
    }

    private void mostrarMensajeError(String mensaje) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                realizarLlamada();
            } else {
                Toast.makeText(requireContext(), "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean esNumeroValido(String numero) {
        // Comprueba si el texto contiene solo dígitos numericos
        return numero.matches("\\d+");
    }

    private void realizarLlamada() {
        String numero = binding.etLlamar.getText().toString();
        if (esNumeroValido(numero)) {
            String llamo = "tel:" + numero;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(llamo)));
        } else {
            Toast.makeText(requireContext(), "Ingrese un número válido", Toast.LENGTH_SHORT).show();
          //  Toast.makeText(requireContext(), "Ingrese un número válido", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
