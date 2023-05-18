package com.ingenieriiajhr.electric.instalacioneselectricas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import com.ingenieriiajhr.animation.RotateView;
import com.ingenieriiajhr.data.DataInstalacionElectrica;
import com.ingenieriiajhr.electric.R;
import com.ingenieriiajhr.electric.databinding.ActivityMainBinding;
import com.ingenieriiajhr.gridview.OptAdapter;
import com.rajat.pdfviewer.PdfViewerActivity;

public class InstalacionesElectrica extends AppCompatActivity  {


    ActivityMainBinding binding;

    DataInstalacionElectrica dataInsElectr = new DataInstalacionElectrica();

    RotateView rotateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.color_base));

        animationInitRotate();

        binding.gridViewSelect.setAdapter(new OptAdapter(
                this,dataInsElectr.textOpt(),dataInsElectr.numberOpt()));

        binding.gridViewSelect.setExpanded(true);

        clickGridView();

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLogo();
            }
        });



    }

    /**
     * init view img logo rotate
     */
    private void animationInitRotate() {
        rotateView = new RotateView(this,binding.imgLogo);
        rotateLogo();
    }

    /**
     * rotate image view
     */
    private void rotateLogo() {
        rotateView.rotateView();
    }

    /**
     * detect click element @gridviewselect
     */
    private void clickGridView() {
        binding.gridViewSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPdfClickItem(i);
            }
        });

    }

    /**
     * open pdf witch position
     * @param i
     */
    private void openPdfClickItem(int i) {
        startActivity(PdfViewerActivity.Companion.launchPdfFromPath(
                getApplicationContext(),
                dataInsElectr.namePdf().get(i),
                dataInsElectr.textOpt().get(i),
                "assets",
                false,
                true
        ));
    }


}