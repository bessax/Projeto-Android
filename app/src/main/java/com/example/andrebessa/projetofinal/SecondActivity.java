//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: André Bessa da Silva
//*****************************************************
package com.example.andrebessa.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    // Definindo o objeto de interação com a base de dados.
    private DBHelperDAO dhDAO;

    // Definindo os controles (Widgets) da aplicação;
    Button btvoltar;
    Button btinserir;
    Button btlistar;

    EditText etnome;
    EditText etcpf;
    EditText etidade;
    EditText ettelefone;
    EditText etemail;

    long idade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Inicializando o objeto de interação com a base de dados.
        this.dhDAO = new DBHelperDAO(this);

        // Relacionando os objetos da interface com o código JAVA.
        btvoltar = (Button) findViewById(R.id.btVoltar);
        btinserir = (Button) findViewById(R.id.btInserir);
        btlistar = (Button) findViewById(R.id.btListar);
        // Relacionando os objetos da interface com o código JAVA.
        etnome =(EditText) findViewById(R.id.etNome);
        etcpf =(EditText) findViewById(R.id.etCPF);
        etidade =(EditText) findViewById(R.id.etIdade);
        ettelefone =(EditText) findViewById(R.id.etTelefone);
        etemail = (EditText) findViewById(R.id.etEmail);

        btinserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validando se os campos estão preenchidos antes da insersão das informações na base de dados.
                if (etnome.getText().length()>0 && etcpf.getText().length()>0 && etidade.getText().length()>0 && ettelefone.getText().length()>0 &&
                        etemail.getText().length()>0)
                {
                    dhDAO.insert(etnome.getText().toString(),etcpf.getText().toString(),etidade.getText().toString(),ettelefone.getText().toString(),etemail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle(">>>Sucesso<<<");
                    adb.setMessage("Cadastro Realizado com Sucesso!");
                    adb.show();
                    limpaCampos();

                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("(((Erro)))");
                    adb.setMessage("Todos os campos devem ser preenchidos.");
                    adb.show();
                    limpaCampos();

                }
            }
        });

        // Definindo o método listar do botão btListar;
        btlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Pessoa> pessoas = dhDAO.queryGetAll();
                if(pessoas==null)
                {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Não há contatos cadastrados.");
                    adb.show();
                    return;
                }
                for(int i=0; i<pessoas.size(); i++){
                    Pessoa pessoa = pessoas.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle(">>> Registro Pessoa Física ->"+i);
                    adb.setMessage("Nome: "+pessoa.getNome()+"\n"+"CPF: "+pessoa.getCpf()+"\n"+"Idade: "+pessoa.getIdade()+"\n"+"Telefone: "+pessoa.getTelefone()+"\n"+ "E-mail: "+ pessoa.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Aperta OK, fecha a janela e vai para o próximo.
                            dialogInterface.dismiss();
                        }
                    });
                    adb.show();

                }


            }
        });

        // Definindo o evento do botão voltar.
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Executando o método para voltar ao MainActivity
                ChamarMaindActivity();
            }
        });

    }

    /**
     * Funcção definida para realizar a limpeza dos campos da tela
     * do programa.
     * */
    void limpaCampos() {
        etnome.setText("");
        etcpf.setText("");
        etemail.setText("");
        etidade.setText("");
        ettelefone.setText("");
    }

    /**
    * Funcção definida para realizar a navegação entre as telas
    * do programa.
    * */
    void ChamarMaindActivity(){
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
