package by.bsuir.publisher.services.impl;

import by.bsuir.publisher.domain.Comment;
import by.bsuir.publisher.dto.requests.CommentRequestDto;
import by.bsuir.publisher.dto.requests.converters.CommentRequestConverter;
import by.bsuir.publisher.dto.responses.CommentResponseDto;
import by.bsuir.publisher.dto.responses.converters.CollectionCommentResponseConverter;
import by.bsuir.publisher.dto.responses.converters.CommentResponseConverter;
import by.bsuir.publisher.exceptions.EntityExistsException;
import by.bsuir.publisher.exceptions.Messages;
import by.bsuir.publisher.exceptions.NoEntityExistsException;
import by.bsuir.publisher.repositories.CommentRepository;
import by.bsuir.publisher.services.CommentService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentRequestConverter commentRequestConverter;
    private final CommentResponseConverter commentResponseConverter;
    private final CollectionCommentResponseConverter collectionCommentResponseConverter;

    @Override
    @Validated
    public CommentResponseDto create(@Valid @NonNull CommentRequestDto dto) throws EntityExistsException {
        Optional<Comment> comment = dto.getId() == null ? Optional.empty() : commentRepository.findById(dto.getId());
        if (comment.isEmpty()) {
            return commentResponseConverter.toDto(commentRepository.save(commentRequestConverter.fromDto(dto)));
        } else {
            throw new EntityExistsException(Messages.EntityExistsException);
        }
    }

    @Override
    public Optional<CommentResponseDto> read(@NonNull Long id) {
        return commentRepository.findById(id).flatMap(comment -> Optional.of(
                commentResponseConverter.toDto(comment)));
    }

    @Override
    @Validated
    public CommentResponseDto update(@Valid @NonNull CommentRequestDto dto) throws NoEntityExistsException {
        Optional<Comment> comment = dto.getId() == null || commentRepository.findById(dto.getId()).isEmpty() ?
                Optional.empty() : Optional.of(commentRequestConverter.fromDto(dto));
        return commentResponseConverter.toDto(commentRepository.save(comment.orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException))));
    }

    @Override
    public Long delete(@NonNull Long id) throws NoEntityExistsException {
        Optional<Comment> comment = commentRepository.findById(id);
        commentRepository.deleteById(comment.map(Comment::getId).orElseThrow(() ->
                new NoEntityExistsException(Messages.NoEntityExistsException)));
        return comment.get().getId();
    }

    @Override
    public List<CommentResponseDto> readAll() {
        return collectionCommentResponseConverter.toListDto(commentRepository.findAll());
    }
}
