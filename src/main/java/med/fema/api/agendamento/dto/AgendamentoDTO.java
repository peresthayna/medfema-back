package med.fema.api.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.fema.api.agendamento.Agendamento;
import med.fema.api.cancelamento.MotivoCancelamento;
import med.fema.api.generics.PageResponseDTO;
import med.fema.api.medico.dto.MedicoDTO;
import med.fema.api.paciente.dto.PacienteDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
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
    private LocalDateTime dataHora;
    private LocalDateTime fimConsulta;
    private boolean ativo;
    private MotivoCancelamento motivoCancelamento;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.medico = new MedicoDTO(agendamento.getMedico());
        this.paciente = new PacienteDTO(agendamento.getPaciente());
        this.dataHora = agendamento.getDataHora();
        this.fimConsulta = this.dataHora.plusHours(1);
        this.ativo = true;
        this.motivoCancelamento = null;
    }

    public static List<AgendamentoDTO> converterParaListDTO(List<Agendamento> agendamentos) {
        return agendamentos.stream().map(agendamento -> new AgendamentoDTO(agendamento)).toList();
    }

    public static PageResponseDTO<AgendamentoDTO> converterParaPageResponseDTO(Page<Agendamento> page) {
        List<AgendamentoDTO> dto = converterParaListDTO(page.getContent());
        return new PageResponseDTO<AgendamentoDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
