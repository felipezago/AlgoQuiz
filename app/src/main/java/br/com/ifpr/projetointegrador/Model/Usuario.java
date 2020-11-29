package br.com.ifpr.projetointegrador.Model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;

import br.com.ifpr.projetointegrador.Firebase.ConfiguracoesFirebase;

public class Usuario {

    private String id;
    private int pontos, pontosMedio, pontosAv, total;
    private String email;
    private String senha;
    private String nick;
    private boolean comprouMedio, comprouAv;
    private String fotoPerfil;
    Conquista conq;
    String sexo;

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Conquista getConq() {
        return conq;
    }

    public void setConq(Conquista conq) {
        this.conq = conq;
    }

    public int getTotal() {
        return pontos+pontosMedio+pontosAv;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public int getPontosMedio() {
        return pontosMedio;
    }

    public void setPontosMedio(int pontosMedio) {
        this.pontosMedio = pontosMedio;
    }

    public int getPontosAv() {
        return pontosAv;
    }

    public void setPontosAv(int pontosAv) {
        this.pontosAv = pontosAv;
    }

    public boolean isComprouMedio() {
        return comprouMedio;
    }

    public void setComprouMedio(boolean comprouMedio) {
        this.comprouMedio = comprouMedio;
    }

    public boolean isComprouAv() {
        return comprouAv;
    }

    public void setComprouAv(boolean comprouAv) {
        this.comprouAv = comprouAv;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void salvar(){

        DatabaseReference firebaseRef = ConfiguracoesFirebase.getFirebaseDatabase();
        DatabaseReference usuarios = firebaseRef.child("Usuario").child(getId());

        usuarios.setValue(this);

    }

    public Usuario(String id, int pontos, int pontosMedio, int pontosAv, String email, String senha, String nick, boolean comprouMedio, boolean comprouAv) {
        this.id = id;
        this.pontos = pontos;
        this.pontosMedio = pontosMedio;
        this.pontosAv = pontosAv;
        this.email = email;
        this.senha = senha;
        this.nick = nick;
        this.comprouMedio = comprouMedio;
        this.comprouAv = comprouAv;
    }

    public Usuario(){
        DatabaseReference ref = ConfiguracoesFirebase.getFirebaseDatabase()
                .child("Usuario");
        setId(ref.push().getKey());
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(pontos);
    }

}
