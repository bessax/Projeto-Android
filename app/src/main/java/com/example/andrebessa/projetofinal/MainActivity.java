//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: André Bessa da Silva
//*****************************************************
package com.example.andrebessa.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btcadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associando botão do Design com o códio java.
        btcadastrar = (Button) findViewById(R.id.btCadastrar);
        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChamarSecondActivity();
            }
        });
    }

    void ChamarSecondActivity(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
