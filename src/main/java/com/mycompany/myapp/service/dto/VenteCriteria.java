package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Vente} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.VenteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ventes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VenteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter quantite;

    private IntegerFilter price;

    private LocalDateFilter datevente;

    private InstantFilter time;

    private LongFilter productId;

    public VenteCriteria() {
    }

    public VenteCriteria(VenteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.quantite = other.quantite == null ? null : other.quantite.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.datevente = other.datevente == null ? null : other.datevente.copy();
        this.time = other.time == null ? null : other.time.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public VenteCriteria copy() {
        return new VenteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getQuantite() {
        return quantite;
    }

    public void setQuantite(IntegerFilter quantite) {
        this.quantite = quantite;
    }

    public IntegerFilter getPrice() {
        return price;
    }

    public void setPrice(IntegerFilter price) {
        this.price = price;
    }

    public LocalDateFilter getDatevente() {
        return datevente;
    }

    public void setDatevente(LocalDateFilter datevente) {
        this.datevente = datevente;
    }

    public InstantFilter getTime() {
        return time;
    }

    public void setTime(InstantFilter time) {
        this.time = time;
    }

    public LongFilter getProductId() {
        return productId;
    }

    public void setProductId(LongFilter productId) {
        this.productId = productId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VenteCriteria that = (VenteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(quantite, that.quantite) &&
            Objects.equals(price, that.price) &&
            Objects.equals(datevente, that.datevente) &&
            Objects.equals(time, that.time) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        quantite,
        price,
        datevente,
        time,
        productId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantite != null ? "quantite=" + quantite + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (datevente != null ? "datevente=" + datevente + ", " : "") +
                (time != null ? "time=" + time + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
