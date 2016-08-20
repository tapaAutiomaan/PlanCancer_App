package com.plancancer.plancancernews.core_services.utilities;

import android.content.Context;
import android.util.Log;

import com.plancancer.plancancernews.core_services.applications.NewsApplication;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Yazid on 27/02/2016.
 */
public class FileUtils {

    public static void writeToFile(String data,String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(NewsApplication.getNewsContext().openFileOutput(filename+".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




}






