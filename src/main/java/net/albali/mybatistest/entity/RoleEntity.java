package net.albali.mybatistest.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class RoleEntity extends BaseEntity {


    private Long id;
    private String name;

}
