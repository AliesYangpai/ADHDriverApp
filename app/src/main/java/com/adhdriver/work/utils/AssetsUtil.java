package com.adhdriver.work.utils;

import com.adhdriver.work.AiDaiHuoApplication;
import com.adhdriver.work.constant.ConstLocalData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/2/17 0017.
 * 类描述
 * 版本
 */
public class AssetsUtil {


    /**
     * 将assets文件家中的图片拷贝到文件夹中
     */
    public static void copyShareImage() {

        boolean result = false;
        FileOutputStream fos = null;
        InputStream is = null;
        File image = null;

        /**
         * 判断是否存在文件夹，并更根据结果进行创建与否
         */

        try {
            image = new File(ConstLocalData.SHARE_IMAGE_DIR);
            if (!image.exists()) {
                image.mkdirs();
            }
            image = new File(ConstLocalData.SHARE_IMAGE_PATH);
            if (!image.exists()) {
                image.createNewFile();
            } else {


                result = true;
                return;
            }

            /**
             *  将asset文件夹中的数据导入到文件夹中
             */

            //得到输入流
            is = AiDaiHuoApplication.getInstance().getResources().getAssets()
                    .open("sharepic.png");
            fos = new FileOutputStream(ConstLocalData.SHARE_IMAGE_PATH);

            byte[] buffer = new byte[1024];

            int count = 0;

            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer,0,count);
            }
            result = true;

            is.close();

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {


            if(!result) {
                image.delete();
            }
        }
    }
}
