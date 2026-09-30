package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dtos.CreatorDto;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Creator fromCreatorRequestToCreator(CreatorRequest request) {
        return modelMapper.map(request, Creator.class);
    }

    public CreatorDto fromCreatorToCreatorDto(Creator creator) {
        return modelMapper.map(creator, CreatorDto.class);
    }
}