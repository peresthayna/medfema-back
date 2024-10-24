package med.fema.api.paciente.repository;

import med.fema.api.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivoTrueOrderByNomeAsc();
    List<Paciente> findAllByNomeContainingIgnoreCaseAndAtivoTrueOrderByNomeAsc(String nome);
}
