package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * 此类封装了常用的数据库操作
 */
public class CoolWeatherDB {
	
	//数据库名
	public static final String DB_NAME="cool_weather";
	
	//数据库版本
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	//私有化构造方法
	private  CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
	}
	
	//获取coolweatherDB的实例
	public synchronized static CoolWeatherDB getInstance(Context context){
		if (coolWeatherDB == null) {
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
		
	}
	
	//将province实例存储到数据库
	public void saveProvince(Province province) {
		if (province!=null) {
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("province", null, values);
		}
	}
	
	//从数据库读取全国所有的省份信息
	public List<Province> loadProvinces() {
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		return list;
	}
	
	//将city实例存储到数据库
		public void saveCity(Province province) {
			if (province!=null) {
				ContentValues values=new ContentValues();
				values.put("province_name", province.getProvinceName());
				values.put("province_code", province.getProvinceCode());
				db.insert("province", null, values);
			}
		}
		
		//从数据库读取全国所有的市信息
		public List<Province> loadCities() {
			List<Province> list=new ArrayList<Province>();
			Cursor cursor=db.query("province", null, null, null, null, null, null);
			if (cursor.moveToFirst()) {
				do {
					Province province=new Province();
					province.setId(cursor.getInt(cursor.getColumnIndex("id")));
					province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
					province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
					list.add(province);
				} while (cursor.moveToNext());
			}
			return list;
		}
}









