package med.fema.api.paciente.service;

import med.fema.api.paciente.Paciente;
import med.fema.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getPacientes() {
        return this.pacienteRepository.findAllByOrderByNomeAsc();
    }

    public Paciente recuperarPorId(Long id) {
        return this.pacienteRepository.findById(id).orElse(null);
    }

    public Paciente salvar(Paciente paciente) {
        if(paciente.getId() != null) {
            if(this.pacienteRepository.findById(paciente.getId()).isPresent()) {
                return this.atualizar(paciente.getId(), paciente);
            } else {
                return this.pacienteRepository.save(paciente);
            }
        } else {
            return this.pacienteRepository.save(paciente);
        }
    }

    public Paciente atualizar(Long id, Paciente paciente) {
        paciente.setId(id);
        return this.pacienteRepository.save(paciente);
    }

    public void deletar(Long id) {
        Optional<Paciente> paciente = this.pacienteRepository.findById(id);
        if(paciente.isPresent()) {
            paciente.get().setAtivo(false);
            this.pacienteRepository.save(paciente.get());
        }
    }
}
