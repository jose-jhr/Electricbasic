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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentTriphase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTriphase extends Fragment {



    public CurrentTriphase() {
        // Required empty public constructor
    }

    EditText edtCorriente,edtPotencia,edtVoltaje,edtFactorPotencia;
    Button btnCalular;

    TextView txtResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_current_triphase, container, false);

        edtCorriente = root.findViewById(R.id.edtCorriente);
        edtPotencia = root.findViewById(R.id.edtPotencia);
        edtVoltaje = root.findViewById(R.id.edtVoltaje);
        edtFactorPotencia = root.findViewById(R.id.edtFactorPotencia);
        btnCalular = root.findViewById(R.id.btnCalcular);

        txtResult = root.findViewById(R.id.txtResult);

        btnCalular.setOnClickListener(view -> {
            Float corriente = 0f;
            Float potencia = 0f;
            Float voltaje = 0f;
            Float factorPotencia = 0f;

            if (oneConditionEmpty()){
                Toast.makeText(getActivity().getApplicationContext(), "Deja solo un valor vacio", Toast.LENGTH_SHORT).show();
            }else{
                if (TextUtils.isEmpty(edtCorriente.getText().toString())){
                    voltaje = Float.valueOf(edtVoltaje.getText().toString());
                    factorPotencia = Float.valueOf(edtFactorPotencia.getText().toString());
                    potencia = Float.valueOf(edtPotencia.getText().toString());
                    txtResult.setText("Corriente: "+ calCorriente(potencia,voltaje,factorPotencia));
                }else{
                    if (TextUtils.isEmpty(edtPotencia.getText().toString())){
                        corriente = Float.valueOf(edtCorriente.getText().toString());
                        voltaje = Float.valueOf(edtVoltaje.getText().toString());
                        factorPotencia = Float.valueOf(edtFactorPotencia.getText().toString());
                        txtResult.setText("Potencia: "+calPotencia(voltaje, factorPotencia,corriente));
                    }else{
                        if (TextUtils.isEmpty(edtVoltaje.getText().toString())){
                            corriente = Float.valueOf(edtCorriente.getText().toString());
                            factorPotencia = Float.valueOf(edtFactorPotencia.getText().toString());
                            potencia = Float.valueOf(edtPotencia.getText().toString());
                            txtResult.setText("Voltaje: "+ calVoltaje(corriente,factorPotencia,potencia));
                        }else{
                            if (TextUtils.isEmpty(edtFactorPotencia.getText().toString())){
                                corriente = Float.valueOf(edtCorriente.getText().toString());
                                potencia = Float.valueOf(edtPotencia.getText().toString());
                                voltaje = Float.valueOf(edtVoltaje.getText().toString());
                                txtResult.setText("Factor de potencia: "+ calFactPot(corriente,potencia,voltaje));
                            }
                        }
                    }
                }
            }
        });
        return root;

    }


    private float calFactPot(Float corriente, Float potencia, Float voltaje) {
        return (float) (potencia/(voltaje*corriente*Math.sqrt(3)));
    }

    private float calVoltaje(Float corriente, Float factorPotencia, Float potencia) {
        return (float) (potencia/(factorPotencia*corriente*Math.sqrt(3)));
    }

    private float calPotencia(Float voltaje, Float factorPotencia, Float corriente) {
        return (float) (corriente*voltaje*factorPotencia*Math.sqrt(3));
    }

    private float calCorriente(Float potencia, Float voltaje, Float factorPotencia){
        return (float) (potencia/(Math.sqrt(3)*voltaje*factorPotencia));
    }

    /**
     *
     * @return boolean value if one editText is empty
     */
    private boolean oneConditionEmpty() {
        boolean value = false;
        if (TextUtils.isEmpty(edtCorriente.getText().toString())&&TextUtils.isEmpty(edtPotencia.getText().toString())&&
                TextUtils.isEmpty(edtVoltaje.getText().toString())){value = true;}

        if (TextUtils.isEmpty(edtCorriente.getText().toString())&&TextUtils.isEmpty(edtPotencia.getText().toString())&&
                TextUtils.isEmpty(edtFactorPotencia.getText().toString())){value = true;}

        if (TextUtils.isEmpty(edtCorriente.getText().toString())&&TextUtils.isEmpty(edtVoltaje.getText().toString())&&
                TextUtils.isEmpty(edtFactorPotencia.getText().toString())){value = true;}

        if (TextUtils.isEmpty(edtPotencia.getText().toString())&&TextUtils.isEmpty(edtVoltaje.getText().toString())&&
                TextUtils.isEmpty(edtFactorPotencia.getText().toString())){value = true;}

        if (!TextUtils.isEmpty(edtCorriente.getText().toString())&&
                !TextUtils.isEmpty(edtPotencia.getText().toString())&&
                !TextUtils.isEmpty(edtVoltaje.getText().toString())&& !TextUtils.isEmpty(edtFactorPotencia.getText().toString())){
            Toast.makeText(getActivity().getApplicationContext(), "Deja vacio el valor a calcular", Toast.LENGTH_SHORT).show();
            return false;
        }
        return value;
    }


}