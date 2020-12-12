package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.VenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Vente}.
 */
public interface VenteService {

    /**
     * Save a vente.
     *
     * @param venteDTO the entity to save.
     * @return the persisted entity.
     */
    VenteDTO save(VenteDTO venteDTO);

    /**
     * Get all the ventes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VenteDTO> findOne(Long id);

    /**
     * Delete the "id" vente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the vente corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VenteDTO> search(String query, Pageable pageable);
}
