package com.manufacturing.manufacturingmanagementsystem.service.Materials;

import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialsServices implements IMaterialsServices {

    private final MaterialsRepository materialsRepository;

    // Các phương thức service khác cần thiết
}
