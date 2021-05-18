package com.tune.tuneme.database;

import com.tune.tuneme.data.Followers;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TuneAPIClient {

    /**
     * @return a list of random followers that include both famous people and people on users
     * contact list that have accounts with TuneMe
     */
    public static List<Followers> getRandomFollowers() {
        return new ArrayList<>();
    }

    /**
     * Checks if a user already has an account
     *
     * @param meAccount the account to check its existence
     * @return true, if user already has an account
     */
    public static boolean checkExistingUser(TuneMeAccount meAccount) {
        return false;
    }

    /**
     * Register a new user to TuneMe if user haven't registered yet
     *
     * @param meAccount the user's account to send to the server
     * @return true, if operation was successful
     */
    public static boolean signUp(TuneMeAccount meAccount) {
        // stop registration if duplicate account was found
        return !checkExistingUser(meAccount);
    }

    /**
     *
     * @param meAccount the user's account to send to the server
     * @return true, if operation was successful
     */
    public static boolean login(TuneMeAccount meAccount) {
        // stop login if account isn't registered
        return checkExistingUser(meAccount);
    }

    public enum Options {

    }
}
