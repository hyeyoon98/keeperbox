package co.kr.mergepoint.aos_keeperbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import java.util.Map;

import co.kr.mergepoint.aos_keeperbox.databinding.ActivityRegisterBinding;

/**
 * Created by Alicia on 2020/10/06.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {


    ActivityRegisterBinding binding;
    String checkPass, pass;
    boolean isCheckId = false;
    boolean isCheckPw = false;
    boolean isCheckNum = false;

    ProgressDialog loading;

    String name = binding.insertName.getText().toString();
    String id = binding.insertId.getText().toString();
    String pw = binding.insertPassword.getText().toString();
    String phoneNumber = binding.insertPhone.getText().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setOnClick(this);
        binding.setTitle("회원가입");
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.register_button) {
            addItemToSheet(this, name, id, pw, phoneNumber);
            Intent intent = new Intent(this, RegisterFingerPrintActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ic_back_button) {
            finish();
        }
    }

    /*구글 스프레드 시트 서버 통산*/
    private class HttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... strings) {
            try {
                HttpPost

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

//https://script.google.com/macros/s/AKfycbxBpBsqs7ZHvgwqGcBN7L93mkevbRcC88_FJt0H6oPTC7JdCve1/exec
}
