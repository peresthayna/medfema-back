package med.fema.api.medico.service;

import med.fema.api.agendamento.repository.AgendamentoRepository;
import med.fema.api.especialidade.Especialidade;
import med.fema.api.medico.Medico;
import med.fema.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Medico> getMedicos() {
        return this.medicoRepository.findAllByAtivoTrueOrderByNomeAsc();
    }

    public Medico recuperarPorId(Long id) {
        return this.medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> buscarMedicos(String busca) {
        return this.medicoRepository.findAllByNomeContainingIgnoreCaseAndAtivoTrueOrderByNomeAsc(busca);
    }

    public Medico salvar(Medico medico) {
        if(medico.getId() != null) {
            if(this.medicoRepository.findById(medico.getId()).isPresent()) {
                return this.atualizar(medico.getId(), medico);
            } else {
                return this.medicoRepository.save(medico);
            }
        } else {
            return this.medicoRepository.save(medico);
        }
    }

    public Medico atualizar(Long id, Medico medico) {
        medico.setId(id);
        return this.medicoRepository.save(medico);
    }

    public void deletar(Long id) {
        Optional<Medico> medico = this.medicoRepository.findById(id);
        if(medico.isPresent()) {
            if(this.agendamentoRepository.findAllByMedicoIdOrderByDataHoraAsc(id).isEmpty()) {
                medico.get().setAtivo(false);
                this.medicoRepository.save(medico.get());
            } else {
                throw new RuntimeException("Certifique-se que não há consultas agendadas, caso tenha, a desativação não poderá ser concluída.");
            }
        }
    }
}
