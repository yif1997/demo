package com.bawei.wangyifei20190218.utils;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * @author 王艺霏
 * @fileName IamgeUtils
 * @package com.bawei.wangyifei20190218.utils
 **/
public class IamgeUtils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(Environment.getExternalStorageDirectory(),"/img");
       if (file.exists()){
           file.mkdir();
       }
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileCount(50)
                .diskCacheSize(50*1024*1024)
                .build();
        ImageLoader.getInstance().init(build);
    }
}
