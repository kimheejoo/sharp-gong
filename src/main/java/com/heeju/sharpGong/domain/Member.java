package com.heeju.sharpGong.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_idx")
    private Long id;

    private String memberId;
    private String memberPassword;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Todo> todos = new ArrayList<>();

}
