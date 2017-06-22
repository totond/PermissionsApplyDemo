package yanzhikai.com.permissionsapplydemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class RxPermissionsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_check, btn_getSingle, btn_getMulti, btn_getMultiEach;
    private TextView tv_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permission);

        btn_check = (Button) findViewById(R.id.btn_check);
        btn_getSingle = (Button) findViewById(R.id.btn_getSingle);
        btn_getMulti = (Button) findViewById(R.id.btn_getMulti);
        btn_getMultiEach = (Button) findViewById(R.id.btn_getMultiEach);
        tv_log = (TextView) findViewById(R.id.tv_log);

        btn_check.setOnClickListener(this);
        btn_getSingle.setOnClickListener(this);
        btn_getMulti.setOnClickListener(this);
        btn_getMultiEach.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check:
                String str = PermissionsLogUtils.checkPermissions(this, Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE);

                tv_log.setText(str);
                break;
            case R.id.btn_getSingle:
                requestRxPermissions(Manifest.permission.RECORD_AUDIO);
                break;
            case R.id.btn_getMulti:
                requestRxPermissions(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.btn_getMultiEach:
                requestEachRxPermission(Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }
    }

    private void requestRxPermissions(String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                if (granted){
                    Toast.makeText(RxPermissionsActivity.this, "已获取权限", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RxPermissionsActivity.this, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void requestEachRxPermission(String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {
                if (permission.granted) {
                    Toast.makeText(RxPermissionsActivity.this, "已获取权限"+ permission.name , Toast.LENGTH_SHORT).show();
                } else if (permission.shouldShowRequestPermissionRationale){
                    //拒绝权限请求
                    Toast.makeText(RxPermissionsActivity.this, "已拒绝权限"+ permission.name , Toast.LENGTH_SHORT).show();
                } else {
                    // 拒绝权限请求,并不再询问
                    // 需要进入设置界面去设置权限
                    Toast.makeText(RxPermissionsActivity.this, "已拒绝权限"+ permission.name +"并不再询问", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
