package by.bsuir.publisher.dto.requests.converters;

import by.bsuir.publisher.domain.Comment;
import by.bsuir.publisher.dto.requests.CommentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentRequestConverter {
    @Mapping(source = "topicId", target = "topic.id")
    Comment fromDto(CommentRequestDto comment);
}
