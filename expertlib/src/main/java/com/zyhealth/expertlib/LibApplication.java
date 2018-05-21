package com.zyhealth.expertlib;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zyhealth.expertlib.utils.MLog;

import org.litepal.LitePalApplication;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;
import okhttp3.OkHttpClient;

/**
 * Created by zz on 2017/4/6.
 */

public class LibApplication extends LitePalApplication {
    public static LibApplication instance;
    public static Stack<Activity> mActivityStack = new Stack<Activity>();
    public static Handler mMainThreadHanler;

    @Override
    public void   onCreate() {
        super.onCreate();
        instance = this;
        mMainThreadHanler = new Handler();
        initLog();
        initUtils();
        initZxing();
        initOkhttp();
        initGalleryFinal();
        registerActivity();
    }



    private void initGalleryFinal() {
        // 设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimaryDark))//标题栏背景颜色
                .setCheckSelectedColor(getResources().getColor(R.color.colorPrimary))//选择框选中颜色
                .setFabNornalColor(getResources().getColor(R.color.colorPrimary))//设置Floating按钮Nornal状态颜色
                .setFabPressedColor(getResources().getColor(R.color.colorPrimary))//设置Floating按钮Pressed状态颜色
                .build();
        // 配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(1)
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        // 配置ImageLoader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }


    private void initLog() {
        MLog.init(true);
    }

    private void initUtils() {
        Utils.init(this);
    }

    private void initZxing() {
        ZXingLibrary.initDisplayOpinion(this);
    }


    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static LibApplication getInstance() {
        return instance;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }


    /**
     * 删除指定的Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mActivityStack.remove(activity);
    }


    /**
     * 结束所有Activity
     */
    public void killAllActivity2(String  tagActivity) {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if(!mActivityStack.get(i).getClass().getSimpleName().equals(tagActivity)){
                    mActivityStack.get(i).finish();
                }
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }

         mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());//关闭应用,与当前应用相关的应用、进程、服务等也会被关闭,会发送 ACTION_PACKAGE_RESTARTED广播。
            activityMgr.killBackgroundProcesses(context.getPackageName());
        } catch (Exception e) {
        } finally {
            System.exit(0);
        }
    }


    private void registerActivity() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {}

            @Override
            public void onActivityResumed(Activity activity) {}

            @Override
            public void onActivityPaused(Activity activity) {}

            @Override
            public void onActivityStopped(Activity activity) {}

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }


    public class GlideImageLoader implements ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Glide.with(activity)
                    .load("file://" + path)
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .override(width, height)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //不缓存到SD卡
                    .skipMemoryCache(true)
                    //.centerCrop()
                    .into(new ImageViewTarget<GlideDrawable>(imageView) {
                        @Override
                        protected void setResource(GlideDrawable resource) {
                            imageView.setImageDrawable(resource);
                        }

                        @Override
                        public void setRequest(Request request) {
                            imageView.setTag(R.id.adapter_item_tag_key, request);
                        }

                        @Override
                        public Request getRequest() {
                            return (Request) imageView.getTag(R.id.adapter_item_tag_key);
                        }
                    });

        }

        @Override
        public void clearMemoryCache() {
        }
    }

}
