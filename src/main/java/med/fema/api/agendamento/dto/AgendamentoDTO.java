package med.fema.api.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.fema.api.agendamento.Agendamento;
import med.fema.api.cancelamento.MotivoCancelamento;
import med.fema.api.medico.dto.MedicoDTO;
import med.fema.api.paciente.dto.PacienteDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private MedicoDTO medico;
    private PacienteDTO paciente;
    private String dataHora;
    private String fimConsulta;
    private boolean ativo;
    private MotivoCancelamento motivoCancelamento;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.medico = new MedicoDTO(agendamento.getMedico());
        this.paciente = new PacienteDTO(agendamento.getPaciente());
        this.dataHora = agendamento.getDataHora().toString();
        this.fimConsulta = agendamento.getFimConsulta().toString();
        this.ativo = true;
        this.motivoCancelamento = null;
    }

    public static List<AgendamentoDTO> converterParaListDTO(List<Agendamento> agendamentos) {
        return agendamentos.stream().map(agendamento -> new AgendamentoDTO(agendamento)).toList();
    }
}
