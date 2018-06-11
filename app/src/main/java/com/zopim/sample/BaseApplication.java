package com.zopim.sample;

import android.app.Application;
import android.util.Log;

import com.zendesk.logger.Logger;
import com.zendesk.util.StringUtils;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;

public class BaseApplication extends Application {



    /**
     * Account config needed to initialize {@link com.zopim.android.sdk.api.ZopimChatApi#init(String)}
     * <p/>
     * Account key can be found in Zopim Dashboard at the <a href="https://dashboard.zopim.com/#widget/getting_started">Getting Started Page</a>
     */
    private final static String ACCOUNT_KEY = ""; // NB: Replace this key with your Zopim account key

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable logging
        Logger.setLoggable(true);

        if (StringUtils.isEmpty(ACCOUNT_KEY)) {
            throw new IllegalStateException("No Account Key defined");
        }

        // Sample breadcrumb
        ZopimChat.trackEvent("Application created");

        /**
         * Minimum chat configuration. Chat must be initialization before starting the chat.
         */
        ZopimChat.init(ACCOUNT_KEY);
        // clear visitor info. Visitor info storage can be disabled at chat initialization
        VisitorInfo emptyVisitorInfo = new VisitorInfo.Builder().build();
        ZopimChat.setVisitorInfo(emptyVisitorInfo);
        Log.v("Zopim Chat Sample", "Visitor info erased.");
    }
}
