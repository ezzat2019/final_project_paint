package com.example.programmer.ordertasksjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    // todo aliaa t1
    // اعملى كذا اى حاجه
    private int x=20;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
    }

    private Paint getPaint(int color,float width ) {
        Paint p = new Paint();

                p.setStyle(Paint.Style.STROKE);

                p.setStrokeWidth(width);

                p.setAntiAlias(true);
                p.setFilterBitmap(true);

                p.setDither(true);
       p.setColor(color);
        return p;
    }

  /*  private Bitmap getBitmapFromImageView(Drawable mDrawable){
        Drawable drawable=mDrawable;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }*/

    public void choose(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri chosenImageUri = data.getData();


            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
               recognizeText(mBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
    private void recognizeText(Bitmap bitmap) {
        //تحويل بيتماب الى بيتماب قابل للتعديل
        final Bitmap mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        final Canvas canvas = new Canvas(mBitmap);
        //الحصول على الكود
        FirebaseVisionImage image  = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        // انتهى بنجاح
                        Paint redPaint = getPaint(Color.RED, .5f);
                        Paint blackPaint = getPaint(Color.BLACK, .5f);
                        Paint bluePaint = getPaint(Color.CYAN, .5f);
                        String text = "";

                        //تجلب البلوكس
                        for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                            //احدثيات النص
                            Rect boundingBox = block.getBoundingBox();
                            //Padding



                            boundingBox.top=boundingBox.top-5;
                            boundingBox.bottom=boundingBox.bottom+5;
                            //رسم مستطيل احمر حول النص
                            canvas.drawRect(boundingBox,redPaint);
                            //تجلب الاسطر داخل كل بلوك
                            for (FirebaseVisionText.Line line : block.getLines()) {
                                //اخد كل سطر و الانتقال للسطر التالي
                                text += line.getText()+"\n";
                                //Padding
                                line.getBoundingBox().top=line.getBoundingBox().top-2;
                                line.getBoundingBox().bottom=line.getBoundingBox().bottom+2;
                                line.getBoundingBox().right=line.getBoundingBox().right+2;
                                //رسم مستطيل اسود حول كل سطر
                                canvas.drawRect(line.getBoundingBox(), blackPaint);
                                //تجلب العناصر او الكلمات داخل كل سطر
                                for (FirebaseVisionText.Element element: line.getElements()) {
                                    //رسم مستطيل ازرق حول كل كلمة
                                    canvas.drawRect(element.getBoundingBox(), bluePaint);
                                }
                            }
                        }
                        //اضافة النص للعنصر
                        textView.setText(text);
                        //وضع الصورة على العنصر
                        imageView.setImageBitmap(mBitmap);


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // خطا ما وقع
                Toast.makeText(getApplicationContext(), "Sorry, something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
