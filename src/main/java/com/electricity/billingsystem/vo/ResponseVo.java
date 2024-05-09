package com.electricity.billingsystem.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private String status;
    private String message;
    private T data;
    public ResponseVo(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseVo(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
