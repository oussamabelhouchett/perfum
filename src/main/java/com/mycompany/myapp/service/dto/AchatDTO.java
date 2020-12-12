package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Achat} entity.
 */
public class AchatDTO implements Serializable {

    private Long id;

    private String quanttiy;

    private String price;

    private LocalDate dateachat;

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

    public String getQuanttiy() {
        return quanttiy;
    }

    public void setQuanttiy(String quanttiy) {
        this.quanttiy = quanttiy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getDateachat() {
        return dateachat;
    }

    public void setDateachat(LocalDate dateachat) {
        this.dateachat = dateachat;
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
        if (!(o instanceof AchatDTO)) {
            return false;
        }

        return id != null && id.equals(((AchatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "AchatDTO{" +
            "id=" + id +
            ", quanttiy='" + quanttiy + '\'' +
            ", price='" + price + '\'' +
            ", dateachat=" + dateachat +
            ", time=" + time +
            ", productId=" + productId +
            ", ProductName='" + ProductName + '\'' +
            ", ProductPrix=" + ProductPrix +
            '}';
    }
}
