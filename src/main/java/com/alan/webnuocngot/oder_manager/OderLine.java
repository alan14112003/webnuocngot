package com.alan.webnuocngot.oder_manager;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oder_line")
public class OderLine {
    @Id
    Integer oderId;
    Integer productId;
    int oderQuantity;

    public Integer getOderId() {
        return oderId;
    }

    public void setOderId(Integer oderId) {
        this.oderId = oderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getOderQuantity() {
        return oderQuantity;
    }

    public void setOderQuantity(int oderQuantity) {
        this.oderQuantity = oderQuantity;
    }
}
