package by.bsuir.publisher.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_comment")
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity {
    @ManyToOne
    private Topic topic;
    private String content;

}
