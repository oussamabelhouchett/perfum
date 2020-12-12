package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.VenteService;
import com.mycompany.myapp.domain.Vente;
import com.mycompany.myapp.repository.VenteRepository;
import com.mycompany.myapp.repository.search.VenteSearchRepository;
import com.mycompany.myapp.service.dto.VenteDTO;
import com.mycompany.myapp.service.mapper.VenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Vente}.
 */
@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    private final Logger log = LoggerFactory.getLogger(VenteServiceImpl.class);

    private final VenteRepository venteRepository;

    private final VenteMapper venteMapper;

    private final VenteSearchRepository venteSearchRepository;

    public VenteServiceImpl(VenteRepository venteRepository, VenteMapper venteMapper, VenteSearchRepository venteSearchRepository) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
        this.venteSearchRepository = venteSearchRepository;
    }

    @Override
    public VenteDTO save(VenteDTO venteDTO) {
        log.debug("Request to save Vente : {}", venteDTO);
        Vente vente = venteMapper.toEntity(venteDTO);
        vente = venteRepository.save(vente);
        VenteDTO result = venteMapper.toDto(vente);
        venteSearchRepository.save(vente);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ventes");
        return venteRepository.findAll(pageable)
            .map(venteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VenteDTO> findOne(Long id) {
        log.debug("Request to get Vente : {}", id);
        return venteRepository.findById(id)
            .map(venteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vente : {}", id);
        venteRepository.deleteById(id);
        venteSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VenteDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Ventes for query {}", query);
        return venteSearchRepository.search(queryStringQuery(query), pageable)
            .map(venteMapper::toDto);
    }
}
