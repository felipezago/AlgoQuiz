package br.com.ifpr.projetointegrador.Model;

public class Conquista {

    boolean pegouPrimeiroLugar, completouIntro, completouMedio, completou, maisdevinte;

    public boolean isCompletouIntro() {
        return completouIntro;
    }

    public void setCompletouIntro(boolean completouIntro) {
        this.completouIntro = completouIntro;
    }

    public boolean isCompletouMedio() {
        return completouMedio;
    }

    public void setCompletouMedio(boolean completouMedio) {
        this.completouMedio = completouMedio;
    }

    public boolean isCompletou() {
        return completou;
    }

    public void setCompletou(boolean completou) {
        this.completou = completou;
    }

    public boolean isMaisdevinte() {
        return maisdevinte;
    }

    public void setMaisdevinte(boolean maisdevinte) {
        this.maisdevinte = maisdevinte;
    }

    public boolean isPegouPrimeiroLugar() {
        return pegouPrimeiroLugar;
    }

    public void setPegouPrimeiroLugar(boolean pegouPrimeiroLugar) {
        this.pegouPrimeiroLugar = pegouPrimeiroLugar;
    }
}
