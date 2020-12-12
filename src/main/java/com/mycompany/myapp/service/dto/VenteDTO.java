package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {

    private Long id;

    private Integer quantite;

    private Integer price;

    private LocalDate datevente;

    private Instant time;


    private Long productId;
    private String ProductName;
    private Integer ProductPrix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getDatevente() {
        return datevente;
    }

    public void setDatevente(LocalDate datevente) {
        this.datevente = datevente;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getProductPrix() {
        return ProductPrix;
    }

    public void setProductPrix(Integer productPrix) {
        ProductPrix = productPrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenteDTO)) {
            return false;
        }

        return id != null && id.equals(((VenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + id +
            ", quantite=" + quantite +
            ", price=" + price +
            ", datevente=" + datevente +
            ", time=" + time +
            ", productId=" + productId +
            ", ProductName='" + ProductName + '\'' +
            ", ProductPrix=" + ProductPrix +
            '}';
    }
}
