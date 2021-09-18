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

    CSDN("https://blog.csdn.net/", "id","article_content"),
    CNBLOG("https://www.cnblogs.com/", "id","post_detail"),
    WECHAT("https://mp.weixin.qq.com/", "id","img-content"),
    /**
     * 暂不支持, 需要抓取异步返回数据
     */
    BILIBILI("https://www.bilibili.com/", "css","article-container"),
    UNKNOWN("UNKNOWN", "UNKNOWN","body"),
    ;

    private String url;
    private String contentType;
    private String pageDiv;

    HostEnums(String url, String contentType, String pageDiv) {
        this.url = url;
        this.contentType = contentType;
        this.pageDiv = pageDiv;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public String getPageDiv() {
        return pageDiv;
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
