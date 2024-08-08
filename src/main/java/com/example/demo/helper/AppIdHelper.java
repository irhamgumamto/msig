package com.example.demo.helper;

import com.example.demo.exception.domain.AppIdErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.example.demo.constant.ExceptionConstant.APPID_ERROR_MSG;

@Component
public class AppIdHelper {
    private static String appId;

    public static void verifyAppId(String currentAppId) {
        if (currentAppId == null || currentAppId.trim().isEmpty())
            throw new AppIdErrorException(APPID_ERROR_MSG);

        if (!AppIdHelper.appId.equals(currentAppId))
            throw new AppIdErrorException(APPID_ERROR_MSG);
    }

    @Value("${app.app-id}")
    public void setAppId(String appId) {
        AppIdHelper.appId = appId;
    }
}
