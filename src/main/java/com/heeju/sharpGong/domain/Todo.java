package com.heeju.sharpGong.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    private Boolean isDone;
    private String work;

}
