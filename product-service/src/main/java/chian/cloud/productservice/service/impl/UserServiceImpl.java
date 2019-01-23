package chian.cloud.productservice.service.impl;

import chian.cloud.productservice.entity.User;
import chian.cloud.productservice.entity.UserDto;
import chian.cloud.productservice.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    @HystrixCommand(fallbackMethod = "findAllFallback")
    public List<UserDto> users() {
        UserDto[] dtos = this.restTemplate.getForObject("http://USERSERVICE/users",UserDto[].class);
        return Arrays.asList(dtos);
    }

    @Override
    @HystrixCommand(fallbackMethod = "loadFallback")
    public UserDto getUser(int id) {
        return restTemplate.getForEntity("http://USERSERVICE/users/"+id,UserDto.class).getBody();
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
