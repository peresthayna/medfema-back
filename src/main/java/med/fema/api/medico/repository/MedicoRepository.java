package med.fema.api.medico.repository;

import med.fema.api.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m WHERE m NOT IN (SELECT a.medico FROM Agendamento a WHERE a.dataHora = :dataHora) AND m.ativo = true ORDER BY m.nome ASC")
    List<Medico> findMedicosDisponiveis(LocalDateTime dataHora);
    List<Medico> findAllByAtivoTrueOrderByNomeAsc();
    List<Medico> findAllByNomeContainingIgnoreCaseAndAtivoTrueOrderByNomeAsc(String nome);
}
