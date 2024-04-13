package com.wilgon.tpwortiz.ui.MiFoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.wilgon.tpwortiz.R;

public class MiFotoFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mifoto, container, false);

        // Obtenemos la referencia al ImageView en el layout
        ImageView imageView = root.findViewById(R.id.ivimagen);

        // Cargamos la imagen desde los recursos
        imageView.setImageResource(R.drawable.foto);

        return root;
    }
}
