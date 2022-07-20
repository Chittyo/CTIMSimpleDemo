package com.ctim.simpledemo.bean;

public class MessageIntimacyBean {

    private String appId;
    private String fromUserId;
    private String targetId;
    private Integer targetType;
    private String groupId;
    private String classname;
    private ContentDTO content;
    private String dateTime;
    private Long timestamp;
    private String msgUID;
    private String source;
    private String isSensitiveWord;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public ContentDTO getContent() {
        return content;
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMsgUID() {
        return msgUID;
    }

    public void setMsgUID(String msgUID) {
        this.msgUID = msgUID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIsSensitiveWord() {
        return isSensitiveWord;
    }

    public void setIsSensitiveWord(String isSensitiveWord) {
        this.isSensitiveWord = isSensitiveWord;
    }

    public static class ContentDTO {
        private String content;
        private Integer intimacyLevel;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getIntimacyLevel() {
            return intimacyLevel;
        }

        public void setIntimacyLevel(Integer intimacyLevel) {
            this.intimacyLevel = intimacyLevel;
        }

        @Override
        public String toString() {
            return "ContentDTO{" +
                    "content='" + content + '\'' +
                    ", intimacyLevel=" + intimacyLevel +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MessageIntimacyBean{" +
                "appId='" + appId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", targetId='" + targetId + '\'' +
                ", targetType=" + targetType +
                ", groupId='" + groupId + '\'' +
                ", classname='" + classname + '\'' +
                ", content=" + content +
                ", dateTime='" + dateTime + '\'' +
                ", timestamp=" + timestamp +
                ", msgUID='" + msgUID + '\'' +
                ", source='" + source + '\'' +
                ", isSensitiveWord='" + isSensitiveWord + '\'' +
                '}';
    }
}
