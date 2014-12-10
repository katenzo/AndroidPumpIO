package model.register;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Links {

    @Expose
    private Self self;
    @SerializedName("activity-inbox")
    @Expose
    private ActivityInbox activityInbox;
    @SerializedName("activity-outbox")
    @Expose
    private ActivityOutbox activityOutbox;

    /**
     *
     * @return
     * The self
     */
    public Self getSelf() {
        return self;
    }

    /**
     *
     * @param self
     * The self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

    /**
     *
     * @return
     * The activityInbox
     */
    public ActivityInbox getActivityInbox() {
        return activityInbox;
    }

    /**
     *
     * @param activityInbox
     * The activity-inbox
     */
    public void setActivityInbox(ActivityInbox activityInbox) {
        this.activityInbox = activityInbox;
    }

    /**
     *
     * @return
     * The activityOutbox
     */
    public ActivityOutbox getActivityOutbox() {
        return activityOutbox;
    }

    /**
     *
     * @param activityOutbox
     * The activity-outbox
     */
    public void setActivityOutbox(ActivityOutbox activityOutbox) {
        this.activityOutbox = activityOutbox;
    }

}