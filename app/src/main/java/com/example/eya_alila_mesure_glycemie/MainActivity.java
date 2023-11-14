package com.example.eya_alila_mesure_glycemie;
//les imporatations nécessaires
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   // Déclaration des composants de l'interface utilisateur :
    private TextView tvAge,tvresult; //le TextView aui comporte l'age et le resultat
    private SeekBar sbAge;
    private RadioButton rbtOui;
    private EditText etValeur;  //l’EditText qui comporte la valeur mesurée.
    private Button btnConsulter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //main:qui va lencer l'application
        setContentView(R.layout.activity_main);  //définir le contenu de l'application
        init();
        //Action sur le SeekBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //pour afficher aux développeurs des informations utiles dans la console de débogage
                Log.i("information","onProgressChange"+progress);
                // Mettre à jour le TextView (tvAge) avec la nouvelle valeur de la SeekBar.
                tvAge.setText("Votre Age : "+progress);
            }
            @Override
            // Cette méthode est appelée lorsque l'utilisateur commence à déplacer le curseur de la SeekBar.
            public void onStartTrackingTouch(SeekBar seekBar) { }
            // Cette méthode est appelée lorsque l'utilisateur arrête de déplacer le curseur de la SeekBar.
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        //Action sur le bouton CONSULTER
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calcule du resultat par l'appel de la méthode calculer()
                calculer(view);
            }
        });
    }
    //L'implémentation de la méthode init()
    private void init()
    {
        tvAge=(TextView)findViewById(R.id.tvAge);
        sbAge=(SeekBar)findViewById(R.id.sbAge);
        rbtOui=(RadioButton)findViewById(R.id.rbtOui);
        etValeur=(EditText)findViewById(R.id.etValeur);
        btnConsulter=(Button)findViewById(R.id.btnConsulter);
        tvresult=(TextView)findViewById(R.id.tvresult);
    }
    @SuppressLint("SetTextI18n")
    public void calculer(View v)
    {
        int age=sbAge.getProgress();
        float ValeurMesure;
        String valeur=etValeur.getText().toString();
        boolean verifAge=false, verifvaleur=false;
        if(age!=0)
            verifAge=true;
        else
            Toast.makeText(MainActivity.this,"veuillez verifier votre Age",Toast.LENGTH_SHORT).show();
        if(!valeur.isEmpty())
            verifvaleur=true;
        else
            Toast.makeText(MainActivity.this,"veuillez verifier votre valeur",Toast.LENGTH_LONG).show();
        if(verifAge && verifvaleur)
        {
            ValeurMesure=Float.parseFloat(etValeur.getText().toString());
            if(rbtOui.isChecked())
                if(age>=13)
                    if(ValeurMesure<5.0)
                        tvresult.setText("Le niveau de glycémie est bas");
                    else if(ValeurMesure>=5.0 && ValeurMesure<=7.2)
                        tvresult.setText(("Le niveau de glycémie est normal"));
                    else
                        tvresult.setText("Le niveau de glycémie est élevé");
                else if(age>=6)
                    if(ValeurMesure<5.0)
                        tvresult.setText("Le niveau de glycémie est bas");
                    else if(ValeurMesure>=5.0 && ValeurMesure<=10.0)
                        tvresult.setText(("Le niveau de glycémie est normal"));
                    else
                        tvresult.setText("Le niveau de glycémie est élevé");
                else
                if(ValeurMesure<5.5)
                    tvresult.setText("Le niveau de glycémie est bas");
                else if(ValeurMesure>=5.5 && ValeurMesure<=10.0)
                    tvresult.setText(("Le niveau de glycémie est normal"));
                else
                    tvresult.setText("Le niveau de glycémie est élevé");
            else if(ValeurMesure<=10.5)
                tvresult.setText(("Le niveau de glycémie est normal"));
            else
                tvresult.setText("Le niveau de glycémie est élevé");
        }
    }

}
