package med.fema.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.fema.api.endereco.dto.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.getLogradouro();
        this.cep = enderecoDTO.getCep();
        if(!enderecoDTO.getNumero().isEmpty()){
            this.numero = enderecoDTO.getNumero();
        }
        if(!enderecoDTO.getComplemento().isEmpty()){
            this.complemento = enderecoDTO.getComplemento();
        }
        this.cidade = enderecoDTO.getCidade();
        this.uf = enderecoDTO.getUf();
    }
}
