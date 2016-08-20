package com.plancancer.plancancernews.core_services.loggers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Assou on 02/08/2015.
 */
public class NewsToaster  {

    Context context;

    public NewsToaster(Context context){
        this.context=context;
    }

    public  void printMessage(String message){
        Toast.makeText(this.context,message,Toast.LENGTH_LONG).show();
    }

}
