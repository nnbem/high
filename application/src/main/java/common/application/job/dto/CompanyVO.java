package common.application.job.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyVO {
    private String cno;
    private String com_name;
    private LocalDate regdate;
    private String phone;
    private String address;
    private int empl_num;
    private String type;
    private String ceo;
    private String infra;
    private String business;
    private String pwd;
    private String email;
    private String qrcode;
    private String authority;
    private int enabled;
}
