package ua.com.owu.sep2021javaadv.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {
    int statusCode;
    private String msg;
}
