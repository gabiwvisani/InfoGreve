package br.org.dieese.infogreves.service;

import br.org.dieese.infogreves.domain.Greve;
import br.org.dieese.infogreves.payloads.request.FinalGreveRequest;
import br.org.dieese.infogreves.payloads.request.GreveRequest;
import br.org.dieese.infogreves.repository.GreveRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class GreveService {
    private final ModelMapper modelMapper;
    private final GreveRepository greveRepository;

    public GreveService(GreveRepository greveRepository,ModelMapper modelMapper) {
        this.greveRepository = greveRepository;
        this.modelMapper= modelMapper;
    }
    public String cadastrarGreve(GreveRequest greveRequest) {
        Greve greve = modelMapper.map(greveRequest, Greve.class);
        validaData(greve.getDataInicio(), greve.getDataFim());
        Greve savedGreve = greveRepository.save(greve);
        return savedGreve.getIdGreve();
    }
    public List<Greve> buscarTodasGreves(){
        List<Greve> listaComTodas = greveRepository.findAll();
        return listaComTodas;
    }
    public Greve buscarGrevePorId(String id) {
        Optional<Greve> optionalGreve = greveRepository.findById(id);
        if (optionalGreve.isPresent()) {
            Greve grevePresente=optionalGreve.get();
            return grevePresente;
        } else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de greve com esse id.");
        }
    }


    public Greve alterarDadosFinalGreve(FinalGreveRequest request, String id) {
        Optional<Greve> optionalGreve = greveRepository.findById(id);
        if (optionalGreve.isPresent()) {
            Greve grevePresente=optionalGreve.get();
            validaData(grevePresente.getDataInicio(), request.getDataFim());
                grevePresente.setGreveTeveSucesso(request.getGreveTeveSucesso());
                grevePresente.setDataFim(request.getDataFim());
                grevePresente.setResolucaoAcordo(request.getResolucaoAcordo());
                Greve savedGreve = greveRepository.save(grevePresente);
                return savedGreve;
        } else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de greve com esse id.");
        }
    }

    public Greve alterarDadosGreve(GreveRequest greveRequest, String id) {
        Optional<Greve> optionalGreve = greveRepository.findById(id);
        if (optionalGreve.isPresent()) {
            Greve greve = optionalGreve.get();
            validaData(greveRequest.getDataInicio(), greveRequest.getDataFim());
                greve.setResolucaoAcordo(greveRequest.getResolucaoAcordo());
                greve.setGreveTeveSucesso(greveRequest.getGreveTeveSucesso());
                greve.setLocal(greveRequest.getLocal());
                greve.setDataFim(greveRequest.getDataFim());
                greve.setCategoria(greveRequest.getCategoria());
                greve.setMotivo(greveRequest.getMotivo());
                greve.setDataInicio(greveRequest.getDataInicio());
                greve.setNumeroTrabalhadores(greveRequest.getNumeroTrabalhadores());
                greve.setSindicatoResponsavel(greveRequest.getSindicatoResponsavel());
                greve = greveRepository.save(greve);
                return greve;
        } else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de greve com esse id.");
        }
    }
    public void validaData(LocalDate dataInicio, LocalDate dataFim) {
        LocalDate hoje = LocalDate.now();
        if (dataInicio == null) dataInicio = hoje;
        if (dataFim == null) dataFim = dataInicio;

        if (dataInicio.isBefore(hoje) || dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("Data de início de greve não pode ser posterior a hoje e data de final de greve não pode ser anterior a data de inicio.");
        }
    }

    public void deletarGreve(String id) {
        Optional<Greve> optionalGreve = greveRepository.findById(id);
        if (optionalGreve.isPresent()) {
            greveRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("Não foi possível encontrar um registro de greve com esse id.");
        }

    }
}
