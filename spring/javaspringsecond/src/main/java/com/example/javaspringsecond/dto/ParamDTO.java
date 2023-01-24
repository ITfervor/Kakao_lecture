package com.example.javaspringsecond.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ParamDTO {
    private String name;
    private String email;
    private String organization;
}
