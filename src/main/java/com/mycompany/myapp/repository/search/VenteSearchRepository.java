package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Vente;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Vente} entity.
 */
public interface VenteSearchRepository extends ElasticsearchRepository<Vente, Long> {
}
