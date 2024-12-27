package com.woori.logincustom.Entity;

import com.woori.logincustom.Constant.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString
@Builder
@Table(name = "users")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String loginid;
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
