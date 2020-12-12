package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AchatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Achat} and its DTO {@link AchatDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface AchatMapper extends EntityMapper<AchatDTO, Achat> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "productPrix")
    AchatDTO toDto(Achat achat);

    @Mapping(source = "productId", target = "product")
    Achat toEntity(AchatDTO achatDTO);

    default Achat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Achat achat = new Achat();
        achat.setId(id);
        return achat;
    }
}
