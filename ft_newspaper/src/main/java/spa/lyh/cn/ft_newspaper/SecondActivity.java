package spa.lyh.cn.ft_newspaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
       /* if (BuildConfig.DEBUG){
            tv.setText("debug版本");
        }else {
            tv.setText("release版本");
        }*/
        tv.setText(Test.URL);
    }
}
