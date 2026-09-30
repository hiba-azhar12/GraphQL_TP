 package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dtos.CreatorDto;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.mappers.CreatorMapper;
import org.springframework.stereotype.Service;

@Service
public class CreatorManagerImpl implements CreatorManager {

    private final CreatorRepository creatorRepository;
    private final CreatorMapper creatorMapper;

    public CreatorManagerImpl(CreatorRepository creatorRepository, CreatorMapper creatorMapper) {
        this.creatorRepository = creatorRepository;
        this.creatorMapper = creatorMapper;
    }

    @Override
    public CreatorDto findById(Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return creatorMapper.fromCreatorToCreatorDto(creator);
    }

    @Override
    public CreatorDto saveCreator(CreatorRequest request) {
        Creator creator = creatorRepository.save(creatorMapper.fromCreatorRequestToCreator(request));
        return creatorMapper.fromCreatorToCreatorDto(creator);
    }
}