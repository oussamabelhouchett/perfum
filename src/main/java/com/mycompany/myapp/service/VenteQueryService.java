package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.Vente;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.VenteRepository;
import com.mycompany.myapp.repository.search.VenteSearchRepository;
import com.mycompany.myapp.service.dto.VenteCriteria;
import com.mycompany.myapp.service.dto.VenteDTO;
import com.mycompany.myapp.service.mapper.VenteMapper;

/**
 * Service for executing complex queries for {@link Vente} entities in the database.
 * The main input is a {@link VenteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VenteDTO} or a {@link Page} of {@link VenteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VenteQueryService extends QueryService<Vente> {

    private final Logger log = LoggerFactory.getLogger(VenteQueryService.class);

    private final VenteRepository venteRepository;

    private final VenteMapper venteMapper;

    private final VenteSearchRepository venteSearchRepository;

    public VenteQueryService(VenteRepository venteRepository, VenteMapper venteMapper, VenteSearchRepository venteSearchRepository) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
        this.venteSearchRepository = venteSearchRepository;
    }

    /**
     * Return a {@link List} of {@link VenteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VenteDTO> findByCriteria(VenteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteMapper.toDto(venteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VenteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VenteDTO> findByCriteria(VenteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteRepository.findAll(specification, page)
            .map(venteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VenteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vente> specification = createSpecification(criteria);
        return venteRepository.count(specification);
    }

    /**
     * Function to convert {@link VenteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Vente> createSpecification(VenteCriteria criteria) {
        Specification<Vente> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Vente_.id));
            }
            if (criteria.getQuantite() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantite(), Vente_.quantite));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Vente_.price));
            }
            if (criteria.getDatevente() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatevente(), Vente_.datevente));
            }
            if (criteria.getTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTime(), Vente_.time));
            }
            if (criteria.getProductId() != null) {
                specification = specification.and(buildSpecification(criteria.getProductId(),
                    root -> root.join(Vente_.product, JoinType.LEFT).get(Product_.id)));
            }
        }
        return specification;
    }
}
