package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AchatService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.AchatDTO;
import com.mycompany.myapp.service.dto.AchatCriteria;
import com.mycompany.myapp.service.AchatQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Achat}.
 */
@RestController
@RequestMapping("/api")
public class AchatResource {

    private final Logger log = LoggerFactory.getLogger(AchatResource.class);

    private static final String ENTITY_NAME = "achat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AchatService achatService;

    private final AchatQueryService achatQueryService;

    public AchatResource(AchatService achatService, AchatQueryService achatQueryService) {
        this.achatService = achatService;
        this.achatQueryService = achatQueryService;
    }

    /**
     * {@code POST  /achats} : Create a new achat.
     *
     * @param achatDTO the achatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new achatDTO, or with status {@code 400 (Bad Request)} if the achat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/achats")
    public ResponseEntity<AchatDTO> createAchat(@RequestBody AchatDTO achatDTO) throws URISyntaxException {
        log.debug("REST request to save Achat : {}", achatDTO);
        if (achatDTO.getId() != null) {
            throw new BadRequestAlertException("A new achat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchatDTO result = achatService.save(achatDTO);
        return ResponseEntity.created(new URI("/api/achats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /achats} : Updates an existing achat.
     *
     * @param achatDTO the achatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated achatDTO,
     * or with status {@code 400 (Bad Request)} if the achatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the achatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/achats")
    public ResponseEntity<AchatDTO> updateAchat(@RequestBody AchatDTO achatDTO) throws URISyntaxException {
        log.debug("REST request to update Achat : {}", achatDTO);
        if (achatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AchatDTO result = achatService.save(achatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, achatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /achats} : get all the achats.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of achats in body.
     */
    @GetMapping("/achats")
    public ResponseEntity<List<AchatDTO>> getAllAchats(AchatCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Achats by criteria: {}", criteria);
        Page<AchatDTO> page = achatQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /achats/count} : count all the achats.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/achats/count")
    public ResponseEntity<Long> countAchats(AchatCriteria criteria) {
        log.debug("REST request to count Achats by criteria: {}", criteria);
        return ResponseEntity.ok().body(achatQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /achats/:id} : get the "id" achat.
     *
     * @param id the id of the achatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the achatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/achats/{id}")
    public ResponseEntity<AchatDTO> getAchat(@PathVariable Long id) {
        log.debug("REST request to get Achat : {}", id);
        Optional<AchatDTO> achatDTO = achatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achatDTO);
    }

    /**
     * {@code DELETE  /achats/:id} : delete the "id" achat.
     *
     * @param id the id of the achatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/achats/{id}")
    public ResponseEntity<Void> deleteAchat(@PathVariable Long id) {
        log.debug("REST request to delete Achat : {}", id);
        achatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/achats?query=:query} : search for the achat corresponding
     * to the query.
     *
     * @param query the query of the achat search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/achats")
    public ResponseEntity<List<AchatDTO>> searchAchats(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Achats for query {}", query);
        Page<AchatDTO> page = achatService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        }
}
