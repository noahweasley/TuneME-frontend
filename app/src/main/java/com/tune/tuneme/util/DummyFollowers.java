package com.tune.tuneme.util;

import com.tune.tuneme.data.Followers;

import java.util.ArrayList;
import java.util.List;

/**
 * A provider of fake followers details for testing purposes only
 */
public class DummyFollowers {

    /**
     * @return the desired amount of dummy followers specified by <code>size</code>
     */
    public static List<Followers> initialize(int size) {
        List<Followers> followers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            followers.add(Dummy.get());
        }
        return followers;
    }

    // dummy followers
    private static class Dummy {
        private static int dummyNum = 0;

        public static Followers get() {
            return new Followers("@dummyUsername" + dummyNum, "Dummy " + dummyNum++, null);
        }
    }
}
