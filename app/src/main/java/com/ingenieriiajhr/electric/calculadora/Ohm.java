package com.ingenieriiajhr.electric.calculadora;

import android.annotation.SuppressLint;
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


public class Ohm extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Ohm() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    EditText edtCorriente,edtVoltaje,edtResistencia;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ohm, container, false);


        Button btnCalcular = root.findViewById(R.id.btnCalcular);
        edtCorriente = root.findViewById(R.id.edtCorriente);
        edtVoltaje = root.findViewById(R.id.edtVoltaje);
        edtResistencia = root.findViewById(R.id.edtResistencia);
        TextView txtResult = root.findViewById(R.id.txtResult);

        btnCalcular.setOnClickListener(view -> {
            Float corriente = 0f;
            Float voltaje = 0f;
            Float resistencia = 0f;
            
            if (oneConditionEmpty()){
                Toast.makeText(getActivity().getApplicationContext(), "Deja solo un valor vacio", Toast.LENGTH_SHORT).show();
            }else{
                if (TextUtils.isEmpty(edtCorriente.getText().toString())){
                    voltaje = Float.valueOf(edtVoltaje.getText().toString());
                    resistencia = Float.valueOf(edtResistencia.getText().toString());
                    txtResult.setText("Corriente: "+ calCorriente(resistencia, voltaje));
                }else{
                    if (TextUtils.isEmpty(edtResistencia.getText().toString())){
                        corriente = Float.valueOf(edtCorriente.getText().toString());
                        voltaje = Float.valueOf(edtVoltaje.getText().toString());
                        txtResult.setText("Resistencia: "+calResistencia(voltaje, corriente));
                    }else{
                        if (TextUtils.isEmpty(edtVoltaje.getText().toString())){
                            corriente = Float.valueOf(edtCorriente.getText().toString());
                            resistencia = Float.valueOf(edtResistencia.getText().toString());
                            txtResult.setText("Voltaje: "+ calVoltaje(corriente, resistencia));
                        }
                    }
                }  
            }

            
        });


        return root;
    }//V = I*R

    private boolean oneConditionEmpty() {
        boolean value = false;
        if (TextUtils.isEmpty(edtCorriente.getText().toString())&&TextUtils.isEmpty(edtVoltaje.getText().toString())){value = true;}
        if (TextUtils.isEmpty(edtCorriente.getText().toString())&&TextUtils.isEmpty(edtResistencia.getText().toString())){value = true;}
        if (TextUtils.isEmpty(edtVoltaje.getText().toString())&&TextUtils.isEmpty(edtResistencia.getText().toString())){value = true;}
        if (!TextUtils.isEmpty(edtVoltaje.getText().toString())&&!TextUtils.isEmpty(edtResistencia.getText().toString())&&!TextUtils.isEmpty(edtCorriente.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(), "Deja vacio el valor a calcular", Toast.LENGTH_SHORT).show();
            return false;
        }
        return value;
    }

    private Float calResistencia(Float voltaje,Float corriente){
        return voltaje/corriente;
    }
    private Float calCorriente(Float resistencia,Float voltaje){
        return voltaje/resistencia;
    }
    private Float calVoltaje(Float corriente,Float resistencia){
        return corriente*resistencia;
    }


}