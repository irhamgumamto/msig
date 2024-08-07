package com.example.demo.helper;

import com.example.demo.exception.domain.AppIdErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppIdHelper {
    private static String appId;

    public static void verifyAppId(String currentAppId) {
        if (currentAppId == null || currentAppId.trim().isEmpty())
            throw new AppIdErrorException();

        if (!AppIdHelper.appId.equals(currentAppId))
            throw new AppIdErrorException();
    }

    @Value("${app.app-id}")
    public void setAppId(String appId) {
        AppIdHelper.appId = appId;
    }
}
