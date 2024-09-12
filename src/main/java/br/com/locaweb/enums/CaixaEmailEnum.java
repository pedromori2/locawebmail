package br.com.locaweb.enums;

public enum CaixaEmailEnum {

    entrada("entrada"),
    saida("saida");

    private String descricao;

    CaixaEmailEnum(String descricao) {

        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }
}
