package net.albali.mybatistest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity extends BaseEntity {

    @JsonIgnore
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Status status;
    private List<RoleEntity> role;

    private String roleName;

}
