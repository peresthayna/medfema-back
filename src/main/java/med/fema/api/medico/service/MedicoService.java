package med.fema.api.medico.service;

import med.fema.api.generics.PaginationRequest;
import med.fema.api.medico.Medico;
import med.fema.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Page<Medico> recuperarTodosPaginados(PaginationRequest request) {
        return this.medicoRepository.findAll(request.toPageable());
    }

    public Medico recuperarPorId(Long id) {
        return this.medicoRepository.findById(id).orElse(null);
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
