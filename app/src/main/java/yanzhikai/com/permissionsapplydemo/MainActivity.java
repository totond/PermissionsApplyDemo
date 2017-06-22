package yanzhikai.com.permissionsapplydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final String TAG = "permissionsapplydemo";
    private Button btn_enter1, btn_enter2, btn_enter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_enter1 = (Button) findViewById(R.id.btn_enter1);
        btn_enter2 = (Button) findViewById(R.id.btn_enter2);
        btn_enter3 = (Button) findViewById(R.id.btn_enter3);

        btn_enter1.setOnClickListener(this);
        btn_enter2.setOnClickListener(this);
        btn_enter3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_enter1:
                startActivity(new Intent(this,PermissionsDispatcherActivity.class));
                break;
            case R.id.btn_enter2:
                startActivity(new Intent(this,EasyPermissionsActivity.class));
                break;
            case R.id.btn_enter3:
                startActivity(new Intent(this,RxPermissionsActivity.class));
                break;
        }
    }

}
