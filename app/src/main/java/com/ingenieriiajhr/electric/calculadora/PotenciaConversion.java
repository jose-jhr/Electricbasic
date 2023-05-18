package com.ingenieriiajhr.electric.calculadora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenieriiajhr.electric.R;


public class PotenciaConversion extends Fragment {


    public PotenciaConversion() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    EditText edtHp,edtVoltaje,edtCorriente;
    Button calculate1,calculate2;
    TextView txtResult1,txtResult2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_potencia_conversion, container, false);

        edtHp = root.findViewById(R.id.edtHp);
        edtVoltaje = root.findViewById(R.id.edtVoltaje);
        edtCorriente = root.findViewById(R.id.edtCorriente);

        txtResult1 = root.findViewById(R.id.txtResult1);
        txtResult2 = root.findViewById(R.id.txtResult2);

        calculate1 = root.findViewById(R.id.btnCalpot);
        calculate2 = root.findViewById(R.id.btnCalpot2);

        calculate1.setOnClickListener(click->{
            if (!TextUtils.isEmpty(edtHp.getText().toString())){
                txtResult1.setText("P1: "+ Float.parseFloat(edtHp.getText().toString()) * 745.7f);
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Requiere HP", Toast.LENGTH_SHORT).show();
            }
        });

        calculate2.setOnClickListener(click2->{
            if (!TextUtils.isEmpty(edtCorriente.getText().toString())){
                if (!TextUtils.isEmpty(edtVoltaje.getText().toString())){
                    txtResult2.setText("P2: "+ stof(edtCorriente.getText().toString()) * stof(edtVoltaje.getText().toString()));
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Requiere Voltaje", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity().getApplicationContext(), "Requiere Corriente", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    private float stof(String value){
        return Float.parseFloat(value);
    }

}