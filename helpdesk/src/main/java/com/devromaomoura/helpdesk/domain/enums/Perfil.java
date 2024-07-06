package com.devromaomoura.helpdesk.domain.enums;

public enum Perfil {
    ADMIN(0, "ADMIN"), CLIENTE(1, "CLIENTE"), TECNICO(2, "TECNICO");

   private  Integer codigo;
   private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (Perfil perfil : Perfil.values()){
            if (codigo.equals(perfil.getCodigo())){
                return perfil;
            }
        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}
