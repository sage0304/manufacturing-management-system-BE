package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.BOM.BOMResponse;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the IBOMsServices response
public interface IBOMsServices {

    BOMsDTO getBOMById(Long id);

    BOMsEntity createBOM(BOMRequest bomRequest);

    BOMsEntity findBOMByName(String name);

    Boolean deleteBOM(Long id);

    List<BOMsEntity> getAllBOMs();

    List<BOMsEntity> getAllBOMsbyStatus(String status);

    List<BOMsEntity> getBOMsByLikeName(String name);

    void updateBOM(BOMRequest bomRequest, Long id);

    BOMsEntity findBOMById(Long id);
}

