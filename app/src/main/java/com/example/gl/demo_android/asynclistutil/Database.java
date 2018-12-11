package com.example.gl.demo_android.asynclistutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Database {
    public static SQLiteDatabase getDatabase(Context context, String assetFileName) {
        SQLiteDatabase database = null;
        try {
            InputStream is = context.getApplicationContext().getAssets().open(assetFileName);
            File outfile = new File(context.getFilesDir(), assetFileName);
            if (outfile.exists()) {
                outfile.delete();
            }
            copyToFile(is, outfile);
            database = SQLiteDatabase.openOrCreateDatabase(outfile, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database;
    }

    private static void copyToFile(InputStream is, File outfile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outfile);
            byte[] bf = new byte[1024];
            while (true) {
                if (is.available() < 1024) {
                    int c = -1;
                    while ((c = is.read()) != -1) {
                        fos.write(c);
                    }
                    break;
                } else {
                    is.read(bf);
                    fos.write(bf);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
