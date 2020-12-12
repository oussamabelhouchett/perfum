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

import com.mycompany.myapp.domain.Achat;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.AchatRepository;
import com.mycompany.myapp.repository.search.AchatSearchRepository;
import com.mycompany.myapp.service.dto.AchatCriteria;
import com.mycompany.myapp.service.dto.AchatDTO;
import com.mycompany.myapp.service.mapper.AchatMapper;

/**
 * Service for executing complex queries for {@link Achat} entities in the database.
 * The main input is a {@link AchatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AchatDTO} or a {@link Page} of {@link AchatDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AchatQueryService extends QueryService<Achat> {

    private final Logger log = LoggerFactory.getLogger(AchatQueryService.class);

    private final AchatRepository achatRepository;

    private final AchatMapper achatMapper;

    private final AchatSearchRepository achatSearchRepository;

    public AchatQueryService(AchatRepository achatRepository, AchatMapper achatMapper, AchatSearchRepository achatSearchRepository) {
        this.achatRepository = achatRepository;
        this.achatMapper = achatMapper;
        this.achatSearchRepository = achatSearchRepository;
    }

    /**
     * Return a {@link List} of {@link AchatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AchatDTO> findByCriteria(AchatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Achat> specification = createSpecification(criteria);
        return achatMapper.toDto(achatRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AchatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AchatDTO> findByCriteria(AchatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Achat> specification = createSpecification(criteria);
        return achatRepository.findAll(specification, page)
            .map(achatMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AchatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Achat> specification = createSpecification(criteria);
        return achatRepository.count(specification);
    }

    /**
     * Function to convert {@link AchatCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Achat> createSpecification(AchatCriteria criteria) {
        Specification<Achat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Achat_.id));
            }
            if (criteria.getQuanttiy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQuanttiy(), Achat_.quanttiy));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrice(), Achat_.price));
            }
            if (criteria.getDateachat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateachat(), Achat_.dateachat));
            }
            if (criteria.getTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTime(), Achat_.time));
            }
            if (criteria.getProductId() != null) {
                specification = specification.and(buildSpecification(criteria.getProductId(),
                    root -> root.join(Achat_.product, JoinType.LEFT).get(Product_.id)));
            }
        }
        return specification;
    }
}
