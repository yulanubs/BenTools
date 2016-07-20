package com.bentools.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewUtils {

	public static int getListViewItemHeight(ListView listView) {
		ListAdapter adapter = listView.getAdapter();
		if (adapter == null) {
			return 0;
		}
		View child = adapter.getView(0, null, listView);
		if (child == null) {
			return 0;
		}
		child.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		return child.getMeasuredHeight();
	}

	public static int getListViewScrollY(ListView list_view) {
		View child = list_view.getChildAt(0);
		if (child == null) {
			return 0;
		}
		int top = child.getTop();

		return top;
	}
}
