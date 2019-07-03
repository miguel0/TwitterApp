package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    EditText etBody;
    Button btTweet;
    RestClient client;
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etBody = findViewById(R.id.etBody);
        btTweet = findViewById(R.id.btTweet);
        tvCount = findViewById(R.id.tvCount);

        btTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });

        client = RestApplication.getRestClient(this);

        etBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvCount.setText(etBody.getText().toString().length() + "");
            }
        });
    }

    private void sendTweet() {
        client.sendTweet(etBody.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject response = new JSONObject(new String(responseBody));
                    Tweet resultTweet = Tweet.fromJSON(response);

                    Intent responseDataIntent = new Intent();
                    responseDataIntent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(resultTweet));
                    setResult(RESULT_OK, responseDataIntent);

                    client.sendTweet(etBody.getText().toString(), this);

                    finish();
                } catch (JSONException e) {
                    Log.e("ComposeActivity", "Error parsing response");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
