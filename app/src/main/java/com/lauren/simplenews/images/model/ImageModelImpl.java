package com.lauren.simplenews.images.model;

import com.lauren.simplenews.beans.ImageBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.images.ImageJsonUtils;
import com.lauren.simplenews.images.widget.ImageFragment;
import com.lauren.simplenews.utils.OkHttpUtils;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public class ImageModelImpl implements ImageModel {

    /**
     * 获取图片列表
     * @param type
     * @param pageIndex
     */
    @Override
    public void loadImageList(int type, int pageIndex, final OnLoadImageListListener listener) {
        String url = getUrl(type, pageIndex);
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> iamgeBeanList = ImageJsonUtils.readJsonImageBeans(response);
                listener.onSuccess(iamgeBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case ImageFragment.IMAGE_TYPE_JINGXUAN:
                sb.append(Urls.JINGXUAN_ID);
                break;
            case ImageFragment.IMAGE_TYPE_QUTU:
                sb.append(Urls.QUTU_ID);
                break;
            case ImageFragment.IMAGE_TYPE_MEITU:
                sb.append(Urls.MEITU_ID);
                break;
            case ImageFragment.IMAGE_TYPE_GUSHI:
                sb.append(Urls.GUSHI_ID);
                break;
            default:
                sb.append(Urls.JINGXUAN_ID);
                break;
        }
        sb.append(pageIndex);
        return sb.toString();
    }


    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);
        void onFailure(String msg, Exception e);
    }
}
