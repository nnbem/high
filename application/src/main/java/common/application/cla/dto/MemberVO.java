package common.application.cla.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private String mid;
    private String pwd;
    private String name;
    private String picture;
    private LocalDate birth;
    private String phone;
    private String address;
    private String email;
    private String qrcode;
    private int enabled;
    private List<String> Authorities;
}
