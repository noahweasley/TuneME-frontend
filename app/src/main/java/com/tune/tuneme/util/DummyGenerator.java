package com.tune.tuneme.util;

import com.tune.tuneme.data.DataModel;
import com.tune.tuneme.data.Followers;
import com.tune.tuneme.data.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * A provider of fake followers details for testing purposes only
 */
public class DummyGenerator {
    private static final int FOLLOWERS = 1;
    private static final int STORIES = 2;

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
    public static List<Story> getDummyStory(int size) {
        List<Story> stories = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stories.add((Story) Dummy.get(DummyGenerator.STORIES));
        }
        return stories;
    }

    // dummy followers
    private static class Dummy {
        private static int dummyNum = 0;

        public static DataModel get(int dummyType) {
            switch (dummyType) {
                case FOLLOWERS:
                    return new Followers("@dummyUsername" + dummyNum, "Dummy " + dummyNum++, null);
                case STORIES:
                    return new Story();
                default:
                    throw new IllegalStateException("Unexpected value: " + dummyType);
            }
        }
    }
}
