//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: André Bessa da Silva
//*****************************************************
package com.example.andrebessa.projetofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DBHelperDAO {

    private static final String DATABASE_NAME = "pessoafisica.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoa";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;

    private static final String INSERT = "insert into " + TABLE_NAME + " (nome,cpf,idade,telefone,email) values (?,?,?,?,?)";

    // Constructor criado para configurar os objetos de conexão e execução na base de dados.
    public DBHelperDAO(Context context){

        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);

    }

    // Método criado para realizar as inserções de informações na base de dados;
    public long insert(String nome,String cpf,String idade,String telefone, String email){

        this.insertStmt.bindString(1,nome);
        this.insertStmt.bindString(2,cpf);
        this.insertStmt.bindString(3,idade);
        this.insertStmt.bindString(4,telefone);
        this.insertStmt.bindString(5,email);

        return this.insertStmt.executeInsert();
    }

    // Método criado para as necessidades de deletar uma tabela;
    public void deleteAll(){

        this.db.delete(TABLE_NAME,null,null);

    }
    // Método criado para recuperar todas as pessoas cadastradas na base de dados e retornar em uma lista.
    public List<Pessoa> queryGetAll(){
        List<Pessoa> list = new ArrayList<Pessoa>();
        try{
            Cursor cursor = this.db.query(TABLE_NAME,new String[]{"nome","cpf","idade","telefone","email"},null,null,null,null,null,null);
            int nregistros = cursor.getCount();
            if(nregistros!=0){
                // vai para o primeiro registro.
                cursor.moveToFirst();
                do {
                    Pessoa pessoa = new Pessoa(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                    list.add(pessoa);
                }while(cursor.moveToNext());
                if(cursor!= null && !cursor.isClosed()){
                    cursor.close();
                    return list;
                }
            }
            else{
                return  null;
            }

        }
        catch (Exception err){
            return null;
        }

        return null;
    }

    // Inner Class responsavel pela criação do banco de dados na inicialização do aplicativo.
    private static class OpenHelper extends SQLiteOpenHelper {
        //Constructor para configuração do banco.
        OpenHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }

        //Método OnCreate para a criação da tabela no banco de dados da aplicação.
        public void onCreate(SQLiteDatabase db){
            String sql ="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, "+
                    "cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
            db.execSQL("DROP TABLE IF EXIST "+ TABLE_NAME);
            onCreate(db);

        }

    }

}
