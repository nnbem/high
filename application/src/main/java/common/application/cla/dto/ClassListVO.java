package common.application.cla.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassListVO {

    private int clno;               // 강의 번호
    private String class_name;      // 강의명
    private Date regDate;           // 등록일
    private String class_video;     // 강의 영상 URL
    private int video_length;       // 영상 길이
    private int fsno;               // 기술 스택 분류 번호
    private String introduce;       // 강의 소개
    private int fno;                // 분야 번호
    private String field;           // 분야명 (웹개발, 인공지능 등)
    private int skno;               // 기술 스택 번호
    private String skill_stack;     // 기술 스택명 (Java, HTML 등)

    // 변환된 시간 반환 메서드
    public String getFormattedVideoLength() {
        int hours = video_length / 3600;
        int minutes = (video_length % 3600) / 60;
        int seconds = video_length % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
