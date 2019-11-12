package com.bawei.shop2019101511.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends Fragment {

    @BindView(R.id.iv_head_portrait)
    ImageView ivHeadPortrait;
    @BindView(R.id.tv_datum)
    TextView tvDatum;
    @BindView(R.id.tv_circle)
    TextView tvCircle;
    @BindView(R.id.tv_foot)
    TextView tvFoot;
    @BindView(R.id.tv_burse)
    TextView tvBurse;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



//    点击事件
    @OnClick({R.id.iv_head_portrait, R.id.tv_datum, R.id.tv_circle, R.id.tv_foot, R.id.tv_burse, R.id.tv_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_portrait:
//                调用相册
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,200);
                break;
            case R.id.tv_datum:
                break;
            case R.id.tv_circle:
                break;
            case R.id.tv_foot:
                break;
            case R.id.tv_burse:
                break;
            case R.id.tv_address:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 200) {//从图库选取的回调方法
                Uri uri = data.getData();//content://media/external/images/media/72876
                Bitmap bitmap1 = null;
                if (uri != null) {
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    String path = null;
                    String[] pic= {MediaStore.Images.Media.DATA};
                    Cursor cursor = contentResolver.query(uri, pic, null, null, null);
                    if (cursor==null){
                        path=uri.getPath();
                    }else {
                        cursor.moveToFirst();
                        path= cursor.getString(cursor.getColumnIndex(pic[0]));
                    }
                    try {
                        bitmap1 = BitmapFactory.decodeFile(path);
                        ivHeadPortrait.setImageBitmap(bitmap1);//在预览框显示选择的图片，问题就在这里，有时候显示，有时候不显示

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
