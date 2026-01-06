package br.com.alura.challenge_literalura.model;

import java.util.Locale;

public enum Idioma {

    PORTUGUES("pt", "Português"),
    INGLES("en", "Inglês"),
    ESPANHOL("es", "Espanhol"),
    FRANCES("fr", "Francês");

    // código retornado pela API
    private final String codigoApi;

    // descrição legível
    private final String descricao;

    Idioma(String codigoApi, String descricao) {
        this.codigoApi = codigoApi;
        this.descricao = descricao;
    }

    public String getCodigoApi() {
        return codigoApi;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte código da API (pt, en, es, fr) para ENUM
     */
    public static Idioma fromCodigoApi(String codigo) {
        if (codigo == null) {
            return null;
        }

        String normalizado = codigo.toLowerCase(Locale.ROOT);

        for (Idioma idioma : values()) {
            if (idioma.codigoApi.equals(normalizado)) {
                return idioma;
            }
        }

        throw new IllegalArgumentException("Idioma inválido (API): " + codigo);
    }

    /**
     * Converte descrição legível para ENUM
     * (caso ainda seja necessário em algum ponto)
     */
    public static Idioma fromDescricao(String descricao) {
        if (descricao == null) {
            return null;
        }

        for (Idioma idioma : values()) {
            if (idioma.descricao.equalsIgnoreCase(descricao)) {
                return idioma;
            }
        }

        throw new IllegalArgumentException("Idioma inválido (descrição): " + descricao);
    }
}
