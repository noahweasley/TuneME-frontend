package com.tune.tuneme.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Set of utility methods used to produce better images in device memory
 */
public class ImageUtils {
    public static final String TAG = "ImageUtils";

    /**
     * @param res   the app resource
     * @param resId the resource Id of the image
     * @return the sub-sampled image based on the size specified
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {
        final int LDPI_LOWER = 320;
        final int LDPI_HIGHER = 400;
        final int XXHDPI = 560;
        final int MDPI = 480;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        int inSampleSize;
        // sub-sample image according to device density
        int density = res.getDisplayMetrics().densityDpi;

        Log.d(TAG, "Density: " + density + " dpi");

        if (density <= LDPI_LOWER) {
            inSampleSize = 5;
        } else if (density <= LDPI_HIGHER && density > LDPI_LOWER) {
            inSampleSize = 4;
        } else if (density <= MDPI && density < XXHDPI) {
            inSampleSize = 3;
        } else inSampleSize = 2;
        options.inSampleSize = inSampleSize;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

}
