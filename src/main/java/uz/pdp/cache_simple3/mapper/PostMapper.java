package uz.pdp.cache_simple3.mapper;

import org.mapstruct.*;
import uz.pdp.cache_simple3.dto.PostCreateDTO;
import uz.pdp.cache_simple3.entity.Post;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    Post toEntity(PostCreateDTO postCreateDTO);

    PostCreateDTO toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostCreateDTO postCreateDTO, @MappingTarget Post post);
}