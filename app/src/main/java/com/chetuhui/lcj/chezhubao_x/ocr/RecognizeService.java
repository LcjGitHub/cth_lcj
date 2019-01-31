/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.chetuhui.lcj.chezhubao_x.ocr;

import android.content.Context;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.OcrRequestParams;
import com.baidu.ocr.sdk.model.OcrResponseResult;

import java.io.File;

/**
 * Created by ruanshimin on 2017/4/20.
 */

public class RecognizeService {

    public interface ServiceListener {
        public void onResult(String result);
    }

    public static void recVehicleLicense(Context ctx, String filePath, final ServiceListener listener) {
        OcrRequestParams param = new OcrRequestParams();
        param.setImageFile(new File(filePath));
        OCR.getInstance(ctx).recognizeVehicleLicense(param, new OnResultListener<OcrResponseResult>() {
            @Override
            public void onResult(OcrResponseResult result) {
                listener.onResult(result.getJsonRes());
            }

            @Override
            public void onError(OCRError error) {
                listener.onResult(error.getMessage());
            }
        });
    }


}
