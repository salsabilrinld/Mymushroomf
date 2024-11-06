package com.example.mymushroomf;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {

    private Button btnNext;
    private TextView tvResendCode;
    private CountDownTimer countDownTimer;

    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4, otpDigit5, otpDigit6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        btnNext = findViewById(R.id.nextButton);
        tvResendCode = findViewById(R.id.tvResendCode);

        tvResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendOtpCode();
                startResendCodeTimer();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logika untuk mengecek OTP
            }
        });


        otpDigit1 = findViewById(R.id.otpDigit1);
        otpDigit2 = findViewById(R.id.otpDigit2);
        otpDigit3 = findViewById(R.id.otpDigit3);
        otpDigit4 = findViewById(R.id.otpDigit4);
        otpDigit5 = findViewById(R.id.otpDigit5);
        otpDigit6 = findViewById(R.id.otpDigit6);

        setupOTPInput();
    }

private void setupOTPInput() {
    otpDigit1.addTextChangedListener(new OTPTextWatcher(otpDigit1, otpDigit2));
    otpDigit2.addTextChangedListener(new OTPTextWatcher(otpDigit2, otpDigit3));
    otpDigit3.addTextChangedListener(new OTPTextWatcher(otpDigit3, otpDigit4));
    otpDigit4.addTextChangedListener(new OTPTextWatcher(otpDigit4, otpDigit5));
    otpDigit5.addTextChangedListener(new OTPTextWatcher(otpDigit5, otpDigit6));
    otpDigit6.addTextChangedListener(new OTPActivity.OTPTextWatcher(otpDigit6, null));
}

private class OTPTextWatcher implements TextWatcher {

    private final EditText currentEditText;
    private final EditText nextEditText;

    public OTPTextWatcher(EditText currentEditText, EditText nextEditText) {
        this.currentEditText = currentEditText;
        this.nextEditText = nextEditText;
    }


    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 1 && nextEditText != null) {
            nextEditText.requestFocus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}


    private void resendOtpCode() {
        Toast.makeText(this, "Kode OTP telah dikirim ulang.", Toast.LENGTH_SHORT).show();
        // Di sini Anda dapat menambahkan logika untuk mengirim ulang kode OTP
    }

    private void startResendCodeTimer() {
        tvResendCode.setEnabled(false);
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvResendCode.setText("Resend Code in " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvResendCode.setText("Resend Code");
                tvResendCode.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}

