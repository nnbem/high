package common.application.cla.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassMenuVO {

    private String ccode; //코드
	private String cname; //이름
	private String curl; //url
	private String ctext; //javascript
	private String upcode; //상위레벨코드
	private int clevel; //메뉴레벨


}
