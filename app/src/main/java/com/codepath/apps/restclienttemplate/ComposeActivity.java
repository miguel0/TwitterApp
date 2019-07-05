package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    @BindView(R.id.etBody) EditText etBody;
    @BindView(R.id.btTweet) Button btTweet;
    @BindView(R.id.tvCount) TextView tvCount;

    @OnTextChanged(R.id.etBody)
    public void changeCount() {
        tvCount.setText(etBody.getText().toString().length() + "");
    }

    RestClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        ButterKnife.bind(this);
        btTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });

        client = RestApplication.getRestClient(this);
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
                error.printStackTrace();
            }
        });
    }
}
