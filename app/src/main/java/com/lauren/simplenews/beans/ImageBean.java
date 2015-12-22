package com.lauren.simplenews.beans;

import java.io.Serializable;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public class ImageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * title
     */
    private String title;
    /**
     * pic
     */
    private String pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
