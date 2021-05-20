package com.tune.tuneme.follow;

import android.os.Bundle;

@SuppressWarnings("unused")
public interface State {

    /**
     * used this to persist the state of the followed user. If <code>checked</code> is false, then
     * any previous entry (when <code>check</code> was true) in the state, would be erased,
     * as it is not useful.
     *
     * @param position the position in which the its following state is to be inserted
     * @param check    the status of the followed user
     */
    void setChecked(int position, boolean check);

    /**
     * @param position the position of the item
     * @return the following status of an suggested user in the list
     */
    boolean isChecked(int position);

    /**
     * Call this when saving apps state passing a valid bundle as the parameter, or else
     * checked follower's state won't be saved at all.
     * Use with onSaveInstanceState.
     *
     * @param bundle the bundle in which state would be saved
     */
    void onSaveInstanceState(Bundle bundle);

    /**
     * Call this when restoring apps state passing a valid bundle as the parameter, or else
     *checked follower's state won't be retrieved at all.
     * Use with onRestoreInstanceState.
     *
     * @param bundle the bundle in which state would be saved
     */
    void onRestoreInstanceState(Bundle bundle);

    /**
     * @return the position or index of all the followed suggested users in the list
     */
    Integer[] getCheckedChoicesIndices();

}
