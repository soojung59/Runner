package com.sj.runner.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "bookmark")
public class BookmarkEntity extends CreateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private MemberEntity member;

    @ManyToOne
    @JoinColumn
    private RecordEntity record;


    @Builder
    public BookmarkEntity(Long id, RecordEntity record,MemberEntity member, LocalDateTime createDate){
        this.id= id;
        this.member = member;
        this.record = record;
    }
}
