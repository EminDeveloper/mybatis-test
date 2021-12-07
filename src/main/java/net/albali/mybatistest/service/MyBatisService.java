package net.albali.mybatistest.service;

import lombok.RequiredArgsConstructor;
import net.albali.mybatistest.entity.RoleEntity;
import net.albali.mybatistest.entity.UserEntity;
import net.albali.mybatistest.mapper.MyBatisMapper;
import net.albali.mybatistest.model.InsertRequest;
import net.albali.mybatistest.model.UpdateRoleRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyBatisService {

    private final MyBatisMapper myBatisMapper;


    public UserEntity getUserDataByName(String name) {
        return myBatisMapper.findUserEntityByUsername(name);
    }

    public List<UserEntity> getAll() {
        return myBatisMapper.findAllUsers();
    }

    public String insertUserEntity(InsertRequest request) {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(request.getRole());
        roleEntityList.add(roleEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userEntity.setRole(roleEntityList);

        userEntity.setRoleName(request.getRole());

        myBatisMapper.insertUser(userEntity);

        return "OK";
    }

    public String updateRole(UpdateRoleRequest request) {
        myBatisMapper.changeUserRole(request.getUsername(), request.getRole());

        return "OK";
    }

}
