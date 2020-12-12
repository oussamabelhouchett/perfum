package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CategorieService;
import com.mycompany.myapp.domain.Categorie;
import com.mycompany.myapp.repository.CategorieRepository;
import com.mycompany.myapp.repository.search.CategorieSearchRepository;
import com.mycompany.myapp.service.dto.CategorieDTO;
import com.mycompany.myapp.service.mapper.CategorieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Categorie}.
 */
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);

    private final CategorieRepository categorieRepository;

    private final CategorieMapper categorieMapper;

    private final CategorieSearchRepository categorieSearchRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMapper categorieMapper, CategorieSearchRepository categorieSearchRepository) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
        this.categorieSearchRepository = categorieSearchRepository;
    }

    @Override
    public CategorieDTO save(CategorieDTO categorieDTO) {
        log.debug("Request to save Categorie : {}", categorieDTO);
        Categorie categorie = categorieMapper.toEntity(categorieDTO);
        categorie = categorieRepository.save(categorie);
        CategorieDTO result = categorieMapper.toDto(categorie);
        categorieSearchRepository.save(categorie);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieDTO> findAll() {
        log.debug("Request to get all Categories");
        return categorieRepository.findAll().stream()
            .map(categorieMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieDTO> findOne(Long id) {
        log.debug("Request to get Categorie : {}", id);
        return categorieRepository.findById(id)
            .map(categorieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Categorie : {}", id);
        categorieRepository.deleteById(id);
        categorieSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieDTO> search(String query) {
        log.debug("Request to search Categories for query {}", query);
        return StreamSupport
            .stream(categorieSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(categorieMapper::toDto)
        .collect(Collectors.toList());
    }
}
