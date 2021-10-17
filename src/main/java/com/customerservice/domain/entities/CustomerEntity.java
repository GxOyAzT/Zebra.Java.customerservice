package com.customerservice.domain.entities;

import com.customerservice.domain.entities.shared.EntityBase;
import com.customerservice.domain.enums.GenderEnum;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(
        name = "customer"
)
@Table(
        name = "customers"
)
public class CustomerEntity extends EntityBase {

    @Column(
            name = "user_id"
    )
    private String userId;

    //region PROPS
    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

    @Column(
            name = "dob"
    )
    private Date dob;

    @Column(
            name = "gender",
            nullable = false
    )
    private GenderEnum gender;

    @OneToMany(
            targetEntity = CouponEntity.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "fk_customerid_coupon"
            )
    )
    private List<CouponEntity> coupons;
    //endregion

    //region GETTERS SETTERS
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public List<CouponEntity> getCoupons() {
        return coupons;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    //endregion
}
