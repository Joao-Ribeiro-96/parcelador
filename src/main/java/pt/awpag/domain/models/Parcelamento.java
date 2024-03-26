package pt.awpag.domain.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import pt.awpag.domain.validation.ValidationGroups;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne
    @NotNull
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    private Cliente cliente;

    @NotNull
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Max(12)
    private Integer quantidadeParcelas;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

}
