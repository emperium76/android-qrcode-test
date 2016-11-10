package tommy.com.qrcodereadertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button qrcodeBtn = (Button) findViewById(R.id.qrcodeBtn);
        if (qrcodeBtn != null) {
            qrcodeBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan a QR Code");
                    //integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setOrientationLocked(false);
                    integrator.setBeepEnabled(true);
                    integrator.setBarcodeImageEnabled(true);
                    integrator.initiateScan();
                }
            });
        }

        final Button barcodeBtn = (Button) findViewById(R.id.barcodeBtn);
        barcodeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan a Barcode");
                //integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });

        final Button csBtn = (Button) findViewById(R.id.csBtn);
        csBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, ContinuousCaptureActivity.class);
                startActivity(i);
            }
        });

        final Button csBtn2 = (Button) findViewById(R.id.csBtn2);
        csBtn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setCaptureActivity(CustomScannerActivity.class);
                integrator.initiateScan();
            }
        });
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
