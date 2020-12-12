package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.AchatService;
import com.mycompany.myapp.domain.Achat;
import com.mycompany.myapp.repository.AchatRepository;
import com.mycompany.myapp.repository.search.AchatSearchRepository;
import com.mycompany.myapp.service.dto.AchatDTO;
import com.mycompany.myapp.service.mapper.AchatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Achat}.
 */
@Service
@Transactional
public class AchatServiceImpl implements AchatService {

    private final Logger log = LoggerFactory.getLogger(AchatServiceImpl.class);

    private final AchatRepository achatRepository;

    private final AchatMapper achatMapper;

    private final AchatSearchRepository achatSearchRepository;

    public AchatServiceImpl(AchatRepository achatRepository, AchatMapper achatMapper, AchatSearchRepository achatSearchRepository) {
        this.achatRepository = achatRepository;
        this.achatMapper = achatMapper;
        this.achatSearchRepository = achatSearchRepository;
    }

    @Override
    public AchatDTO save(AchatDTO achatDTO) {
        log.debug("Request to save Achat : {}", achatDTO);
        Achat achat = achatMapper.toEntity(achatDTO);
        achat = achatRepository.save(achat);
        AchatDTO result = achatMapper.toDto(achat);
        achatSearchRepository.save(achat);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AchatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Achats");
        return achatRepository.findAll(pageable)
            .map(achatMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AchatDTO> findOne(Long id) {
        log.debug("Request to get Achat : {}", id);
        return achatRepository.findById(id)
            .map(achatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Achat : {}", id);
        achatRepository.deleteById(id);
        achatSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AchatDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Achats for query {}", query);
        return achatSearchRepository.search(queryStringQuery(query), pageable)
            .map(achatMapper::toDto);
    }
}
