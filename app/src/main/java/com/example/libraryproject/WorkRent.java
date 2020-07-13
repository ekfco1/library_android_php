
package com.example.libraryproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * Created by 15U560 on 2016-10-25.
 */


public class WorkRent extends AppCompatActivity {

    private String memNo;
    private static String CLIENT_ID = "부여받은 Client Id";
    private static String CLIENT_PASSWORD = "부여받은 pwd";
    //String bookName, bookPrice, bookPubDate, authorName, publisherName;
   // int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_rent);
        new IntentIntegrator(this).initiateScan();

        int permssionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
         super.onActivityResult(requestCode, resultCode, data);
         IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
         // 결과값 출력
         Toast.makeText(this, "출력 ISBN:" + result.getContents(), Toast.LENGTH_SHORT).show();
         memNo = result.getContents();
         //레트로핏 구현해야함
             Intent intent = new Intent(getApplicationContext(),Rent.class);
             intent.putExtra("MEM_NO",memNo);
             startActivity(intent);

     }
}




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_work_rent);
//        new IntentIntegrator(this).initiateScan();
//    }
//
//
// protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//     // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
//     super.onActivityResult(requestCode, resultCode, data);
//     IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//     // 결과값 출력
//     Toast.makeText(this, "출력 ISBN:" + result.getContents(), Toast.LENGTH_SHORT).show();
//
// }
//
//}


/*package com.example.libraryproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WorkRent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_rent);
    }
}*/
