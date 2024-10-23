package med.fema.api.paciente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.fema.api.endereco.Endereco;
import med.fema.api.paciente.dto.PacienteDTO;

@Getter
@Setter
@Entity
@Table(name = "paciente")
@NoArgsConstructor
public class Paciente {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Embedded
    private Endereco endereco;

    @Column(name = "ativo")
    private boolean ativo;

    public Paciente(PacienteDTO pacienteDTO) {
        this.id = pacienteDTO.getId();
        this.nome = pacienteDTO.getNome();
        this.telefone = pacienteDTO.getTelefone();
        this.email = pacienteDTO.getEmail();
        this.cpf = pacienteDTO.getCpf();
        this.endereco = new Endereco(pacienteDTO.getEndereco());
        this.ativo = true;
    }
}
