package com.Controlnote.controle.de.nota.models;

import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "tb_phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aparelho;
    private String defeito;
    private double valorCobrado;
    private boolean retirouSemNota;
    private boolean semBateria;
    private boolean semChip;
    private boolean semTampaFundo;
    private boolean arranhao;
    private boolean telaTrincada;
    private boolean desligado;
    private boolean DisplayVazado;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    public Long getId() {
        return id;
    }

}
