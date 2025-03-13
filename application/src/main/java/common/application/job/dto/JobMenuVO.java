package common.application.job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobMenuVO {

    private String jcode; //코드
	private String jname; //이름
	private String jurl; //url
	private String jtext; //javascript
	private String upcode; //상위레벨코드
	private int jlevel; //메뉴레벨

}
