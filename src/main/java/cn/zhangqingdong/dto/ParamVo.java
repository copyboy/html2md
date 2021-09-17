package cn.zhangqingdong.dto;

/**
 * 请求参数
 */
public class ParamVo {
    private String url;
    private String html;
    private String type;

    @Override
    public String toString() {
        return "ParamVo{" +
                "url='" + url + '\'' +
                ", html='" + html + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
