package chian.cloud.productservice.mq;

import com.google.common.base.MoreObjects;

//用户消息变更
public class UserMsg {
    public static final String UA_UPDATE = "update";
    public static final String UA_DELETE = "delete";

    /**
     * 变更事件类型
     */
    private String action;
    /**
     * 所变更的用户ID
     */
    private int userId;
    /**
     * Sleuth所生成的同一追踪ID
     */
    private String traceId;

    public UserMsg() {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    protected MoreObjects.ToStringHelper toStringHelper() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("userId", this.getUserId())
                .add("traceId", this.getTraceId());
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
