package br.com.ifpr.projetointegrador.Questoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Questao implements Serializable {
    private String pergunta;
    private List<String> respostas= new ArrayList<>();
    private int respostaCerta;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public int getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(int respostaCerta) {
        this.respostaCerta = respostaCerta;
    }
    
    public Questao(String pergunta, int respostaCerta, String... respostas){
        this.pergunta = pergunta;
        this.respostas.add(respostas[0]);
        this.respostas.add(respostas[1]);
        this.respostas.add(respostas[2]);
        this.respostas.add(respostas[3]);
        this.respostaCerta= respostaCerta;
    }
}
