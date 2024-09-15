package med.fema.api.medico.controller;

import med.fema.api.generics.PageResponseDTO;
import med.fema.api.generics.PaginationRequest;
import med.fema.api.medico.Medico;
import med.fema.api.medico.dto.MedicoDTO;
import med.fema.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping()
    public PageResponseDTO<MedicoDTO> recuperarTodosPaginados(PaginationRequest request) {
        return MedicoDTO.converterParaPageResponseDTO(this.medicoService.recuperarTodosPaginados(request));
    }

    @GetMapping("/{id}")
    public MedicoDTO recuperarPorId(@PathVariable Long id) {
        return new MedicoDTO(this.medicoService.recuperarPorId(id));
    }

    @PostMapping()
    public void salvar(@RequestBody MedicoDTO medicoDTO) {
        this.medicoService.salvar(new Medico(medicoDTO));
    }

    @PutMapping
    public void atualizar(@PathVariable("id") Long id, @RequestBody MedicoDTO medicoDTO) {
        this.medicoService.atualizar(id, new Medico(medicoDTO));
    }

    @DeleteMapping
    public void deletar(@PathVariable("id") Long id) {
        this.medicoService.deletar(id);
    }

}
