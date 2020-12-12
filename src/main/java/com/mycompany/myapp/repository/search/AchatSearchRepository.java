package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Achat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Achat} entity.
 */
public interface AchatSearchRepository extends ElasticsearchRepository<Achat, Long> {
}
