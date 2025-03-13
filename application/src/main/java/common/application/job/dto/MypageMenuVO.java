package common.application.job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MypageMenuVO {

    private String mcode; //코드
	private String mname; //이름
	private String murl; //url
	private String mtext; //javascript
	private String upcode; //상위레벨코드
	private int mlevel; //메뉴레벨

}
