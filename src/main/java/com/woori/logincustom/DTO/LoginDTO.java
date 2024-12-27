package com.woori.logincustom.DTO;

import com.woori.logincustom.Constant.RoleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder @ToString
public class LoginDTO {
    private Integer id;
    private String loginid;
    private String password;
    private String username;
    private RoleType roleType;
}
