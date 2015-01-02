package com.dragonrider.SWEotE.Classes;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SWListBoxItem {
	
	private String name;
	private String desc;
	private Bitmap image;
	private Object tag;
	private String category;
	
	
	
	public SWListBoxItem(String Name, String Desc) {
		this.name = Name;
		this.desc = Desc;
	}

	public String getName() {
		return name;
	}

	public SWListBoxItem setName(String name) {
		this.name = name;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public SWListBoxItem setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public Bitmap getImage() {
		return image;
	}

	public SWListBoxItem setImage(Bitmap image) {
		this.image = image;
		return this;
	}
	
	public SWListBoxItem setDrawable(int Drawable) {
		this.image = BitmapFactory.decodeResource(App.getContext().getResources(), Drawable);
		
		return this;
	}

	public Object getTag() {
		return tag;
	}

	public SWListBoxItem setTag(Object tag) {
		this.tag = tag;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public SWListBoxItem setCategory(String category) {
		this.category = category;
		return this;
	}
	
	
	
}