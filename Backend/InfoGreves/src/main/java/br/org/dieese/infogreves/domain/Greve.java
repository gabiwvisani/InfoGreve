package br.org.dieese.infogreves.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="greve")
public class Greve {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id_greve", nullable=false)
    private String idGreve;
    @Column(name="data_inicio")
    private LocalDate dataInicio;
    @Column(name="data_fim")
    private LocalDate dataFim;
    @Column(name="motivo", length = 255)
    private String motivo;
    private String categoria;
    @Column(name="sindicato_responsavel")
    private String sindicatoResponsavel;
    @Column(name="numero_trabalhadores")
    private int numeroTrabalhadores;
    private String local;
    @Column(name="greve_teve_sucesso")
    private Boolean greveTeveSucesso;
    @Column(name="resolucao_acordo")
    private String resolucaoAcordo;


    @Builder
    public Greve(LocalDate dataInicio, LocalDate dataFim, String motivo, String categoria,
                 String sindicatoResponsavel, int numeroTrabalhadores, String local,
                 Boolean greveTeveSucesso, String resolucaoAcordo) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.motivo = motivo;
        this.categoria = categoria;
        this.sindicatoResponsavel = sindicatoResponsavel;
        this.numeroTrabalhadores = numeroTrabalhadores;
        this.local = local;
        this.greveTeveSucesso = greveTeveSucesso;
        this.resolucaoAcordo = resolucaoAcordo;
    }





}

