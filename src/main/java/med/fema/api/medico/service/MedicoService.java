package med.fema.api.medico.service;

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

    public List<Medico> getMedicos() {
        return this.medicoRepository.findAllByOrderByNomeAsc();
    }

    public Medico recuperarPorId(Long id) {
        return this.medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> buscarMedicos(String busca) {
        return this.medicoRepository.findAllByNomeContainingIgnoreCase(busca);
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
            medico.get().setAtivo(false);
            this.medicoRepository.save(medico.get());
        }
    }
}
