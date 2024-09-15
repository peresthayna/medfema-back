package med.fema.api.paciente.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import med.fema.api.endereco.dto.EnderecoDTO;
import med.fema.api.generics.PageResponseDTO;
import med.fema.api.paciente.Paciente;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class PacienteDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private EnderecoDTO endereco;
    private boolean ativo;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.cpf = paciente.getCpf();
        this.ativo = paciente.isAtivo();
        this.endereco = new EnderecoDTO(paciente.getEndereco());
    }

    public static List<PacienteDTO> converterParaListDTO(List<Paciente> pacientes) {
        return pacientes.stream().map(paciente -> new PacienteDTO(paciente)).toList();
    }

    public static PageResponseDTO<PacienteDTO> converterParaPageResponseDTO(Page<Paciente> page) {
        List<PacienteDTO> dto = converterParaListDTO(page.getContent());
        return new PageResponseDTO<PacienteDTO>(dto, page.hasNext(), page.getTotalElements(), page.getTotalPages());
    }
}
