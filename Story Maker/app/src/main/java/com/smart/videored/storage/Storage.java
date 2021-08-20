package com.smart.videored.storage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;
import com.smart.videored.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Storage {
    private static final String DIRECTORY_IMAGE = "TextPhoto";
    private static final String DIRECTORY_RAW = "StoryMaker";
    /////////////////////////////RAW/////////////////////////////////////

    public static String getDirectoryRawPath(Context context) {
        File directory = new File(context.getFilesDir(), DIRECTORY_RAW);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory.getAbsolutePath();

    }

    public static void CopyRAWToSDCard(Context context, int id, String filename) {
        File directory = new File(context.getFilesDir(), DIRECTORY_RAW);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File rawFile = new File(directory, filename);
        InputStream in = context.getResources().openRawResource(id);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(rawFile);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////IMAGE///////////////////////////////////
    public static File getOutputFileImage(Context context) {
        File directory = new File(context.getFilesDir(), DIRECTORY_IMAGE);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = "IMG_" + Calendar.getInstance().getTime().getTime() + ".jpg";
        return new File(directory, fileName);
    }

    private static File getOutputFileImageBelowAndroid10(Context context) {
        File mediaDirectory = new File(Environment.getExternalStorageDirectory(), DIRECTORY_IMAGE);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !mediaDirectory.isDirectory()) {
            mediaDirectory.mkdirs();
        }
        String fileName = "IMG_" + Calendar.getInstance().getTime().getTime() + ".jpg";
        File mediaFile = new File(mediaDirectory, fileName);
        return mediaFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static File getOutputFileImageAboveAndroid10(Context context) {
        String filename = "IMG_" + Calendar.getInstance().getTime().getTime() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
        contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + DIRECTORY_IMAGE
        );
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + DIRECTORY_IMAGE + File.separator + filename);
    }

    public static String saveImage(Context context, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return saveImageAboveAndroid10(context, bitmap);
        } else {
            return saveImageBelowAndroid10(context, bitmap);
        }
    }

    private static String saveImageBelowAndroid10(Context context, Bitmap bitmap) {
        File mediaDirectory = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !mediaDirectory.isDirectory()) {
            mediaDirectory.mkdirs();
        }
        String fileName = "IMG_" + Calendar.getInstance().getTime().getTime() + ".jpg";
        File mediaFile = new File(mediaDirectory, fileName);
        try {
            OutputStream fileOutputStream = new FileOutputStream(mediaFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(), mediaFile.getAbsolutePath(), mediaFile.getName(), mediaFile.getName());
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(mediaFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaFile.getAbsolutePath();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static String saveImageAboveAndroid10(Context context, Bitmap bitmap) {
        String filename = "IMG_" + Calendar.getInstance().getTime().getTime() + ".jpg";
        OutputStream outputStream;
        Uri imageUri;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
//        contentValues.put(MediaStore.Video.Media.IS_PENDING, 1);
        contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + context.getString(
                        R.string.app_name
                )
        );
        imageUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        try {
            outputStream = context.getContentResolver().openOutputStream(imageUri);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        contentValues.clear();
//        contentValues.put(MediaStore.Video.Media.IS_PENDING, 0);
        context.getContentResolver().update(imageUri, contentValues, null, null);
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + context.getString(
                R.string.app_name
        ) + File.separator + filename;
    }

    public static String getDirectoryFileImageInStorage(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getDirectoryImageAboveAndroid10(context);
        } else {
            return getDirectoryImageBelowAndroid10(context);
        }
    }

    private static String getDirectoryImageBelowAndroid10(Context context) {
        File mediaDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));
        if (mediaDirectory.exists()) {
            return mediaDirectory.getAbsolutePath();
        }
        return "";
    }

    private static String getDirectoryImageAboveAndroid10(Context context) {
        File mediaDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));
        if (mediaDirectory.exists()) {
            return mediaDirectory.getAbsolutePath();
        }
        return "";
    }

    public static File[] getImages(Context context) {
        String uri = MediaStore.Images.Media.DATA;
        // if GetImageFromThisDirectory is the name of the directory from which image will be retrieved
        String condition = uri + " like '%/Pictures/" + context.getString(R.string.app_name) + "/%'";
        String[] projection = {uri, MediaStore.Images.Media.DATE_ADDED};
        List<File> lstFile = new ArrayList<>();
        try {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                    condition, null, null);
            if (cursor != null) {
                boolean isDataPresent = cursor.moveToFirst();
                if (isDataPresent) {
                    do {
                        lstFile.add(new File(cursor.getString(cursor.getColumnIndex(uri))));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File[] lstOut = new File[lstFile.size()];
        for (int i = 0; i < lstFile.size(); i++) {
            lstOut[i] = lstFile.get(i);
        }
        return lstOut;
    }

    /////////////////////////////VIDEO///////////////////////////////////

    public static VideoValue saveVideo(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return saveVideoAboveAndroid10(context);
        } else {
            return saveVideoBelowAndroid10(context);
        }
    }

    public static VideoValue saveVideoBelowAndroid10(Context context) {
        File mediaDirectory = new File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name));
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !mediaDirectory.isDirectory()) {
            mediaDirectory.mkdirs();
        }
        String fileName = "Story_maker_" + Calendar.getInstance().getTime().getTime() + ".mp4";
        File mediaFile = new File(mediaDirectory, fileName);
        try {
            mediaFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VideoValue videoValue = new VideoValue();
        videoValue.setPath(mediaFile.getAbsolutePath());
        return videoValue;
    }

    public static VideoValue saveVideoAboveAndroid10(Context context) {
        String fileName = "Story_maker_" + Calendar.getInstance().getTime().getTime() + ".mp4";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + File.separator + context.getString(R.string.app_name));
        contentValues.put(MediaStore.Video.Media.TITLE, fileName);
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        contentValues.put(MediaStore.Video.Media.IS_PENDING, 1);
        Uri mUri = context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        VideoValue videoValue = new VideoValue();
        videoValue.setContentValues(contentValues);
        videoValue.setPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + File.separator + context.getString(
                R.string.app_name
        ) + File.separator + fileName);
        videoValue.setUri(mUri);
        return videoValue;
    }

    public static String getDirectoryFileVideoInStorage(Context context) {
        File mediaDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), context.getString(R.string.app_name));
        if (mediaDirectory.exists()) {
            return mediaDirectory.getAbsolutePath();
        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void updateGalleryBelow10(Context context, String path) {
        MediaScannerConnection.scanFile(context,
                new String[]{path}, null,
                (path1, uri) -> {

                });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void updateGalleryAbove10(Context context, ContentValues contentValues, Uri uri) {
        contentValues.clear();
        contentValues.put(MediaStore.Video.Media.IS_PENDING, 0);
        context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static class VideoValue {
        private String path;
        private Uri uri;
        private ContentValues contentValues;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Uri getUri() {
            return uri;
        }

        public void setUri(Uri uri) {
            this.uri = uri;
        }

        public ContentValues getContentValues() {
            return contentValues;
        }

        public void setContentValues(ContentValues contentValues) {
            this.contentValues = contentValues;
        }
    }
}
