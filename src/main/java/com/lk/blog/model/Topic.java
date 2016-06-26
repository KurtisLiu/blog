package com.lk.blog.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Alias("topic")
public class Topic {
    private String id;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
    private User author;
    private Tag tag;
    private int replyCount;
    private int visitCount;
    private boolean isGood;
    private Date lastReplyDate;
    private User lastReplyUser;
    private boolean isDelete;
    private List<Reply> Replies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public Date getLastReplyDate() {
        return lastReplyDate;
    }

    public void setLastReplyDate(Date lastReplyDate) {
        this.lastReplyDate = lastReplyDate;
    }

    public User getLastReplyUser() {
        return lastReplyUser;
    }

    public void setLastReplyUser(User lastReplyUser) {
        this.lastReplyUser = lastReplyUser;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Reply> getReplies() {
        return Replies;
    }

    public void setReplies(List<Reply> replies) {
        Replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (isDelete != topic.isDelete) return false;
        if (id != null ? !id.equals(topic.id) : topic.id != null) return false;
        return Replies != null ? Replies.equals(topic.Replies) : topic.Replies == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isDelete ? 1 : 0);
        result = 31 * result + (Replies != null ? Replies.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", author=" + author +
                ", tag=" + tag +
                ", replyCount=" + replyCount +
                ", visitCount=" + visitCount +
                ", isGood=" + isGood +
                ", lastReplyDate=" + lastReplyDate +
                ", lastReplyUser=" + lastReplyUser +
                ", isDelete=" + isDelete +
                ", Replies=" + Replies +
                '}';
    }
}
