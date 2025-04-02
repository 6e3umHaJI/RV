package by.bsuir.publisher.services;

import by.bsuir.publisher.dto.requests.CommentRequestDto;
import by.bsuir.publisher.dto.responses.CommentResponseDto;

import java.util.List;

public interface CommentService extends BaseService<CommentRequestDto, CommentResponseDto> {
    List<CommentResponseDto> readAll();
}
