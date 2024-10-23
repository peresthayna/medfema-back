package med.fema.api.especialidade;

import lombok.Getter;

@Getter
public enum Especialidade {
    CARDIOLOGISTA("Cardiologista"),
    CIRURGIAO("Cirurgião"),
    CLINICO_GERAL("Clínico Geral"),
    DERMATOLOGISTA("Dermatologista"),
    GASTROENTEROLOGISTA("Gastroenterologista"),
    GINECOLOGISTA("Ginecologista"),
    NEUROLOGISTA("Neurologista"),
    OFTALMOLOGISTA("Oftalmologista"),
    ORTOPEDISTA("Ortopedista"),
    PEDIATRA("Pediatra"),
    PNEUMOLOGISTA("Pneumologista"),
    PSIQUIATRA("Psiquiatra");

    private final String descricao;

    Especialidade(String descricao) {
        this.descricao = descricao;
    }

    public static Especialidade fromDescricao(String descricao) {
        for (Especialidade especialidade : Especialidade.values()) {
            if (especialidade.getDescricao().equalsIgnoreCase(descricao)) {
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida: " + descricao);
    }
}
