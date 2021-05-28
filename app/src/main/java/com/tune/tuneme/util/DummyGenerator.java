package com.tune.tuneme.util;

import com.tune.tuneme.data.DataModel;
import com.tune.tuneme.data.Followers;
import com.tune.tuneme.data.Notification;
import com.tune.tuneme.data.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * A provider of fake followers details for testing purposes only
 */
public class DummyGenerator {
    private static final int FOLLOWERS = 1;
    private static final int STORIES = 2;
    private static final int NOTIFICATIONS = 3;

    /**
     * @return the desired amount of dummy followers specified by <code>size</code>
     */
    public static List<Followers> getDummyFollowers(int size) {
        List<Followers> followers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            followers.add((Followers) Dummy.get(DummyGenerator.FOLLOWERS));
        }
        return followers;
    }

    /**
     * @return the desired amount of dummy followers specified by <code>size</code>
     */
    public static List<Story> getDummyStories(int size) {
        List<Story> stories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stories.add((Story) Dummy.get(DummyGenerator.STORIES));
        }
        return stories;
    }

    public static List<? extends Notification> getNotifications(int size) {
        List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            notifications.add((Notification) Dummy.get(DummyGenerator.NOTIFICATIONS));
        }
        return notifications;
    }

    // Dummies
    private static class Dummy {
        private static final String[] names =
                {"Beti Ayah", "Charies Myles", "Lynux Noah", "Charles Dhenn", "Augustus Lab"};

        private static final String[] messages
                = {
                "liked your comment", "replied to your comment", "liked your post",
                "mentioned you in a post", "replied to your post", "commented on your video"};

        private static final String[] uTime = {
                "1 hour ago", "4 hours ago", "2 seconds ago", "5 minutes ago"};

        // array indices
        private static int dn = 0;
        private static int dm = 0;
        private static int dt = 0;
        private static int dummyNum = 0;

        /**
         * @param dummyType the type of dummy to return
         * @return the required list of dummies
         */
        public static DataModel get(int dummyType) {
            switch (dummyType) {
                case FOLLOWERS:
                    return new Followers("@dummyUsername" + dummyNum, "Dummy " + dummyNum++, null);
                case STORIES:
                    return new Story();
                case NOTIFICATIONS:
                    int nIndex = dn == names.length ? dn = 0 : dn++;    // return 0 or inc
                    int mIndex = dm == messages.length ? dm = 0 : dm++; // return 0 or inc
                    int tIndex = dt == uTime.length ? dt = 0 : dt++;    // return 0 or inc
                    return new Notification(null, names[nIndex], messages[mIndex], uTime[tIndex]);
                default:
                    throw new IllegalStateException("Unexpected value: " + dummyType);
            }
        }

    }
}
