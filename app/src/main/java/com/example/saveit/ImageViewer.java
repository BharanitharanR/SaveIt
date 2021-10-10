package com.example.saveit;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;
import java.io.IOException;

public class ImageViewer  extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer_activity);
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("fileName");
        Button goBackToMainMenu = (Button) findViewById(R.id.backInImageViewer);
        ImageView image = (ImageView) findViewById(R.id.imageViewer);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "saveIt");
        File fileURI = new File(mediaStorageDir.getPath() + File.separator +
                fileName);
        Log.e(("Image"),fileURI.getName());
        image.setImageURI(Uri.fromFile(fileURI));
        Matrix matrix = new Matrix();
        image.setScaleType(ImageView.ScaleType.MATRIX); //required
        matrix.postRotate((float) 90, image.getDrawable().getBounds().width()/2,    image.getDrawable().getBounds().height()/2);
        image.setImageMatrix(matrix);


        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        InputImage imageDt=null;
        Task<Text> result = null;
        try {
            imageDt = InputImage.fromFilePath(this.getApplicationContext(), Uri.fromFile(fileURI));


             result =
                    recognizer.process(imageDt)
                            .addOnSuccessListener(new OnSuccessListener<Text>() {
                                @Override
                                public void onSuccess(Text visionText) {
                                    // Task completed successfully
                                    // ...
                                    for (Text.TextBlock block : visionText.getTextBlocks()) {
                                        Rect boundingBox = block.getBoundingBox();
                                        Point[] cornerPoints = block.getCornerPoints();
                                        String text = block.getText();

                                        for (Text.Line line: block.getLines()) {
                                            // ...
                                            for (Text.Element element: line.getElements()) {
                                                // ...
                                                String dataElement = element.getText();
                                                Log.e("element!!!",dataElement);
                                            }
                                        }
                                    }

                                   // getTextData(result);
                                }
                            })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Task failed with an exception
                                            // ...
                                        }
                                    });


        } catch (IOException e) {
            e.printStackTrace();
        }




        goBackToMainMenu.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                // get back to main Image
                                                sendMessage();
                                            }
                                        }
        );

    }



    public void sendMessage()
    {

        Log.e("Call SendMessgae","Send");
        android.content.Intent intent = new android.content.Intent(ImageViewer.this, MainActivity.class);
        startActivity(intent);
    }
}
