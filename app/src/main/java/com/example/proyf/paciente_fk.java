package com.example.proyf;

public class paciente_fk {

    private String id_diagnostico_fk;

    public paciente_fk(String id_diagnostico_fk) {
        this.id_diagnostico_fk = id_diagnostico_fk;
    }

    public paciente_fk() {

    }

    public String getId_diagnostico_fk() {
        return id_diagnostico_fk;
    }

    public void setId_diagnostico_fk(String id_diagnostico_fk) {
        this.id_diagnostico_fk = id_diagnostico_fk;
    }
}
