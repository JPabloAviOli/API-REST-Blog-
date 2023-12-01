package com.pavila.blog.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime articleDate;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn (name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @PrePersist
    void addDate(){
        this.articleDate = LocalDateTime.now();
    }

}
