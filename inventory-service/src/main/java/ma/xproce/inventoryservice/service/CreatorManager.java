package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dtos.CreatorDto;
import ma.xproce.inventoryservice.dtos.CreatorRequest;

public interface CreatorManager {
    CreatorDto findById(Long id);
    CreatorDto saveCreator(CreatorRequest request);
}