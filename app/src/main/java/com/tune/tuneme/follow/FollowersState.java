package com.tune.tuneme.follow;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class FollowersState implements State {
    public static final String ARG_STATES = "Choice states";
    public static final String ARG_ID_INDICES = "Id Indices";
    private boolean loggingEnabled = false;
    public static final String TAG = "FollowersState";

    // States to be saved
    private List<Integer> indices = new ArrayList<>();
    private ParcelableSparseBooleanArray sbarr = new ParcelableSparseBooleanArray();

    public FollowersState() {
    }

    /**
     * use this constructor if logging states is required for testing otherwise use the default one
     * or set <code>loggingEnabled</code> to false
     *
     * @param loggingEnabled if states should be logged to the console
     */
    public FollowersState(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    @Override
    public void setChecked(int position, boolean checked) {
        if (!checked) {
            sbarr.delete(position);
            indices.remove(Integer.valueOf(position));
        } else {
            sbarr.put(position, true);
            indices.add(position);
        }
        if (loggingEnabled) Log.d(TAG, this.toString());
    }


    @Override
    public boolean isChecked(int position) {
        return sbarr.get(position);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(ARG_STATES, sbarr);
        bundle.putIntegerArrayList(ARG_ID_INDICES, (ArrayList<Integer>) indices);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        sbarr = bundle.getParcelable(ARG_STATES);
        indices = bundle.getIntegerArrayList(ARG_ID_INDICES);
    }

    @Override
    public Integer[] getCheckedChoicesIndices() {
        return indices.toArray(new Integer[0]);
    }

    void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" + "indices=" + indices + ", \n sbarr=" + sbarr + '}';
    }
}
