package med.fema.api.agendamento.controller;

import med.fema.api.agendamento.Agendamento;
import med.fema.api.agendamento.dto.AgendamentoDTO;
import med.fema.api.agendamento.service.AgendamentoService;
import med.fema.api.cancelamento.MotivoCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping()
    public List<AgendamentoDTO> findAll() {
        return AgendamentoDTO.converterParaListDTO(agendamentoService.findAll());
    }

    @GetMapping("/paciente/{id}")
    public List<AgendamentoDTO> findAllByPaciente(@PathVariable Long id) {
        return AgendamentoDTO.converterParaListDTO(agendamentoService.findAllByPacienteId(id));
    }

    @GetMapping("/medico/{id}")
    public List<AgendamentoDTO> findAllByMedico(@PathVariable Long id) {
        return AgendamentoDTO.converterParaListDTO(agendamentoService.findAllByMedicoId(id));
    }

    @GetMapping("/{id}")
    public AgendamentoDTO findById(@PathVariable Long id) {
        return new AgendamentoDTO(agendamentoService.findById(id));
    }

    @PostMapping
    public void salvar(@RequestBody AgendamentoDTO agendamento) throws Exception {
        agendamentoService.criarAgendamento(new Agendamento(agendamento));
    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelarAgendamento(@PathVariable Long id, @RequestParam MotivoCancelamento motivo) {
        try {
            agendamentoService.cancelarConsulta(id, motivo);
            return ResponseEntity.ok("Consulta cancelada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
