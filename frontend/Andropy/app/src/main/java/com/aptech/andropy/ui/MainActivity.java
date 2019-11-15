package com.aptech.andropy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aptech.andropy.R;
import com.aptech.andropy.common.Common;
import com.aptech.andropy.common.UiConstants;
import com.aptech.andropy.data.AndropyService;
import com.aptech.andropy.data.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_clickImage)
    Button btnClickImage;
    @BindView(R.id.txt_type)
    TextView txtType;
    @BindView(R.id.progress_main)
    ProgressBar progressMain;
    private MultipartBody.Part profileImgPart = null;

    private final String TAG = "MAIN ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressMain.setVisibility(View.GONE);
        btnClickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class), UiConstants.SELECT_PROFILE_IMAGE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UiConstants.SELECT_PROFILE_IMAGE) {
            if (data.hasExtra("imagePath")) {
                Toast.makeText(this, data.getExtras().getString("imagePath"),
                        Toast.LENGTH_SHORT).show();
                //pBarUserProfile.setVisibility(View.VISIBLE);
                profileImgPart = Common.uploadFile(data.getExtras().getString("imagePath"),
                        UiConstants.FILETYPE_IMAGE);
                Log.d(TAG, "Activity Result-profile inner if");
                progressMain.setVisibility(View.VISIBLE);
                sendProfileImage();
            }
            Log.d(TAG, "Activity Result - profile if");
        } else {
            Log.d(TAG, "Else hai Activity Result bahr");
        }
        Log.d(TAG, "Activity Result bahr");
    }

    private void sendProfileImage() {
        AndropyService andropyService = ApiClient.getInstance();
        Call<String> getResult = andropyService.getValue(profileImgPart);
        getResult.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    txtType.setText(response.body());
                    progressMain.setVisibility(View.GONE);
                } else {
                    txtType.setText("Not Success");
                    progressMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                txtType.setText("Failure");
                progressMain.setVisibility(View.GONE);
            }
        });
    }
}
