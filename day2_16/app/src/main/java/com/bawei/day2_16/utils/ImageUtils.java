package com.bawei.day2_16.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author 王艺霏
 * @fileName ImageUtils
 * @package com.bawei.day2_16.utils
 **/
public class ImageUtils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration builder = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(builder);
    }
}
