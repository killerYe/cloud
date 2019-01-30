package chain.cloud.userservice.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserMsgSender {
    protected Logger logger = LoggerFactory.getLogger(UserMsgSender.class);
    private Source source;
    @Autowired
    public UserMsgSender(Source source){
        this.source = source;
    }

    public void sendMsg(UserMsg userMsg){
        this.logger.debug("发送用户消息{}",userMsg);
        this.source.output().send(MessageBuilder.withPayload(userMsg).build());
    }

}
