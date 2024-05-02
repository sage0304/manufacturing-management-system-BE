package com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Material.MaterialResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BOMresponse {

    UserResponse productManager;

    private String BOMName;

    private String BOMStatus;

    private Float timeProduction;

    private String unit;

    private Double totalPrice;

    private Double sellPrice;

    private String dateCreation;

    private List<MaterialResponse> materials;

}
