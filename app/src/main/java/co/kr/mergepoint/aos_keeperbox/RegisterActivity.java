package co.kr.mergepoint.aos_keeperbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

    private void   addItemToSheet(Context context, String userName, String userId, String userPw, String userPhoneNumber) {
        final String name=userName;
        final String id=userId;
        final String pw=userPw;
        final String phoneNumber=userPhoneNumber;
        loading =  ProgressDialog.show(context,"Loading","please wait",false,true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/웹앱으로배포한url/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(this,response,Toast.LENGTH_SHORT).show();
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params

                parmas.put("name",name);
                parmas.put("id",id);
                parmas.put("pw",pw);
                parmas.put("phone",phoneNumber);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}


}
