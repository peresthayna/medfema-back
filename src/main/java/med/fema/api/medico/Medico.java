package med.fema.api.medico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.fema.api.endereco.Endereco;
import med.fema.api.especialidade.Especialidade;
import med.fema.api.medico.dto.MedicoDTO;

@Getter
@Setter
@Entity
@Table(name = "medico")
@NoArgsConstructor
public class Medico {
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

    @Column(name = "crm")
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    @Column(name = "ativo")
    private boolean ativo;

    public Medico(MedicoDTO medicoDTO) {
        this.id = medicoDTO.getId();
        this.nome = medicoDTO.getNome();
        this.telefone = medicoDTO.getTelefone();
        this.email = medicoDTO.getEmail();
        this.crm = medicoDTO.getCrm();
        this.especialidade = Especialidade.valueOf(medicoDTO.getEspecialidade());
        this.endereco = new Endereco(medicoDTO.getEndereco());
    }
}
