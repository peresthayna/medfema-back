package med.fema.api.medico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import med.fema.api.endereco.dto.EnderecoDTO;
import med.fema.api.generics.PageResponseDTO;
import med.fema.api.medico.Medico;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class MedicoDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    private String especialidade;
    private EnderecoDTO endereco;
    private boolean ativo;

    public MedicoDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.ativo = medico.isAtivo();
        this.especialidade = medico.getEspecialidade().name();
        this.endereco = new EnderecoDTO(medico.getEndereco());
    }

    public static List<MedicoDTO> converterParaListDTO(List<Medico> medicos) {
        return medicos.stream().map(medico -> new MedicoDTO(medico)).toList();
    }

    public static PageResponseDTO<MedicoDTO> converterParaPageResponseDTO(Page<Medico> page) {
        List<MedicoDTO> dto = converterParaListDTO(page.getContent());
        return new PageResponseDTO<MedicoDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
