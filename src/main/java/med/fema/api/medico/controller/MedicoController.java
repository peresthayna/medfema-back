package med.fema.api.medico.controller;

import med.fema.api.medico.Medico;
import med.fema.api.medico.dto.MedicoDTO;
import med.fema.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping()
    public List<MedicoDTO> getMedicos() {
        return MedicoDTO.converterParaListDTO(this.medicoService.getMedicos());
    }

    @GetMapping("/busca/{busca}")
    public List<MedicoDTO> getMedicosBySearch(@PathVariable String busca) {
        return MedicoDTO.converterParaListDTO(this.medicoService.buscarMedicos(busca));
    }

    @GetMapping("/{id}")
    public MedicoDTO recuperarPorId(@PathVariable Long id) {
        return new MedicoDTO(this.medicoService.recuperarPorId(id));
    }

    @PostMapping()
    public void salvar(@RequestBody MedicoDTO medicoDTO) {
        this.medicoService.salvar(new Medico(medicoDTO));
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") Long id, @RequestBody MedicoDTO medicoDTO) {
        this.medicoService.atualizar(id, new Medico(medicoDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable("id") Long id) {
        this.medicoService.deletar(id);
    }

}
