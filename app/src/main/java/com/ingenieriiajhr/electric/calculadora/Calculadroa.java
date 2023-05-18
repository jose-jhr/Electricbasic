package com.ingenieriiajhr.electric.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.ingenieriiajhr.electric.R;
import com.ingenieriiajhr.electric.databinding.ActivityCalculadroaBinding;

public class Calculadroa extends AppCompatActivity implements View.OnClickListener {

    ActivityCalculadroaBinding binding;

    FragmentManager ft = getSupportFragmentManager();
    Fragment ohmFragment = new Ohm();
    Fragment currentNominal = new CurrentNominal();
    Fragment currentTriphase = new CurrentTriphase();

    Fragment calculatePotencia = new PotenciaConversion();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculadroaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.color_base));

        binding.btnCurrentNominal.setOnClickListener(this);
        binding.btnCurrentTri.setOnClickListener(this);
        binding.btnOhm.setOnClickListener(this);
        binding.btnPotencia.setOnClickListener(this);


    }

    /**
     *
     * @param view
     * all views click
     */
    @Override
    public void onClick(View view) {

        if (binding.btnOhm.equals(view)) {
            changeFragment(ohmFragment);
        } else if (binding.btnCurrentNominal.equals(view)) {
           changeFragment(currentNominal);
        } else if (binding.btnCurrentTri.equals(view)) {
            changeFragment(currentTriphase);
        }else if (binding.btnPotencia.equals(view)){
            changeFragment(calculatePotencia);
        }

    }

    /**
     * change fragment with param fragment using fragment container
     * @param fragment
     */
    private void changeFragment(Fragment fragment) {
        ft.beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
        }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}