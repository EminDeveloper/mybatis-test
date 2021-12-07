package net.albali.mybatistest.model;


import lombok.Data;

@Data
public class InsertRequest {

    private String username;
    private String password;
    private String role;
}
