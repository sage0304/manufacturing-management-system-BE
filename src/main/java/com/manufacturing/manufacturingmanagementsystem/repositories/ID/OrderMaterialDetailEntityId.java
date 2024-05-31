package com.manufacturing.manufacturingmanagementsystem.repositories.ID;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderMaterialDetailEntityId response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class OrderMaterialDetailEntityId implements Serializable {
    private Long materialId;
    private Long orderId;
}
