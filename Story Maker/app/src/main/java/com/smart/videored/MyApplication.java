package com.smart.videored;

import com.ads.control.AdsApplication;

public class MyApplication extends AdsApplication {



    public void onCreate() {
        super.onCreate();


//        new loadRawData().execute();
    }

//    class loadRawData extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            int[] iArr = {R.raw.friend, R.raw.friend, R.raw.beach, R.raw.travel,
//                    R.raw.christmas, R.raw.happy, R.raw.movie, R.raw.summer,
//                    R.raw.funny, R.raw.romantic, R.raw.sad};
//            for (int i = 0; i < 11; i++) {
//                String path = getCacheDir().getPath() + "/StoryMaker";
//                File file = new File(path);
//                if (file.mkdirs() || file.isDirectory()) {
//                    String str = i + ".m4r";
//                    try {
//                        CopyRAWtoSDCard(iArr[i], path + File.separator + str);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return null;
//        }
//    }
//
//    private void CopyRAWtoSDCard(int id, String path) throws IOException {
//        InputStream in = getResources().openRawResource(id);
//        FileOutputStream out = new FileOutputStream(path);
//        byte[] buff = new byte[1024];
//        int read = 0;
//        try {
//            while ((read = in.read(buff)) > 0) {
//                out.write(buff, 0, read);
//            }
//        } finally {
//            in.close();
//            out.close();
//        }
//    }
}
