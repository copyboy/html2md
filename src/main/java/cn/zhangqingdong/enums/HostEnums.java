package cn.zhangqingdong.enums;

import java.net.URL;

/**
 * 支持站点列表
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-09-17 14:43
 */
public enum HostEnums {

    CSDN("https://blog.csdn.net/", "article_content"),
    CNBLOG("https://www.cnblogs.com/", "post_detail"),
    WECHAT("https://mp.weixin.qq.com", "img-content"),
    UNKNOWN("UNKNOWN", "UNKNOWN"),
    ;

    private String url;
    private String pageContentId;

    HostEnums(String url, String pageContentId) {
        this.url = url;
        this.pageContentId = pageContentId;
    }

    public String getUrl() {
        return url;
    }

    public String getPageContentId() {
        return pageContentId;
    }

    public static HostEnums position(URL uri) {
        HostEnums[] values = HostEnums.values();
        for (HostEnums value : values) {
            if (uri.toString().startsWith(value.getUrl())) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
