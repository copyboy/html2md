package cn.zhangqingdong.service;

import cn.zhangqingdong.mdutil.HTML2Md;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

/**
 * 通用 服务类
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-09-16 14:33
 */
@Service
public class Common2mdService {


    public ImmutablePair<String,String> convert(URL url, boolean needTitle, boolean returnHtml) throws IOException {
        return HTML2Md.convert(url, "utf-8", needTitle, returnHtml);
    }

    public String convert2MdWithoutTitle(URL url) {
        return HTML2Md.convert(url, "utf-8", false, false).getValue();
    }

    public String convert2HtmlWithoutTitle(URL url) {
        return HTML2Md.convert(url, "utf-8", false, true).getValue();
    }

    public ImmutablePair<String,String> convert2MdWithTitle(URL url) {
        return HTML2Md.convert(url, "utf-8", true, false);
    }

    public ImmutablePair<String,String> convert2HtmlWithTitle(URL url) {
        return HTML2Md.convert(url, "utf-8", true, true);
    }

}
