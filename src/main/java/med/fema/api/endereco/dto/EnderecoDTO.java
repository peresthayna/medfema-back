package med.fema.api.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.fema.api.endereco.Endereco;

@NoArgsConstructor
@Getter
public class EnderecoDTO {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    private String numero;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;

    public EnderecoDTO(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
    }
}
