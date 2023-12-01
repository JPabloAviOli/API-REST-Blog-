
package com.pavila.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime dateComment;


    @ManyToOne(targetEntity = Article.class)
    @NotNull
    private Article article;
    
    @ManyToOne(targetEntity = User.class)
    private User user;

    @PrePersist
    void addDate(){
       this.dateComment = LocalDateTime.now();
    }
}
