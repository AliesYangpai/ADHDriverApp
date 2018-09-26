package com.adhdriver.work.entity.driver.photo;

/**
 * Created by Administrator on 2017/4/17.
 * 类描述
 * 版本
 */

/**
 * 图片配置类，用于从系统选择，或拍照选择
 */

public class PhotoConfig {

    private  boolean isCrop;//是否剪切

    private boolean isOwnCropTool;// 裁剪工具 【第三方、takePhoto自带】

    //裁剪尺寸配置:

    private int cropWidth; //裁剪尺寸宽度

    private int cropHight; //裁剪尺寸高度


    /**
     * 压缩工具配置
     */

    private boolean isCompress;

    private int compressWidth;//压缩最大宽度

    private int compressHeight;//压缩最大高度

    private int maxSize ;//大小不超过

    private boolean isShowCompressProcess;  //是否显示压缩图片的dialog

    /**
     * 选择相片配置
     */

    private boolean isTakePhotoGallery; //是否进入takePhotoGallery

    private int maxSelectPicCount;  //设置最多显示几张

    private boolean fixRotationAngle;//是否修正拍照后的旋转角度

    private boolean saveRawPic;//拍照后是否保存原图


    public PhotoConfig() {
    }


    public boolean isCrop() {
        return isCrop;
    }

    public void setCrop(boolean crop) {
        isCrop = crop;
    }

    public boolean isOwnCropTool() {
        return isOwnCropTool;
    }

    public void setOwnCropTool(boolean ownCropTool) {
        isOwnCropTool = ownCropTool;
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidth = cropWidth;
    }

    public int getCropHight() {
        return cropHight;
    }

    public void setCropHight(int cropHight) {
        this.cropHight = cropHight;
    }

    public boolean isCompress() {
        return isCompress;
    }

    public void setCompress(boolean compress) {
        isCompress = compress;
    }

    public int getCompressWidth() {
        return compressWidth;
    }

    public void setCompressWidth(int compressWidth) {
        this.compressWidth = compressWidth;
    }

    public int getCompressHeight() {
        return compressHeight;
    }

    public void setCompressHeight(int compressHeight) {
        this.compressHeight = compressHeight;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isShowCompressProcess() {
        return isShowCompressProcess;
    }

    public void setShowCompressProcess(boolean showCompressProcess) {
        isShowCompressProcess = showCompressProcess;
    }

    public boolean isTakePhotoGallery() {
        return isTakePhotoGallery;
    }

    public void setTakePhotoGallery(boolean takePhotoGallery) {
        isTakePhotoGallery = takePhotoGallery;
    }

    public int getMaxSelectPicCount() {
        return maxSelectPicCount;
    }

    public void setMaxSelectPicCount(int maxSelectPicCount) {
        this.maxSelectPicCount = maxSelectPicCount;
    }

    public boolean isFixRotationAngle() {
        return fixRotationAngle;
    }

    public void setFixRotationAngle(boolean fixRotationAngle) {
        this.fixRotationAngle = fixRotationAngle;
    }

    public boolean isSaveRawPic() {
        return saveRawPic;
    }

    public void setSaveRawPic(boolean saveRawPic) {
        this.saveRawPic = saveRawPic;
    }
}
