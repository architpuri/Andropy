package com.aptech.andropy.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aptech.andropy.R;

import java.io.File;

import okhttp3.MultipartBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created By  Archit
 * on 11/13/2019
 * for Andropy
 */
public class CommonUtils {
    public static int fileTypeSelection = 0;
    private static String timeAndDate;
    private static String timeAndDateFormat;
    private static String TAG = "CommonUtils JAVA";
    private static String message = "Not Set";

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    public Bitmap addImageUsingAlertBuilder(Context context) {
        return null;
    }
/*
    public static void selectFileTypeDialog(Context context) {
        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_file_type);
        Button btnImage = dialog.findViewById(R.id.btn_camera_fileType);
        Button btnPdf = dialog.findViewById(R.id.btn_pdf_fileType);
        Button btnVideo = dialog.findViewById(R.id.btn_video_fileType);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileTypeSelection = UiConstants.SELECT_IMAGE;
            }
        });
        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileTypeSelection = UiConstants.SELECT_FILE;
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileTypeSelection = UiConstants.SELECT_VIDEO;
            }
        });
        dialog.show();
    }*/

    //Use with Select File Type Dialog function
    public static int getFileTypeSelection() {
        return fileTypeSelection;
    }

    public static MultipartBody.Part getFilePartFromResult(int requestCode, int resultCode, Intent data, Context context) {
        MultipartBody.Part filePart = null;
        if (resultCode == RESULT_OK && requestCode == UiConstants.SELECT_FILE) {
            if (data.hasExtra("filePath")) {
                Toast.makeText(context, data.getExtras().getString("filePath"),
                        Toast.LENGTH_SHORT).show();
                filePart = Common.uploadFile(data.getExtras().getString("filePath"),
                        UiConstants.FILETYPE_PDF);
            }
        } else if (resultCode == RESULT_OK && requestCode == UiConstants.SELECT_IMAGE) {
            if (data.hasExtra("imagePath")) {
                Toast.makeText(context, data.getExtras().getString("imagePath"),
                        Toast.LENGTH_SHORT).show();
                filePart = Common.uploadFile(data.getExtras().getString("imagePath"),
                        UiConstants.FILETYPE_IMAGE);
            }
        } else if (resultCode == RESULT_OK && requestCode == UiConstants.SELECT_VIDEO) {
            if (data.hasExtra("videoPath")) {
                Toast.makeText(context, data.getExtras().getString("videoPath"),
                        Toast.LENGTH_SHORT).show();
                filePart = Common.uploadFile(data.getExtras().getString("videoPath"),
                        UiConstants.FILETYPE_VIDEO);

            }
        }
        return filePart;
    }

    public static File getFileFromPath(String path) {
        File file = new File(path);
        return file;
    }

}
