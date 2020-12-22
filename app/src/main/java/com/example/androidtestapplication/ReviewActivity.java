package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

public class ReviewActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=HTeKeMyeWts&ab_channel=TheEasyLearnAndroid

    private ReviewManager manager;
    private ReviewInfo reviewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    reviewInfo = task.getResult();
                    Toast.makeText(ReviewActivity.this, "Now review the system", Toast.LENGTH_SHORT).show();

                } else {
                    // There was some problem, continue regardless of the result.
                    Toast.makeText(ReviewActivity.this, "There is some problem.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(reviewInfo != null){
            Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                    if (task.isSuccessful()){
                        Toast.makeText(ReviewActivity.this, "Review successful", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ReviewActivity.this, "Review error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else {
            super.onBackPressed();
        }
    }
}