package med.fema.api.agendamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.fema.api.agendamento.dto.AgendamentoDTO;
import med.fema.api.cancelamento.MotivoCancelamento;
import med.fema.api.medico.Medico;
import med.fema.api.paciente.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class Agendamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataHora;

    @Column(name = "fim_consulta")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fimConsulta;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public Agendamento(AgendamentoDTO agendamentoDTO) {
        this.id = agendamentoDTO.getId();
        this.medico = new Medico(agendamentoDTO.getMedico());
        this.paciente = new Paciente(agendamentoDTO.getPaciente());
        this.dataHora = agendamentoDTO.getDataHora();
        this.fimConsulta = this.dataHora.plusHours(1);
        this.ativo = true;
        this.motivoCancelamento = null;
    }
}
