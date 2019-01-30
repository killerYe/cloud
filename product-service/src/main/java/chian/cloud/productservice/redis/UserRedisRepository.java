package chian.cloud.productservice.redis;

import chian.cloud.productservice.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserRedisRepository {
    public static final String USER_KEY_PRE = "user";
    @Autowired
    @Qualifier("userRedisTemplate")
    private RedisTemplate<String, UserDto> redisTemplate;
    private ValueOperations<String,UserDto> operations;

    @PostConstruct
    private void init(){
        this.operations = this.redisTemplate.opsForValue();
    }

    public void saveUser(UserDto userDto){
        this.operations.set(buildKey(userDto.getUser().getId()),userDto);
    }

    public UserDto findOne(int userId){
        String key = buildKey(userId);
        if(!this.redisTemplate.hasKey(key)){
            return null;
        }
        return this.operations.get(key);
    }

    public void delete(int userId){
        this.redisTemplate.delete(this.buildKey(userId));
    }

    protected String buildKey(int userId){
        return USER_KEY_PRE + String.valueOf(userId);
    }

}
