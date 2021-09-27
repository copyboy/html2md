package cn.zhangqingdong.service;

import cn.zhangqingdong.mdutil.HTML2Md;
import cn.zhangqingdong.util.FilesUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    //

    /**
     * 下载 CSDN 类别下的所有文章
     * @param url ex. https://blog.csdn.net/qq_37771475/category_8584136.html
     * @return 文件列表
     */
    public List<File> downloadZipFiles(URL url) {
        // 遍历该页面下的标题，如果有翻页，后续进行翻页处理文件

        ImmutablePair<String,String> pair = ImmutablePair.nullPair();
        List<String> articleList = new ArrayList<>();
        List<File> mdFiles = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(url, 60 * 100);
            Elements articleEle = doc.select(".column_article_list");
            Elements liEle = articleEle.get(0).children();
            for (Element ele : liEle) {
                String href = ele.child(0).attr("href");
                articleList.add(href);
            }

            for (String articleUrl: articleList) {
                pair = HTML2Md.convert(new URL(articleUrl), "utf-8", true, false);
                mdFiles.add(generateFile(pair));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mdFiles;
    }

    private File generateFile(ImmutablePair<String, String> pair) {

        File file = null;
        try {
            file = new File(pair.getKey() + ".md");
            FileUtils.write(file, pair.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

}
