package com.example.week04n.entity;


import com.example.week04n.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @ManyToOne //한명의 user가 여러 개의 게시물을 작성할 수 있다.  ToOne이 user를 뜻한다.
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.user = user;
    }

    public void update(PostRequestDto postrequestDto) {
        this.title = postrequestDto.getTitle();
        this.contents = postrequestDto.getContents();
    }
}
