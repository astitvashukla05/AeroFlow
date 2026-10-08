package com.aeroflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeroflow.payload.response.ApiResponse;

@RestController
public class Hello {

    @GetMapping("/home")
    public String working() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("HELLO meow");
        return apiResponse.getMessage();

    }

}
