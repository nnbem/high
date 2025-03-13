package common.application.cla.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassHistoryVO {

    private int chno;           // 수강내역 번호
    private Date regDate;       // 수강신청일
    private String mid;         // 신청자 아이디
    private int clno;           // 강의 번호
    private int class_history;  // 세부 강의 시청시간
    private int complete;       // 완료여부
    private int enrollCount;    // 수강신청카운트

}
