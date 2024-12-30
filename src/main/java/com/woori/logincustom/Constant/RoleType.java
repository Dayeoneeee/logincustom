package com.woori.logincustom.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 하나의 파일의 하나의 분류
// 상품분류, 호텔분류, 직원직급
// 권한분류
@Getter
@AllArgsConstructor
public enum RoleType {
    USER,MANAGER,ADMIN
}
