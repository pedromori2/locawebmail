package br.com.locaweb.enums;

public enum CaixaEmailEnum {

    entrada("entrada"),
    saida("saida"),
    lixeira("lixeira"),
    arquivo("arquivo"),
    importante("importante");

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
