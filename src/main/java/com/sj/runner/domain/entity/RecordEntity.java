package com.sj.runner.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "record")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

    @Column(length = 10, nullable = false)
    private Long member_id;

    @Column(length = 10, nullable = false)
    private Long start_latitude;

    @Column(length = 10, nullable = false)
    private Long start_hardness;

    @Column(length = 10, nullable = false)
    private Long arrival_latitude;

    @Column(length = 10, nullable = false)
    private Long arrival_hardness;

    @Column(length = 10, nullable = false)
    private LocalDateTime time;

    @Column(length = 10, nullable = false)
    private String memo;

    @Column(length = 10, nullable = false)
    private int heart;

    @Column(length = 10, nullable = false)
    private int level;

    @Column(length = 10, nullable = false)
    private int view;


    @Builder
    public RecordEntity(Long record_id, Long member_id, Long start_latitude, Long start_hardness, Long arrival_latitude, Long arrival_hardness, LocalDateTime time, String memo, int level , int heart , int view){
        this.record_id = record_id;
        this.member_id = member_id;
        this.start_latitude = start_latitude;
        this.start_hardness = start_hardness;
        this.arrival_latitude = arrival_latitude;
        this.arrival_hardness = arrival_hardness;
        this.time = time;
        this.memo = memo;
        this.level = level;
        this.heart = heart;
        this.view = view;
    }


}
