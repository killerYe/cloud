package chian.cloud.productservice.mq;

import chian.cloud.productservice.redis.UserRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class UserMsgListener {
    private Logger logger = LoggerFactory.getLogger(UserMsgListener.class);
    @Autowired
    private UserRedisRepository userRedisRepository;
    @StreamListener(Sink.INPUT)
    public void onUserMsgSink(UserMsg userMsg){
        if(UserMsg.UA_UPDATE.equalsIgnoreCase(userMsg.getAction())){
            this.logger.info("收到用户更新消息，id为{}",userMsg.getUserId());
            this.userRedisRepository.delete(userMsg.getUserId());
        }else if(UserMsg.UA_DELETE.equalsIgnoreCase(userMsg.getAction())){
            this.logger.info("收到用户删除消息，id为{}",userMsg.getUserId());
            this.userRedisRepository.delete(userMsg.getUserId());
        }else{
            this.logger.info("收到未知用户消息，id为{}",userMsg.getUserId());
        }
    }
}
