package med.fema.api.paciente.controller;

import med.fema.api.medico.dto.MedicoDTO;
import med.fema.api.paciente.Paciente;
import med.fema.api.paciente.dto.PacienteDTO;
import med.fema.api.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping()
    public List<PacienteDTO> getPacientes() {
        return PacienteDTO.converterParaListDTO(this.pacienteService.getPacientes());
    }

    @GetMapping("/{id}")
    public PacienteDTO recuperarPorId(@PathVariable Long id) {
        return new PacienteDTO(this.pacienteService.recuperarPorId(id));
    }

    @GetMapping("/busca/{busca}")
    public List<PacienteDTO> getPacientesBySearch(@PathVariable String busca) {
        return PacienteDTO.converterParaListDTO(this.pacienteService.buscarPacientes(busca));
    }

    @PostMapping()
    public void salvar(@RequestBody PacienteDTO pacienteDTO) {
        this.pacienteService.salvar(new Paciente(pacienteDTO));
    }

    @PutMapping
    public void atualizar(@PathVariable("id") Long id, @RequestBody PacienteDTO pacienteDTO) {
        this.pacienteService.atualizar(id, new Paciente(pacienteDTO));
    }

    @DeleteMapping
    public void deletar(@PathVariable("id") Long id) {
        this.pacienteService.deletar(id);
    }
}
