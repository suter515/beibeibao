package com.example.testphoto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button btn_photo;
	private ImageView mgView_photo;
	private File mPhotoFile;
	private String mPhotoPath;
	public final static int CAMERA_RESULT = 8888;
	public final static String TAG = "xx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_photo = (Button) findViewById(R.id.btn_photo);
		mgView_photo = (ImageView) findViewById(R.id.mgView_photo);
		btn_photo.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			try {
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				mPhotoPath = "mnt/sdcard/DCIM/Camera/" + getPhotoFileName();
				mPhotoFile = new File(mPhotoPath);
				if (!mPhotoFile.exists()) {
					mPhotoFile.createNewFile();
				}
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(mPhotoFile));
				startActivityForResult(intent, CAMERA_RESULT);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	};

	/**
	 * 用时间戳生成照片名称
	 * 
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_RESULT) {
			Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);
			mgView_photo.setImageBitmap(bitmap);
		}
	}

}
