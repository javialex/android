package com.android.cognos.lab05;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    EditText etxNumero1;
    EditText etxNumero2;
    Button btnCalcular;
    TextView txvResultadoSuma;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewContainer = inflater.inflate(R.layout.fragment_blank, container, false);
        etxNumero1 = (EditText) viewContainer.findViewById(R.id.etxNumero1);
        etxNumero2 = (EditText) viewContainer.findViewById(R.id.etxNumero2);
        btnCalcular = (Button) viewContainer.findViewById(R.id.btnSumar);
        txvResultadoSuma = (TextView) viewContainer.findViewById(R.id.txvResultadoSuma);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(etxNumero1.getText().toString());
                int num2 = Integer.parseInt(etxNumero2.getText().toString());
                txvResultadoSuma.setText("El resultado es: " + (num1 + num2));
            }
        });


        return viewContainer;

    }

}
