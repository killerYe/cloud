package chian.cloud.productservice.service.impl;

import chian.cloud.productservice.entity.User;
import chian.cloud.productservice.entity.UserDto;
import chian.cloud.productservice.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class UserServiceFallback implements UserService {
    @Override
    public List<UserDto> users() {
//        return null;
        return this.findAllFallback();
    }

    @Override
    public UserDto getUser(int id) {
//        return null;
        return this.loadFallback(id);
    }

    protected List<UserDto> findAllFallback(){
        List<UserDto> dtos = new ArrayList<>();
        dtos.add(new UserDto(new User(1,"zhangSan_static","/users/avatar/zhangsan.png"),0));
        return dtos;
    }

    protected UserDto loadFallback(int id){
        return new UserDto(new User(id,"Anonymous","default.png"),0);
    }

}
