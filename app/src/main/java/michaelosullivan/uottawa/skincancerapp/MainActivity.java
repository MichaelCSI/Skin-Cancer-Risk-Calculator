package michaelosullivan.uottawa.skincancerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.modeldownloader.CustomModel;
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions;
import com.google.firebase.ml.modeldownloader.DownloadType;
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader;

import org.tensorflow.lite.Interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CustomModelDownloadConditions conditions = new CustomModelDownloadConditions.Builder()
//                .requireWifi()
//                .build();
//        FirebaseModelDownloader.getInstance()
//                .getModel("your_model____", DownloadType.LATEST_MODEL, conditions)
//                .addOnSuccessListener(new OnSuccessListener<CustomModel>() {
//                    @Override
//                    public void onSuccess(CustomModel model) {
//                        // Download complete. Depending on your app, you could enable the ML
//                        // feature, or switch from the local model to the remote model, etc.
//
//                        // The CustomModel object contains the local path of the model file,
//                        // which you can use to instantiate a TensorFlow Lite interpreter.
//                        File modelFile = model.getFile();
//                        if (modelFile != null) {
//                            interpreter = new Interpreter(modelFile);
//                        }
//                    }
//                });
//        TextView output = findViewById(R.id.txtOutput);
//        Button btn = findViewById(R.id.btnTest);
//
//        ImageView imgView = findViewById(R.id.imageToLabel);
//        // assets folder image file name with extension
//        String fileName = "flower.jpg";
//        // get bitmap from assets folder
//        Bitmap bitmap = getBitmapFromAsset(this, fileName);
//
//
//        Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
//        ByteBuffer input = ByteBuffer.allocateDirect(224 * 224 * 3 * 4).order(ByteOrder.nativeOrder());
//        for (int y = 0; y < 224; y++) {
//            for (int x = 0; x < 224; x++) {
//                int px = bitmap.getPixel(x, y);
//
//                // Get channel values from the pixel value.
//                int r = Color.red(px);
//                int g = Color.green(px);
//                int b = Color.blue(px);
//
//                // Normalize channel values to [-1.0, 1.0]. This requirement depends
//                // on the model. For example, some models might require values to be
//                // normalized to the range [0.0, 1.0] instead.
//                float rf = (r - 127) / 255.0f;
//                float gf = (g - 127) / 255.0f;
//                float bf = (b - 127) / 255.0f;
//
//                input.putFloat(rf);
//                input.putFloat(gf);
//                input.putFloat(bf);
//            }
//        }
    }
    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }
        return bitmap;
    }
}