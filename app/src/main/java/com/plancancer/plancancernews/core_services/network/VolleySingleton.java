package com.plancancer.plancancernews.core_services.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.plancancer.plancancernews.core_services.applications.NewsApplication;

/**
 * Created by Assou on 21/08/2015.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance=null;
    private static RequestQueue mRequestQueue;
    //private ImageLoader imageLoader;

    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(NewsApplication.getNewsContext());
        /*this.imageLoader=new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache=new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });*/
    }

    public static VolleySingleton getInstance(){
        if(sInstance==null)
            sInstance=new VolleySingleton();

        return sInstance;
    }


    public static RequestQueue createRequestQueue(){
        return Volley.newRequestQueue(NewsApplication.getNewsContext());
    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
/*
    public ImageLoader getImageLoader(){
        return imageLoader;
    }

*/


}
