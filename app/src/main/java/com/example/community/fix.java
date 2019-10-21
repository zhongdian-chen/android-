package com.example.community;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fix extends AppCompatActivity {
    private ImageView image;//logo，代替黑色色块的图片
    public static final int TAKE_PHOTO = 1;//拍照
    public static final int CHOOSE_PHOTO = 2;//从相册选择图片
    private Uri imageUri;
    private Bitmap logoBitmap;//logo图片
    private Button browse, sumbit, cancle;
    private Spinner spinner;
    private EditText briefinfo, addr;
    private FixInfoDBAdapter fixInfoDBAdapter;
    private String Item, Name, Num, Pho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix);
        Intent intent = getIntent();
        Name = intent.getStringExtra("name");
        Num = intent.getStringExtra("num");
        Pho = intent.getStringExtra("pho");
        spinner = (Spinner) findViewById(R.id.spinner);
        briefinfo = (EditText) findViewById(R.id.briefinfo);
        addr = (EditText) findViewById(R.id.addr);
        image = findViewById(R.id.image);
        browse = findViewById(R.id.browse);
        browse.setOnClickListener(browselistener);
        sumbit=findViewById(R.id.submit);
        sumbit.setOnClickListener(sumbitlistener);
        cancle=findViewById(R.id.cancel);
        cancle.setOnClickListener(canclelistener);
        //设置自动换行
        briefinfo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        briefinfo.setGravity(Gravity.TOP);
        briefinfo.setSingleLine(false);
        briefinfo.setHorizontallyScrolling(false);

        addr.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        addr.setGravity(Gravity.TOP);
        addr.setSingleLine(false);
        addr.setHorizontallyScrolling(false);

        List<String> listspinner = new ArrayList<String>();
        listspinner.add("请选择报修项目");
        listspinner.add("水管类");
        listspinner.add("电管类");
        listspinner.add("木金、五金类");
        listspinner.add("综合类（含电焊、油漆涂料粉刷等）");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listspinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Item = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fixInfoDBAdapter= new FixInfoDBAdapter(this);
        fixInfoDBAdapter.open();
    }



    View.OnClickListener sumbitlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FixInfo fixInfo = new FixInfo();
            fixInfo.Num = Num;
            fixInfo.BriefInfo = briefinfo.getText().toString();
            fixInfo.Item = Item;
            fixInfo.Addr = addr.getText().toString();
            long colunm = fixInfoDBAdapter.insert(fixInfo);
            if(TextUtils.isEmpty(briefinfo.getText())){
                Toast.makeText(fix.this,"报修情况不为空",Toast.LENGTH_SHORT).show();
            }else if(Item.equals("请选择报修项目")){
                Toast.makeText(fix.this,"请选择报修项目",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(addr.getText())){
                Toast.makeText(fix.this,"报修地址不为空",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(fix.this, "请等待工作人员前来维修", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fix.this, main.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        }
    };


    View.OnClickListener canclelistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(fix.this, main.class);
            intent.putExtra("name",Name);
            intent.putExtra("pho",Pho);
            intent.putExtra("num",Num);
            startActivity(intent);
        }
    };



    View.OnClickListener browselistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder choiceBuilder = new AlertDialog.Builder(fix.this);
            choiceBuilder.setCancelable(false);
            choiceBuilder
                    .setTitle("选择图片")
                    .setSingleChoiceItems(new String[]{"拍照上传", "从相册选择"}, -1,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0://拍照
                                            takePhoto();
                                            break;
                                        case 1:// 从相册选择
                                            choosePhotoFromAlbum();
                                            break;
                                        default:
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            choiceBuilder.create();
            choiceBuilder.show();
        }
    };


    /**
     * 拍照
     */
    private void takePhoto() {
        // 创建File对象，用于存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        }
//        else {
//            imageUri = FileProvider.getUriForFile(fix.this, "com.example.xch.generateqrcode.fileprovider", outputImage);
//        }
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 从相册选取图片
     */
    private void choosePhotoFromAlbum() {
        if (ContextCompat.checkSelfPermission(fix.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(fix.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    /**
     * /打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相册哟", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        logoBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        // 将拍摄的照片显示出来
                        image.setImageBitmap(logoBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 4.4以后
     *
     * @param data
     */
    @SuppressLint("NewApi")
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    /**
     * 4.4版本以前，直接获取真实路径
     *
     * @param data
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 显示图片
     *
     * @param imagePath 图片路径
     */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            logoBitmap = BitmapFactory.decodeFile(imagePath);
            // 显示图片
            image.setImageBitmap(logoBitmap);
        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }
}
