// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TweetAdapter$ViewHolder_ViewBinding<T extends TweetAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TweetAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivProfileImage = Utils.findRequiredViewAsType(source, R.id.ivProfileImage, "field 'ivProfileImage'", ImageView.class);
    target.tvUsername = Utils.findRequiredViewAsType(source, R.id.tvUserName, "field 'tvUsername'", TextView.class);
    target.tvBody = Utils.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tvDate, "field 'tvDate'", TextView.class);
    target.ivTweetImg = Utils.findRequiredViewAsType(source, R.id.ivTweetImg, "field 'ivTweetImg'", ImageView.class);
    target.btLike = Utils.findRequiredViewAsType(source, R.id.btLike, "field 'btLike'", ImageButton.class);
    target.likeCount = Utils.findRequiredViewAsType(source, R.id.likeCount, "field 'likeCount'", TextView.class);
    target.btRetweet = Utils.findRequiredViewAsType(source, R.id.btRetweet, "field 'btRetweet'", ImageButton.class);
    target.retweetCount = Utils.findRequiredViewAsType(source, R.id.retweetCount, "field 'retweetCount'", TextView.class);
    target.tvHandle = Utils.findRequiredViewAsType(source, R.id.tvHandle, "field 'tvHandle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivProfileImage = null;
    target.tvUsername = null;
    target.tvBody = null;
    target.tvDate = null;
    target.ivTweetImg = null;
    target.btLike = null;
    target.likeCount = null;
    target.btRetweet = null;
    target.retweetCount = null;
    target.tvHandle = null;

    this.target = null;
  }
}
