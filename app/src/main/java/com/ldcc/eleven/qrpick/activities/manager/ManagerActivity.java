package com.ldcc.eleven.qrpick.activities.manager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.activities.dataSetListener;
import com.ldcc.eleven.qrpick.qr.barcodescanning.BarcodeScanningProcessor;
import com.ldcc.eleven.qrpick.qr.common.CameraSource;
import com.ldcc.eleven.qrpick.qr.common.CameraSourcePreview;
import com.ldcc.eleven.qrpick.qr.common.GraphicOverlay;
import com.ldcc.eleven.qrpick.util.vo.Qr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerActivity extends AppCompatActivity implements dataSetListener {
    private static final String TAG = "ManagerActivity";
    private static final String FACE_CONTOUR = "Face Contour";
    private static final int PERMISSION_REQUESTS = 1;


    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private String selectedModel = FACE_CONTOUR;
    private String qrData = null;


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "pause");
        preview.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
        Log.d(TAG, "destroy");
    }


    private void startCameraSource() {
        if (cameraSource != null) {
            try {
                if (preview == null) {
                    Log.d(TAG, "resume: Preview is null");
                }
                if (graphicOverlay == null) {
                    Log.d(TAG, "resume: graphOverlay is null");
                }
                Log.d(TAG, "startCameraSource");
                preview.start(cameraSource, graphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    // 권한 체크
    private boolean allPermissionsGranted() {
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                return false;
            }
        }
        return true;
    }

    private String[] getRequiredPermissions() {
        try {
            PackageInfo info =
                    this.getPackageManager()
                            .getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] ps = info.requestedPermissions;
            if (ps != null && ps.length > 0) {
                return ps;
            } else {
                return new String[0];
            }
        } catch (Exception e) {
            return new String[0];
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        Log.i(TAG, "Permission granted!");
        if (allPermissionsGranted()) {
            createCameraSource(selectedModel);


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private static boolean isPermissionGranted(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission granted: " + permission);
            return true;
        }
        Log.i(TAG, "Permission NOT granted: " + permission);
        return false;
    }


    private void getRuntimePermissions() {
        List<String> allNeededPermissions = new ArrayList<>();
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                allNeededPermissions.add(permission);
            }
        }

        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this, allNeededPermissions.toArray(new String[0]), PERMISSION_REQUESTS);
        }
    }

    //권한체크 끝


    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }


        Log.i(TAG, "Using Barcode Detector Processor");
        cameraSource.setMachineLearningFrameProcessor(new BarcodeScanningProcessor(0, this));
        Log.d(TAG,"endendend");

    }


    int go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Intent intent = getIntent();

        go = intent.getIntExtra("go", 0);


        preview = (CameraSourcePreview) findViewById(R.id.firePreview);

        if (preview == null) {
            Log.d(TAG, "Preview is null");
        }
        graphicOverlay = (GraphicOverlay) findViewById(R.id.fireFaceOverlay);
        if (graphicOverlay == null) {
            Log.d(TAG, "graphicOverlay is null");
        }


        if (allPermissionsGranted()) {
            createCameraSource(selectedModel);
        } else {
            getRuntimePermissions();
        }



    }
    String result;
    @Override
    public void setData(String data) {
        qrData = data;
        if(go == 0)
            startActivity(new Intent(getApplicationContext(), MnglistActivity.class).putExtra("data", qrData));
        else{  // 등록버튼을 눌렀을 때
            Gson gson = new Gson();
            final Qr qr = gson.fromJson(qrData, Qr.class);


            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            StringRequest request = new StringRequest(Request.Method.POST, "http://18.223.57.133:3000"+"/item/delete",
                    //요청 성공 시
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("result", "[" + response + "]");
                            result = response;
                        }
                    },
                    // 에러 발생 시
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("error", "[" + error.getMessage() + "]");
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", qr.getId());
                    return params;
                }
            };

            queue.add(request);
            startActivity(new Intent(getApplicationContext(), MenudetailActivity.class).putExtra("data", result));
        }
    }
}
