package com.line.line_dev_ec_pretest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseMsg{

    private String status;

    private String message;

    private Object data;

    public ResponseMsg(String status, String message){
        super();
        this.status = status;
        this.message = message;
    }
}
