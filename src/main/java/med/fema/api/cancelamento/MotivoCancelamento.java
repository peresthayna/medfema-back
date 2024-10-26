package med.fema.api.cancelamento;

import lombok.Getter;

@Getter
public enum MotivoCancelamento {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Médico cancelou"),
    OUTROS("Outros");

    private final String descricao;

    MotivoCancelamento(String descricao) {
        this.descricao = descricao;
    }

    public static MotivoCancelamento fromDescricao(String descricao) {
        for (MotivoCancelamento motivo : MotivoCancelamento.values()) {
            if (motivo.getDescricao().equalsIgnoreCase(descricao)) {
                return motivo;
            }
        }
        throw new IllegalArgumentException("Motivo cancelamento inválido: " + descricao);
    }
}
