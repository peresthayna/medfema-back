package med.fema.api.agendamento.controller;

import med.fema.api.agendamento.Agendamento;
import med.fema.api.agendamento.dto.AgendamentoDTO;
import med.fema.api.agendamento.service.AgendamentoService;
import med.fema.api.cancelamento.MotivoCancelamento;
import med.fema.api.generics.PageResponseDTO;
import med.fema.api.generics.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping()
    public PageResponseDTO<AgendamentoDTO> findAll(PaginationRequest request) {
        return AgendamentoDTO.converterParaPageResponseDTO(agendamentoService.findAll(request));
    }

    @GetMapping("/paciente/{id}")
    public PageResponseDTO<AgendamentoDTO> findAllByPaciente(PaginationRequest request, @PathVariable Long id) {
        return AgendamentoDTO.converterParaPageResponseDTO(agendamentoService.findAllByPacienteId(request, id));
    }

    @GetMapping("/medico/{id}")
    public PageResponseDTO<AgendamentoDTO> findAllByMedico(PaginationRequest request, @PathVariable Long id) {
        return AgendamentoDTO.converterParaPageResponseDTO(agendamentoService.findAllByMedicoId(request, id));
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
