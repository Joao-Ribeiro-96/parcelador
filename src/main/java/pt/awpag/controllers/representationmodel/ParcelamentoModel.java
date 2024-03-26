package pt.awpag.controllers.representationmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.awpag.domain.models.Parcelamento;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelamentoModel {

    private Long id;
    private String nomeCliente;
    private String descricao;
    private OffsetDateTime dataCriacao;
    private String valor;
    private String parcelas;

    public ParcelamentoModel(Parcelamento parcelamento) {
        this.id = parcelamento.getId();
        this.nomeCliente = parcelamento.getCliente().getNome();
        this.descricao = parcelamento.getDescricao();
        this.dataCriacao = parcelamento.getDataCriacao();
        this.valor = parcelamento.getValor().toString();
        this.parcelas = parcelamento.getQuantidadeParcelas().toString();
    }
}
