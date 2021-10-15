package com.customerservice.domain.entities;

import com.customerservice.domain.entities.shared.EntityBase;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity(
        name = "coupon"
)
@Table(
        name = "coupons"
)
public class CouponEntity extends EntityBase {

    //region PROPS
    @Column(
            name = "customer_id"
    )
    private UUID customerId;

    @Column(
            name = "value",
            nullable = false
    )
    private int value;

    @Column(
            name = "expires",
            nullable = false
    )
    private Date expires;
    //endregion

    //region GETTERS SETTERS
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
    //endregion
}
