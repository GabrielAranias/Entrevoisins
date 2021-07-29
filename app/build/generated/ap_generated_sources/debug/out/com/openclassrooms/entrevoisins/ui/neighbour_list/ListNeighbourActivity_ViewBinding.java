// Generated code from Butter Knife. Do not modify!
package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.openclassrooms.entrevoisins.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListNeighbourActivity_ViewBinding implements Unbinder {
  private ListNeighbourActivity target;

  private View view7f08001e;

  @UiThread
  public ListNeighbourActivity_ViewBinding(ListNeighbourActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListNeighbourActivity_ViewBinding(final ListNeighbourActivity target, View source) {
    this.target = target;

    View view;
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tabs, "field 'mTabLayout'", TabLayout.class);
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.container, "field 'mViewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.add_neighbour, "method 'addNeighbour'");
    view7f08001e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addNeighbour();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ListNeighbourActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTabLayout = null;
    target.mToolbar = null;
    target.mViewPager = null;

    view7f08001e.setOnClickListener(null);
    view7f08001e = null;
  }
}
