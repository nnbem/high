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
    private int state;          // 상태

    private String class_name;  // 강의명
    private int fno;            // 분야 번호
    private String field;       //분야명

    public String getFormattedclass_history() {
        int hours = class_history / 3600;
        int minutes = (class_history % 3600) / 60;
        int seconds = class_history % 60;
        
        if (hours > 0) {
            return String.format("%d시간 %d분 %d초", hours, minutes, seconds);
        } else {
            return String.format("%d분 %d초", minutes, seconds);
        }
    }

}
