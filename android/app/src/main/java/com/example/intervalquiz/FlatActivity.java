package com.example.intervalquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FlatActivity extends AppCompatActivity {


    // static State Atributes
    private static String NOTA1 = "C";
    private static String NOTA2 = "C";
    //private static final String NOTATION = "#";



    // Elements
    private NumberPicker intervalNumberPicker;
    private NumberPicker intervalNumberPicker_C;


    private Button flatSharpButton;

    // Switch for specifying which from which NOTE to calculate interval
    private Switch changeNOTE1Switch;

    // Boolean for Syncing
    private boolean isSyncing = false;
    private boolean isSyncing2 = false;

    // Spinner 1 left
    private Spinner nota1_spinner;
    private SkalaFlat cdur;
    private ArrayAdapter<CharSequence> adapter_c;

    // Spinner 2 right
    private Spinner nota2_spinner;
    private SkalaFlat skala;
    private ArrayAdapter<CharSequence> adapter_skala;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Button flatSharpButton = findViewById(R.id.sharpFlatButton);
        //flatSharpButton.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));

        // Start

        // Init Skale
        try {
            String NOTA_INIT = "C";
            System.out.println("56");
            cdur = new SkalaFlat(NOTA_INIT);
            cdur.printNote_lista();
            System.out.println("59");
            skala = new SkalaFlat(NOTA_INIT);
            skala.printNote_lista();
        } catch (interval.SkalaNePostojiException e) {
            Toast.makeText(this,"Ne postoji Skala s zeljenom notom"+e,Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }

        // findViewById
        flatSharpButton = findViewById(R.id.sharpFlatButton);
        nota1_spinner = findViewById(R.id.nota1);
        nota2_spinner = findViewById(R.id.nota2);
        intervalNumberPicker = findViewById(R.id.interval);
        intervalNumberPicker_C = findViewById(R.id.interval_od_C);
        changeNOTE1Switch = findViewById(R.id.switch1);


        // Init Number picker for Intervals
        intervalNumberPicker.setMinValue(0);
        intervalNumberPicker.setMaxValue(11);
        intervalNumberPicker.setDisplayedValues(SkalaFlat.get_intervali_lista());
        intervalNumberPicker.setEnabled(true);

        // Init Number picker for C
        intervalNumberPicker_C.setMinValue(0);
        intervalNumberPicker_C.setMaxValue(11);
        intervalNumberPicker_C.setDisplayedValues(SkalaFlat.notes.second);//cdur.getNote_lista_global());
        intervalNumberPicker_C.setEnabled(false);


        // Set C adapter
        try {
            adapter_c = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cdur.sortSkala(NOTA1));
            adapter_c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            nota1_spinner.setAdapter(adapter_c);
        } catch (interval.SkalaNePostojiException e) {
            Toast.makeText(this,"Ne postoji Skala s zeljenom notom",Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }


        // Set skala adapter
        try {
            adapter_skala = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skala.sortSkala(NOTA2));//SkalaFlat.note_lista_global);
            adapter_skala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            nota2_spinner.setAdapter(adapter_skala);
        } catch (interval.SkalaNePostojiException e) {
            Toast.makeText(this,"Ne postoji Skala s zeljenom notom",Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }

        // Logic for changing notation
        flatSharpButton.setOnClickListener(view -> {
            System.out.println("flatSharpButton.setOnClickListener");
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(0, 0);

        });

        // Logic for switch
        changeNOTE1Switch.setOnCheckedChangeListener(((compoundButton, isChecked) -> {
            intervalNumberPicker.setEnabled(isChecked);
        }));

        // Logic for selecting Note for C
        nota1_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("nota1_spinner.setOnItemSelectedListener");
                if (!isSyncing) {
                    isSyncing = true;
                    NOTA1 = adapterView.getItemAtPosition(i).toString();
                    if(changeNOTE1Switch.isChecked()) {
                        int interval = intervalNumberPicker.getValue();
                        try {
                            adapter_skala = new ArrayAdapter<>(FlatActivity.this, android.R.layout.simple_spinner_item, cdur.sortSkala(NOTA1));//Skala.note_lista_global);
                        } catch (interval.SkalaNePostojiException e) {
                            Toast.makeText(FlatActivity.this, "Ne postoji Skala s zeljenom notom" + e, Toast.LENGTH_LONG).show();
                            throw new RuntimeException(e);
                        }
                        adapter_skala.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        nota2_spinner.setAdapter(adapter_skala);

                        nota2_spinner.setSelection(interval);

                    }

                    calculateAndSetIntervalNumberPicker();
                    isSyncing = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Logic for selecting Note for skala
        nota2_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("nota2_spinner.setOnItemSelectedListener");
                if (!isSyncing2) {
                    if (!isSyncing) {
                        isSyncing = true;
                        NOTA2 = adapterView.getItemAtPosition(i).toString();

                        calculateAndSetIntervalNumberPicker();

                        isSyncing = false;
                    }
                }
                isSyncing2=false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Logic for selecting Interval
        intervalNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            System.out.println("intervalNumberPicker.setOnValueChangedListener");
            if (!isSyncing) {
                isSyncing = true;
                intervalNumberPicker_C.setValue(i1);
                calculateAndSetNote2(i1);
                isSyncing = false;
            }

        });

        // Logic for C representation of Interval
        intervalNumberPicker_C.setOnValueChangedListener((numberPicker, i, i1) -> {
            System.out.println("intervalNumberPicker_C.setOnValueChangedListener");
            if (!isSyncing) {
                isSyncing = true;
                intervalNumberPicker.setValue(i1);


                isSyncing = false;
            }
        });
    }

    // Function for selection skala spinner
    private void calculateAndSetNote2(int interval) {
        System.out.println("calculateAndSetNote2()");
        try {
            skala = new SkalaFlat(NOTA1);
            NOTA2 = skala.findNotaFromInterval(interval);
            isSyncing2 = true;
            nota2_spinner.setSelection(skala.findIntervalAsInt(NOTA2));


        } catch (interval.SkalaNePostojiException e) {
            Toast.makeText(this,"Ne postoji Skala s zeljenom notom"+e,Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }

    // Function for selecting Interval
    private void calculateAndSetIntervalNumberPicker() {
        System.out.println("calculateAndSetIntervalNumberPicker()");
        try {
            skala = new SkalaFlat(NOTA1);
            int INTERVAL = skala.findIntervalAsInt(NOTA2);


            intervalNumberPicker.setValue(INTERVAL);
            intervalNumberPicker_C.setValue(INTERVAL);



        } catch (interval.SkalaNePostojiException e) {
            Toast.makeText(this,"Ne postoji Skala s zeljenom notom"+e,Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }
}