package com.example.week04n.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // 맴버변수가 컬럼이 되도록한다.
@EntityListeners(AuditingEntityListener.class)      // 변경사항있으면 반영시킴
public abstract class Timestamped {

    @CreatedDate            // 생성시점
    private LocalDateTime createAt;

    @LastModifiedDate       // 마지막수정시점
    private LocalDateTime modifiedAt;
}
