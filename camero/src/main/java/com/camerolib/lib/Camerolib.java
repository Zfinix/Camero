package com.camerolib.lib;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.camerolib.lib.utils.PathUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class Camerolib {

private static String X[];

    public static String[] readFileDataX(Uri filePath, Context context) throws IOException {
        if( filePath != null){
           String path = null;
            try {
                path = PathUtil.getPath(context, filePath);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            String[] data = new String[0];
            File file = new File(path);
            Log.d( "CSVX", "readFileData: ");
            if (file.exists())
            {
                BufferedReader br = null;

                br = new BufferedReader(new FileReader(file));

                {
                    String csvLine = "";
                    X = data;
                    while ((csvLine = br.readLine()) != null)
                    {
                        data = csvLine.split(",");
                        try
                        {
                            Log.d( "CSVX", "readFileData: " + data[0]+" "+data[1]);
                            //Toast.makeText(context,data[0]+" "+data[1],Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            Log.e("Problem",e.toString());
                        }
                    }
                }


            }
            else
            {
                Toast.makeText(context,"file not exists",Toast.LENGTH_SHORT).show();
            }
        }
        return X;
    }
}
