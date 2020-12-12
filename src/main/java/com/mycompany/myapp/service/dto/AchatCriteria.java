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
 * Criteria class for the {@link com.mycompany.myapp.domain.Achat} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.AchatResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /achats?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AchatCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter quanttiy;

    private StringFilter price;

    private LocalDateFilter dateachat;

    private InstantFilter time;

    private LongFilter productId;

    public AchatCriteria() {
    }

    public AchatCriteria(AchatCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.quanttiy = other.quanttiy == null ? null : other.quanttiy.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.dateachat = other.dateachat == null ? null : other.dateachat.copy();
        this.time = other.time == null ? null : other.time.copy();
        this.productId = other.productId == null ? null : other.productId.copy();
    }

    @Override
    public AchatCriteria copy() {
        return new AchatCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getQuanttiy() {
        return quanttiy;
    }

    public void setQuanttiy(StringFilter quanttiy) {
        this.quanttiy = quanttiy;
    }

    public StringFilter getPrice() {
        return price;
    }

    public void setPrice(StringFilter price) {
        this.price = price;
    }

    public LocalDateFilter getDateachat() {
        return dateachat;
    }

    public void setDateachat(LocalDateFilter dateachat) {
        this.dateachat = dateachat;
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
        final AchatCriteria that = (AchatCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(quanttiy, that.quanttiy) &&
            Objects.equals(price, that.price) &&
            Objects.equals(dateachat, that.dateachat) &&
            Objects.equals(time, that.time) &&
            Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        quanttiy,
        price,
        dateachat,
        time,
        productId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AchatCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quanttiy != null ? "quanttiy=" + quanttiy + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (dateachat != null ? "dateachat=" + dateachat + ", " : "") +
                (time != null ? "time=" + time + ", " : "") +
                (productId != null ? "productId=" + productId + ", " : "") +
            "}";
    }

}
