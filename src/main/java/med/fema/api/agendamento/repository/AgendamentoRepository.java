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
    List<Agendamento> findByPacienteAndDataHoraAndAtivoTrueOrderByDataHoraAsc(Paciente paciente, LocalDateTime dataHora);
    List<Agendamento> findByMedicoAndDataHoraAndAtivoTrueOrderByDataHoraAsc(Medico medico, LocalDateTime dataHora);
    List<Agendamento> findAllByMedicoIdAndAtivoTrueOrderByDataHoraAsc(Long idMedico);
    List<Agendamento> findAllByPacienteIdAndAtivoTrueOrderByDataHoraAsc(Long idPaciente);
    List<Agendamento> findAllByDataHoraAfterAndAtivoTrueOrderByDataHoraAsc(LocalDateTime dataHora);
    List<Agendamento> findAllByPacienteNomeContainingOrMedicoNomeContainingAndAtivoTrueOrderByDataHoraAsc(String nomePaciente, String nomeMedico);
}
