package common.application.cla.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassMypageMenuVO {
    private String cmcode; //코드
	private String cmname; //이름
	private String cmurl; //url
	private String cmtext; //javascript
	private String upcode; //상위레벨코드
	private int cmlevel; //메뉴레벨
}
