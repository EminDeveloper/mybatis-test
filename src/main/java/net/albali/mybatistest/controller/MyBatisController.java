package net.albali.mybatistest.controller;

import lombok.RequiredArgsConstructor;
import net.albali.mybatistest.entity.UserEntity;
import net.albali.mybatistest.model.InsertRequest;
import net.albali.mybatistest.model.UpdateRoleRequest;
import net.albali.mybatistest.service.MyBatisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyBatisController {

    private final MyBatisService myBatisService;

    @GetMapping("/getuser")
    public UserEntity getUser(@RequestParam String name) {
        return myBatisService.getUserDataByName(name);
    }

    @GetMapping("/all")
    public List<UserEntity> getAll() {
        return myBatisService.getAll();
    }


    @PostMapping("/insert")
    public String insert(@RequestBody InsertRequest request) {
        return myBatisService.insertUserEntity(request);
    }

    @PostMapping("/updaterole")
    public String updateRole(@RequestBody UpdateRoleRequest request) {
        return myBatisService.updateRole(request);
    }
}

