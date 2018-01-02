package cts.varma.com.ctspreconditions;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CtsPresettings {
    private static final long TIMEOUT = 2000;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String screenUnlock = "input keyevent KEYCODE_MENU";
    private String openDisplaySettings = "am start -n com.android.settings/.DisplaySettings";
    private String openSettings = "am start -n com.android.settings/.Settings";
    private String openDeveloperOptions = "am start -n com.android.settings/.DevelopmentSettings";
    private String forceClose = "am kill-all";
    private String ctsverifier = "am start -n com.android.cts.verifier/.CtsVerifierActivity";
    public int version = GetAndroidVersion.getApiVersion();

    private UiDevice mDevice;

    @Before
    public void setUp() {

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();

    }

    @Test
    public void preSettings() throws Exception {

        screenUnlocking();
        displaySettings();
        locationSettings();
        securitySettings();
 //       mDevice.wait();
        dateTimeSettings();
  //      mDevice.wait();
        developerOptionsSettings();

    }
    @Test
    public void screenUnlocking() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cts.varma.com.ctspreconditions", appContext.getPackageName());

        if (mDevice.isScreenOn() == true) {
            Log.d("MyActivity", "*****************Screen is ON*****************");
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(screenUnlock);
        } else {
            Log.d("MyActivity", "*****************Screen is not ON*****************");
            mDevice.pressHome();
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(screenUnlock);
        }
    }

    @Test
    public void displaySettings() throws Exception {

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openDisplaySettings);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        if (mDevice.isScreenOn() == true) {

            screenUnlocking();
            if (version == 26) {
                UiObject advanced = mDevice.findObject(new UiSelector().text("Advanced"));
                advanced.click();
            }

            UiObject sleep = mDevice.findObject(new UiSelector().text("Sleep"));
            sleep.click();

            UiObject timeDuration = mDevice.findObject(new UiSelector().text("30 minutes"));
            timeDuration.click();
        } else {
            screenUnlocking();

            UiObject sleep = mDevice.findObject(new UiSelector().text("Sleep"));
            sleep.click();

            UiObject timeDuration = mDevice.findObject(new UiSelector().text("30 minutes"));
            timeDuration.click();
        }

        System.out.println("**********Display Settings Set to Maximum**********");

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
    }

    @Test
    public void locationSettings() throws UiObjectNotFoundException  {


        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openSettings);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        if(version == 21) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Location"));
            UiObject location = mDevice.findObject(new UiSelector().text("Location"));
            location.click();

            UiObject mode = mDevice.findObject(new UiSelector().text("Mode"));
            mode.click();

            UiObject high = mDevice.findObject(new UiSelector().text("High accuracy"));
            high.click();

            mDevice.pressHome();

            System.out.println("**********Location Settings Done Successfully on L 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);


        } else if (version == 22) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Location"));
            UiObject location = mDevice.findObject(new UiSelector().text("Location"));
            location.click();

            UiObject mode = mDevice.findObject(new UiSelector().text("Mode"));
            mode.click();

            UiObject high = mDevice.findObject(new UiSelector().text("High accuracy"));
            high.click();

            UiObject agree = mDevice.findObject(new UiSelector().text("Agree"));
            if (agree.exists()) {
                agree.click();
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Location Settings Done Successfully Lollipop 5.1**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);



        } else if (version == 23) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Location"));
            UiObject location = mDevice.findObject(new UiSelector().text("Location"));
            location.click();

            UiObject mode = mDevice.findObject(new UiSelector().text("Mode"));
            mode.click();

            UiObject high = mDevice.findObject(new UiSelector().text("High accuracy"));
            high.click();

            UiObject agree = mDevice.findObject(new UiSelector().text("Agree"));
            if (agree.exists()) {
                agree.click();
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Location Settings Done Successfully Marshmallow**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);


        } else if (version == 24 || version == 25) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Location"));

            UiObject location = mDevice.findObject(new UiSelector().text("Location"));
            location.click();

            UiObject mode = mDevice.findObject(new UiSelector().text("Mode"));
            mode.click();

            UiObject high = mDevice.findObject(new UiSelector().text("High accuracy"));
            high.click();

            UiObject agree = mDevice.findObject(new UiSelector().text("AGREE"));
            if (agree.exists()) {
                agree.clickAndWaitForNewWindow();

                UiObject yes = mDevice.findObject(new UiSelector().className("android.widget.Button").text("Yes"));
                if (yes.exists()) {
                    yes.click();
                } else {
                    mDevice.pressBack();
                }
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Location Settings Done Successfully on NOUGAT**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version >= 26) {

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security & Location"));

            UiObject screen_lock = mDevice.findObject(new UiSelector().text("Screen lock"));
            screen_lock.click();
            UiObject none = mDevice.findObject(new UiSelector().text("None"));
            none.click();

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Location"));

            UiObject location = mDevice.findObject(new UiSelector().text("Location"));
            location.click();

            UiObject mode_sel = mDevice.findObject(new UiSelector().text("Mode"));
            mode_sel.click();

            UiObject location_mode = mDevice.findObject(new UiSelector().text("High accuracy"));
            location_mode.click();

            UiObject agree = mDevice.findObject(new UiSelector().text("AGREE"));
            if (agree.exists()) {
                agree.clickAndWaitForNewWindow();

                UiObject yes = mDevice.findObject(new UiSelector().className("android.widget.Button").text("YES"));
                if (yes.exists()) {
                    yes.click();
                } else {
                    mDevice.pressBack();
                }
            } else {
                mDevice.pressHome();
            }

        }
    }

    @Test
    public void securityOptions() throws UiObjectNotFoundException, Exception {
        screenUnlocking();
        screenUnlocking();
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openSettings);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);
        if(version == 21) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject unknownSources = mDevice.findObject(new UiSelector().text("Unknown sources"));
            unknownSources.clickAndWaitForNewWindow();

            UiObject ok = mDevice.findObject(new UiSelector().text("OK"));
            if (ok.exists()) {
                ok.clickAndWaitForNewWindow();
                    mDevice.pressBack();
                }
            else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version == 22) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject unknownSources = mDevice.findObject(new UiSelector().text("Unknown sources"));
            unknownSources.clickAndWaitForNewWindow();

            UiObject ok = mDevice.findObject(new UiSelector().text("OK"));
            if (ok.exists()) {
                ok.clickAndWaitForNewWindow();
                mDevice.pressBack();
            }
            else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version == 23) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject unknownSources = mDevice.findObject(new UiSelector().text("Unknown sources"));
            unknownSources.clickAndWaitForNewWindow();

            UiObject ok = mDevice.findObject(new UiSelector().text("OK"));
            if (ok.exists()) {
                ok.clickAndWaitForNewWindow();
                mDevice.pressBack();
            }
            else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version >= 24) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject unknownSources = mDevice.findObject(new UiSelector().text("Unknown sources"));
            unknownSources.clickAndWaitForNewWindow();

            UiObject ok = mDevice.findObject(new UiSelector().text("OK"));
            if (ok.exists()) {
                ok.clickAndWaitForNewWindow();
                mDevice.pressBack();
            }
            else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
        }

    }

    @Test
    public void securitySettings() throws UiObjectNotFoundException {

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openSettings);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        if(version == 21) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject screenlockoption = mDevice.findObject(new UiSelector().text("Screen lock"));
            screenlockoption.clickAndWaitForNewWindow();

            UiObject noneoption = mDevice.findObject(new UiSelector().text("None"));
            noneoption.click();

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Device administrators"));

            UiObject deviceAdmin = mDevice.findObject(new UiSelector().text("Device administrators"));
            deviceAdmin.clickAndWaitForNewWindow();

            UiObject deviceAdminReceiver = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver"));
            if (deviceAdminReceiver.exists()) {
                deviceAdminReceiver.clickAndWaitForNewWindow();

                UiObject activate = mDevice.findObject(new UiSelector().text("Activate"));
                activate.click();

                UiObject deviceAdminReceiver2 = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver2"));
                deviceAdminReceiver2.clickAndWaitForNewWindow();
                activate.click();
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.0**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version == 22) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject screenlockoption = mDevice.findObject(new UiSelector().text("Screen lock"));
            screenlockoption.clickAndWaitForNewWindow();

            UiObject noneoption = mDevice.findObject(new UiSelector().text("None"));
            noneoption.click();

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Device administrators"));
            UiObject deviceAdmin = mDevice.findObject(new UiSelector().text("Device administrators"));
            deviceAdmin.clickAndWaitForNewWindow();

            UiObject deviceAdminReceiver = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver"));
            if (deviceAdminReceiver.exists()) {
                deviceAdminReceiver.clickAndWaitForNewWindow();

                UiObject activate = mDevice.findObject(new UiSelector().text("Activate"));
                activate.click();

                UiObject deviceAdminReceiver2 = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver2"));
                deviceAdminReceiver2.clickAndWaitForNewWindow();
                activate.click();
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on LOLLIPOP 5.1**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version == 23) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject screenlockoption = mDevice.findObject(new UiSelector().text("Screen lock"));
            screenlockoption.clickAndWaitForNewWindow();

            UiObject noneoption = mDevice.findObject(new UiSelector().text("None"));
            noneoption.click();

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Device administrators"));
            UiObject deviceAdmin = mDevice.findObject(new UiSelector().text("Device administrators"));
            deviceAdmin.clickAndWaitForNewWindow();

            UiObject deviceAdminReceiver = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver"));
            if (deviceAdminReceiver.exists()) {
                deviceAdminReceiver.clickAndWaitForNewWindow();

                UiObject activate = mDevice.findObject(new UiSelector().text("Activate"));
                activate.click();

                UiObject deviceAdminReceiver2 = mDevice.findObject(new UiSelector().text("android.deviceadmin.cts.CtsDeviceAdminReceiver2"));
                deviceAdminReceiver2.clickAndWaitForNewWindow();
                activate.click();
            } else {
                mDevice.pressHome();
            }

            System.out.println("**********Security and DeviceAdmin Settings Done Successfully on MARSHMALLOW**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

        } else if (version >= 24) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Security"));

            UiObject security = mDevice.findObject(new UiSelector().text("Security"));
            security.clickAndWaitForNewWindow();

            UiObject screenlockoption = mDevice.findObject(new UiSelector().text("Screen lock"));
            screenlockoption.clickAndWaitForNewWindow();

            UiObject noneoption = mDevice.findObject(new UiSelector().text("None"));
            noneoption.click();

            System.out.println("**********Security Settings Done Successfully on NOUGAT**********");

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
        }
    }

    @Test
    public void dateTimeSettings() throws UiObjectNotFoundException {

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openSettings);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Date & time"));
        UiObject dateTime = mDevice.findObject(new UiSelector().text("Date & time"));
        dateTime.clickAndWaitForNewWindow();

        UiObject autoTimeZone = mDevice.findObject(new UiSelector().text("Automatic time zone"));
        autoTimeZone.click();

        UiObject selectTimeZone = mDevice.findObject(new UiSelector().text("Select time zone"));
        selectTimeZone.clickAndWaitForNewWindow();


        new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Kolkata"));
        UiObject kolkata = mDevice.findObject(new UiSelector().text("Kolkata"));
        kolkata.clickAndWaitForNewWindow();

        autoTimeZone.click();

        System.out.println("-----------------------Date and Time Settings Done Successfully-----------------");

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
    }
    @Test
    public void developerOptionsVerifyApps() throws UiObjectNotFoundException, Exception {
        screenUnlocking();
        screenUnlocking();
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openDeveloperOptions);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        if(version == 21) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
                      verifyAppsOverUsb.click();
                      InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on LOLLIPOP 5.0**********");

        } else if (version == 22) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));

                verifyAppsOverUsb.click();
                InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

            System.out.println("**********Developer Options Settings Done Successfully on LOLLIPOP 5.1**********");

        } else if (version == 23) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));

                verifyAppsOverUsb.click();
                InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);

            System.out.println("**********Developer Options Settings Done Successfully on MARSHMALLOW**********");
        } else if (version >= 24) {
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));

                   verifyAppsOverUsb.click();
                   InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on NOUGAT**********");
            }

        }



    @Test
    public void developerOptionsSettings() throws UiObjectNotFoundException {

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(openDeveloperOptions);
        mDevice.waitForWindowUpdate("com.android.settings", 5000);

        UiObject stayAwake = mDevice.findObject(new UiSelector().text("Stay awake"));
        UiObject allowMockLocations = mDevice.findObject(new UiSelector().text("Allow mock locations"));


        if(version == 21) {
            stayAwake.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Allow mock locations"));
            allowMockLocations.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
            verifyAppsOverUsb.click();
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on LOLLIPOP 5.0**********");
        } else if (version == 22) {
            stayAwake.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Allow mock locations"));
            allowMockLocations.click();

            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
            verifyAppsOverUsb.click();

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on LOLLIPOP 5.1**********");

        } else if (version == 23) {
            stayAwake.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
            verifyAppsOverUsb.click();

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on MARSHMALLOW**********");
        } else if (version == 24 || version == 25) {
            stayAwake.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
            verifyAppsOverUsb.click();

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on NOUGAT**********");
        } else if (version == 26) {
            stayAwake.click();
            new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("Verify apps over USB"));
            UiObject verifyAppsOverUsb = mDevice.findObject(new UiSelector().text("Verify apps over USB"));
            verifyAppsOverUsb.click();

            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(forceClose);
            System.out.println("**********Developer Options Settings Done Successfully on O**********");

        }
    }


    @After
    public void tearDown() {

        mDevice.pressHome();

    }
}
