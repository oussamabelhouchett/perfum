package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AchatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Achat}.
 */
public interface AchatService {

    /**
     * Save a achat.
     *
     * @param achatDTO the entity to save.
     * @return the persisted entity.
     */
    AchatDTO save(AchatDTO achatDTO);

    /**
     * Get all the achats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AchatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AchatDTO> findOne(Long id);

    /**
     * Delete the "id" achat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the achat corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AchatDTO> search(String query, Pageable pageable);
}
