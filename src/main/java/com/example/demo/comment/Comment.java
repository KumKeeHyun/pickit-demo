package com.example.demo.comment;

import com.example.demo.user.Picker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private Long id;

    private String content;

    @CreatedBy
    @ManyToOne
    private Picker createdBy;


    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;

    public Comment(Long id, String content, Picker picker) {
        this(id,  content, picker,null);
    }
}
