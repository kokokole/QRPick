// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.ldcc.eleven.qrpick.qr.barcodescanning;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;


import com.ldcc.eleven.qrpick.activities.dataSetListener;
import com.ldcc.eleven.qrpick.qr.common.CameraImageGraphic;
import com.ldcc.eleven.qrpick.qr.common.FrameMetadata;
import com.ldcc.eleven.qrpick.qr.common.GraphicOverlay;
import com.ldcc.eleven.qrpick.qr.common.VisionProcessorBase;


import java.io.IOException;
import java.util.List;

/**
 * Barcode Detector Demo.
 */
public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

    private static final String TAG = "BarcodeScanProc";

    private final FirebaseVisionBarcodeDetector detector;

    private int state = -1; // 관리자인지 아닌지 확인하는 flag변수
    // 관리자 계정이면 flag 0, 아니면 1

    private dataSetListener dsl;

    /**
     * 바코드(QR코드) 감지 객체 생성
     */
    public BarcodeScanningProcessor(int state, dataSetListener dsl) {
        // Note that if you know which format of barcode your app is dealing with, detection will be
        // faster to specify the supported barcode formats one by one, e.g.
        // new FirebaseVisionBarcodeDetectorOptions.Builder()
        //     .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
        //     .build();


        this.state = state;
        this.dsl = dsl;


        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_QR_CODE)
                        .build();


        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);


//
//        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
//                .getVisionBarcodeDetector(options);


    }


    @Override
    public void stop() {
        try {
            detector.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
        }
    }

    @Override
    protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
        return detector.detectInImage(image);
    }

    /**
     * 감지 성공시 콜백
     * @param originalCameraImage hold the original image from camera, used to draw the background
     * @param barcodes
     * @param frameMetadata
     * @param graphicOverlay
     */
    @Override
    protected void onSuccess(
            @Nullable Bitmap originalCameraImage,
            @NonNull List<FirebaseVisionBarcode> barcodes,
            @NonNull FrameMetadata frameMetadata,
            @NonNull GraphicOverlay graphicOverlay) {
        graphicOverlay.clear();

        if (originalCameraImage != null) {
            CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
            graphicOverlay.add(imageGraphic);
        }


        // 코드 데이터 읽기
//        for (int i = 0; i < barcodes.size(); ++i) {
        if(barcodes.size() > 0) {
            FirebaseVisionBarcode barcode = barcodes.get(0);
             BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
             graphicOverlay.add(barcodeGraphic);  // 코드를 읽으면 그 내용을 화면에 추가
            Log.d("code read", barcode.getValueType() + " /// " + barcode.getRawValue());  // 코드 읽기

            dsl.setData(barcode.getRawValue());
        }

//        }
        graphicOverlay.postInvalidate();  // 코드를 읽으면 그 내용을 화면에 보여줌

    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Barcode detection failed " + e);
    }
}