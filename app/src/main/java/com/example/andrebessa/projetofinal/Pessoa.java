//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: André Bessa da Silva
//*****************************************************
package com.example.andrebessa.projetofinal;

// Classe que modela os atributos relevantes de uma pessoa para execução da aplicação.
public class Pessoa {

   // Definindo os atributos de uma pessoa.
   private String nome;
   private String cpf;
   private String idade;
   private String telefone;
   private String email;

   // Construtor que cria uma pessoa já inicializando seus atributos.
   public Pessoa(String nome,String cpf,String idade,String telefone,String email){
      this.nome=nome;
      this.cpf=cpf;
      this.idade=idade;
      this.telefone=telefone;
      this.email=email;
   }

   // Definindo os métodos "gets and setters" do objeto pessoa de modo público.
   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public String getIdade() {
      return idade;
   }

   public void setIdade(String idade) {
      this.idade = idade;
   }

   public String getTelefone() {
      return telefone;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
