package com.lauren.simplenews.images.presenter;

import com.lauren.simplenews.beans.ImageBean;
import com.lauren.simplenews.images.model.ImageModel;
import com.lauren.simplenews.images.model.ImageModelImpl;
import com.lauren.simplenews.images.view.ImageView;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {

    private ImageModel mImageModel;
    private ImageView mImageView;

    public ImagePresenterImpl(ImageView imageView) {
        this.mImageModel = new ImageModelImpl();
        this.mImageView = imageView;
    }

    @Override
    public void loadImageList(int type, int pageIndex) {
        mImageModel.loadImageList(type, pageIndex, this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
