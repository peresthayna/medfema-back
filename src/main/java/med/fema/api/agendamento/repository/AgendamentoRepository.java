package med.fema.api.agendamento.repository;

import med.fema.api.agendamento.Agendamento;
import med.fema.api.medico.Medico;
import med.fema.api.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByPacienteAndDataHoraOrderByDataHoraAsc(Paciente paciente, LocalDateTime dataHora);
    List<Agendamento> findByMedicoAndDataHoraOrderByDataHoraAsc(Medico medico, LocalDateTime dataHora);
    List<Agendamento> findAllByMedicoIdOrderByDataHoraAsc(Long idMedico);
    List<Agendamento> findAllByPacienteIdOrderByDataHoraAsc(Long idPaciente);
    List<Agendamento> findAllByDataHoraAfterOrderByDataHoraAsc(LocalDateTime dataHora);
    List<Agendamento> findAllByPacienteNomeContainingOrMedicoNomeContainingOrderByDataHoraAsc(String nomePaciente, String nomeMedico);
}
