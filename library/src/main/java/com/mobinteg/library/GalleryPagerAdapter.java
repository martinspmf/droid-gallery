package com.mobinteg.library;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GalleryPagerAdapter extends BaseAdapter{

	ArrayList<String> titles = new ArrayList<String>();
	ArrayList<String> icons = new ArrayList<String>();
	Context context;
	private static LayoutInflater mInflater;

	public GalleryPagerAdapter(Context context,
							   ArrayList<String> titles, ArrayList<String> icons) {

		this.context = context;
		this.titles = titles;
		this.icons = icons;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {


		MenuListHolder holder = new MenuListHolder();
		if (view == null) {
			view = mInflater.inflate(R.layout.item_row, null, true);
			mInflater = ((Activity) context).getLayoutInflater();

			holder.title = (TextView) view.findViewById(R.id.row_title);
			holder.icon = (TextView) view.findViewById(R.id.row_icon);

			view.setTag(holder);
		} else {
			holder = (MenuListHolder) view.getTag();
		}

		holder.title.setText(titles.get(position));
		holder.icon.setText(icons.get(position));

		return view;
	}

	public class MenuListHolder{
		protected TextView title;
		protected TextView icon;

	}
}