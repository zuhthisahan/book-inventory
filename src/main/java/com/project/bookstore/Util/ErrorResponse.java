package com.project.bookstore.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@XmlRootElement(name = "error")
public class ErrorResponse {
    public ErrorResponse(String error) {
        super();
        this.error = error;
    }

    private String error;


}
