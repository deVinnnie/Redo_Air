package com.realdolmen.air.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Embeddable
public class Discount{

    /**
     * The percentage, specified as a fraction. A number between 0.00 and 1.00
     */
    @DecimalMin("0.00")
    @DecimalMax("1.00")
    @Digits(integer=6, fraction=2)
    @NotNull
    private BigDecimal discountPercentage;

    public Discount() {
    }

    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
