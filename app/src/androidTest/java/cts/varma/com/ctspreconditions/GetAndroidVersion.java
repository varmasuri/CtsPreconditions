package cts.varma.com.ctspreconditions;

/**
 * Created by asiahyd on 10/6/2016.
 */

public class GetAndroidVersion {
    public static int getApiVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }
}
