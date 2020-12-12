package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Achat.
 */
@Entity
@Table(name = "achat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "achat")
public class Achat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quanttiy")
    private String quanttiy;

    @Column(name = "price")
    private String price;

    @Column(name = "dateachat")
    private LocalDate dateachat;

    @Column(name = "time")
    private Instant time;

    @ManyToOne
    @JsonIgnoreProperties(value = "achats", allowSetters = true)
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuanttiy() {
        return quanttiy;
    }

    public Achat quanttiy(String quanttiy) {
        this.quanttiy = quanttiy;
        return this;
    }

    public void setQuanttiy(String quanttiy) {
        this.quanttiy = quanttiy;
    }

    public String getPrice() {
        return price;
    }

    public Achat price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getDateachat() {
        return dateachat;
    }

    public Achat dateachat(LocalDate dateachat) {
        this.dateachat = dateachat;
        return this;
    }

    public void setDateachat(LocalDate dateachat) {
        this.dateachat = dateachat;
    }

    public Instant getTime() {
        return time;
    }

    public Achat time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Product getProduct() {
        return product;
    }

    public Achat product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Achat)) {
            return false;
        }
        return id != null && id.equals(((Achat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Achat{" +
            "id=" + getId() +
            ", quanttiy='" + getQuanttiy() + "'" +
            ", price='" + getPrice() + "'" +
            ", dateachat='" + getDateachat() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
