// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComposeActivity_ViewBinding<T extends ComposeActivity> implements Unbinder {
  protected T target;

  private View view2131165245;

  private TextWatcher view2131165245TextWatcher;

  @UiThread
  public ComposeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.etBody, "field 'etBody' and method 'changeCount'");
    target.etBody = Utils.castView(view, R.id.etBody, "field 'etBody'", EditText.class);
    view2131165245 = view;
    view2131165245TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.changeCount();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131165245TextWatcher);
    target.btTweet = Utils.findRequiredViewAsType(source, R.id.btTweet, "field 'btTweet'", Button.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tvCount, "field 'tvCount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etBody = null;
    target.btTweet = null;
    target.tvCount = null;

    ((TextView) view2131165245).removeTextChangedListener(view2131165245TextWatcher);
    view2131165245TextWatcher = null;
    view2131165245 = null;

    this.target = null;
  }
}
