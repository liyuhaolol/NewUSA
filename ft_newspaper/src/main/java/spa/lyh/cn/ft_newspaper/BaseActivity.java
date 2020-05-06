package spa.lyh.cn.ft_newspaper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import spa.lyh.cn.lib_utils.PixelUtils;


/**
 * Created by liyuhao on 2017/12/18.
 */

public class BaseActivity extends AppCompatActivity {
    public View statusBar;
    public View nav_area;
    public String TAG;
    public boolean canNav = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }


    /**
     * 设置沉浸式状态栏
     */
    /*public void setStatusBar(){
        setStatusBar(LinearLayout.class);
    }*/

    /**
     * 设置沉浸式状态栏
     */
    /*public void setStatusBar(Class clazz){
        setTranslucent();
        statusBar = findViewById(R.id.status_bar);
        if (clazz == RelativeLayout.class){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtils.getStatusBarHeight(this));
            statusBar.setLayoutParams(layoutParams);
        }
        if (clazz == LinearLayout.class){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtils.getStatusBarHeight(this));
            statusBar.setLayoutParams(layoutParams);
        }
    }*/

    /**
     * 非纯色状态栏，比如用图片进入状态栏位置，使用这个方法。如果纯色状态栏使用这个方法，效果与上面一致，但是不再
     * 兼容换肤框架，状态栏颜色需要手动控制。
     */
    public void setTranslucentTOP(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void setTranslucentBottom(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(Color.TRANSPARENT);
            setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void setTranslucentBoth(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setTranslucentTOP();
            setTranslucentBottom();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    public boolean hasNavigationBar(Activity activity) {
        Point size = new Point();
        Point realSize = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        activity.getWindowManager().getDefaultDisplay().getRealSize(realSize);
        if (realSize.equals(size)) {
            return false;
        } else {
            size.y = size.y + PixelUtils.getNavigationBarHeight(activity);
            if (realSize.y < size.y){
                return false;
            }
            return true;
        }
    }

    /**
     * 设置导航栏颜色，由于字体原因只在8.0以上支持,此处颜色值写死的
     */
    /*public void setNavigationBarColor(boolean isLightMode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if (isLightMode){
                getWindow().setNavigationBarColor(getColor(R.color.white_bg));
                setSystemUiVisibility(getWindow().getDecorView(),View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,true);
            }else {
                getWindow().setNavigationBarColor(getColor(R.color.white_bg_night));
                setSystemUiVisibility(getWindow().getDecorView(),View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,false);
            }

        }
    }*/

    /**
     * 设置显示的样式
     * @param decorView
     * @param visibility
     */
    public void setSystemUiVisibility(View decorView,int visibility){
        setSystemUiVisibility(decorView,visibility,true);
    }

    /**
     * 设置显示的样式
     * @param decorView
     * @param visibility
     */
    public void setSystemUiVisibility(View decorView,int visibility,boolean isAddVisibility){
        int oldVis = decorView.getSystemUiVisibility();
        int newVis = oldVis;
        if (isAddVisibility){
            newVis |= visibility;
        }else {
            newVis &= ~visibility;
        }
        if (newVis != oldVis) {
            decorView.setSystemUiVisibility(newVis);
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    public void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null && fragment.isAdded() && !fragment.isHidden()) {
            ft.hide(fragment);
        }
    }


    public void showToast(String content){
        try{
            Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showToast(int content){
        showToast(content + "");
    }

    public void showToast(boolean content){
        showToast(content + "");
    }
}
