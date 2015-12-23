package com.lauren.simplenews.images.model;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public interface ImageModel {
    void loadImageList(ImageModelImpl.OnLoadImageListListener listener);
}
