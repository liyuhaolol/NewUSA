package spa.lyh.cn.ft_newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;

public class MainActivity extends BaseActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        //setTranslucentTOP();
        //setTranslucentBottom();
        setTranslucentBoth();
        Log.e("qwer","是否有导航栏："+hasNavigationBar(this));
        Log.e("qwer","高度："+PixelUtils.getNavigationBarHeight(this));
    }
}
