package com.lottery.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.lottery.base.RequestResult;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/24.
 */

public class HttpUtils {
    private Context context;
    private RequestResult requestResult;
    private String mRequestCode;
    private ProgressDialog dialog;

    public HttpUtils(Context context, RequestResult requestResult, String msg, boolean isShow) {
        this.dialog = DialogUtil.getProgressDialog(context, msg);
        if (isShow){
            if (dialog != null){
                dialog.show();
            }
        }
        this.context = context;
        this.requestResult = requestResult;
    }

    /**
     * 异步请求：回调方法可以直接操作UI
     */
    public void async(String url, String requestCode) {
        this.mRequestCode = requestCode;
        OkHttpUtil.getDefault(context).doPostAsync(
                HttpInfo.Builder().setUrl(url).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                        if (requestResult != null) {
                            requestResult.onFailure(result, mRequestCode);
                            if (dialog != null){
                                dialog.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                        if (requestResult != null) {
                            requestResult.onSuccess(result, mRequestCode);
                            if (dialog != null){
                                dialog.dismiss();
                            }
                        }
                    }
                });
    }

//    /**
//     * 先缓存再网络：先请求缓存，失败则请求网络
//     */
//    public void cacheThenNetwork() {
//        OkHttpUtil.Builder().setCacheType(CacheType.CACHE_THEN_NETWORK).build(context)
//                .doGetAsync(
//                        HttpInfo.Builder().setUrl("").build(),
//                        new Callback() {
//                            @Override
//                            public void onSuccess(HttpInfo info) throws IOException {
//                                String result = info.getRetDetail();
//                                if (requestResult != null) {
//                                    requestResult.onSuccess(result);
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(HttpInfo info) throws IOException {
//                                String result = info.getRetDetail();
//                                if (requestResult != null) {
//                                    requestResult.onFailure(result);
//                                }
//                            }
//                        }
//                );
//    }
//
//    /**
//     * 缓存10秒失效：连续点击进行测试10秒内再次请求为缓存响应，10秒后再请求则缓存失效并进行网络请求
//     */
//    public void tenSecondCache(boolean isNeedDeleteCache) {
//        //由于采用同一个url测试，需要先清理缓存
//        if (isNeedDeleteCache) {
//            isNeedDeleteCache = false;
//            OkHttpUtil.getDefault().deleteCache();
//        }
//        OkHttpUtil.Builder()
//                .setCacheType(CacheType.CACHE_THEN_NETWORK)
//                .setCacheSurvivalTime(10)//缓存存活时间为10秒
//                .build(this)
//                .doGetAsync(
//                        HttpInfo.Builder().setUrl("").build(),
//                        new Callback() {
//                            @Override
//                            public void onSuccess(HttpInfo info) throws IOException {
//                                String result = info.getRetDetail();
//                                if (requestResult != null) {
//                                    requestResult.onSuccess(result);
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(HttpInfo info) throws IOException {
//                                String result = info.getRetDetail();
//                                if (requestResult != null) {
//                                    requestResult.onFailure(result);
//                                }
//                            }
//                        }
//                );
//    }
//
//    /**
//     * 异步上传图片：显示上传进度
//     */
//    public void doUploadImg() {
//        HttpInfo info = HttpInfo.Builder()
//                .setUrl("")
//                .addUploadFile("file", "", new ProgressCallback() {
//                    //onProgressMain为UI线程回调，可以直接操作UI
//                    @Override
//                    public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
//                    }
//                })
//                .build();
//        OkHttpUtil.getDefault(this).doUploadFileAsync(info);
//    }
//
//    /**
//     * 单次批量上传：一次请求上传多个文件
//     */
//    public void doUploadBatch(List<String> imgList) {
//        imgList.clear();
//        imgList.add("/storage/emulated/0/okHttp_download/test.apk");
//        imgList.add("/storage/emulated/0/okHttp_download/test.rar");
//        HttpInfo.Builder builder = HttpInfo.Builder()
//                .setUrl("");
//        //循环添加上传文件
//        for (String path : imgList) {
//            //若服务器为php，接口文件参数名称后面追加"[]"表示数组，示例：builder.addUploadFile("uploadFile[]",path);
//            builder.addUploadFile("uploadFile", path);
//        }
//        HttpInfo info = builder.build();
//        OkHttpUtil.getDefault(context).doUploadFileAsync(info, new ProgressCallback() {
//            @Override
//            public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {
//            }
//
//            @Override
//            public void onResponseMain(String filePath, HttpInfo info) {
//                String result = info.getRetDetail();
//                if (requestResult != null) {
//                    requestResult.onSuccess(result);
//                }
//            }
//        });
//    }


}
