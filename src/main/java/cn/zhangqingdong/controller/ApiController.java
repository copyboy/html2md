package cn.zhangqingdong.controller;

import cn.zhangqingdong.dto.BaseResp;
import cn.zhangqingdong.dto.ParamVo;
import cn.zhangqingdong.enums.ResultStatus;
import cn.zhangqingdong.service.Common2mdService;
import cn.zhangqingdong.util.ZipUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Api 入口
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-09-16 14:33
 */
@Controller
@RequestMapping("/tool")
@CrossOrigin
public class ApiController {

    @Resource
    private Common2mdService common2mdService;

    @RequestMapping(value = "/html2md", method = RequestMethod.GET)
    public String toPage() {
        return "/html2md";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    @ResponseBody
    public BaseResp<String> convert(@RequestBody ParamVo paramVo) {
        System.out.println(paramVo);
        try {
            String result = "";
            if (paramVo.getUrl().isEmpty() && paramVo.getHtml().isEmpty()) {
                return new BaseResp<>(ResultStatus.error_invalid_argument,
                        ResultStatus.error_invalid_argument.getErrorMsg());
            }
            if (!paramVo.getUrl().isEmpty()) {
                if ("html".equalsIgnoreCase(paramVo.getType())) {
                    result = common2mdService.convert2HtmlWithoutTitle(new URL(paramVo.getUrl()));
                } else {
                    result = common2mdService.convert2MdWithoutTitle(new URL(paramVo.getUrl()));
                }
            }
            return new BaseResp<>(ResultStatus.SUCCESS, result);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>(ResultStatus.http_status_internal_server_error,
                    ResultStatus.http_status_internal_server_error.getErrorMsg());
        }
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam(name = "url") String url,
                         @RequestParam(name = "type") String type,
                         HttpServletResponse response) {
        try {
            ImmutablePair<String, String> result;
            if ("html".equalsIgnoreCase(type)) {
                result = common2mdService.convert2HtmlWithTitle(new URL(url));
            } else {
                result = common2mdService.convert2MdWithTitle(new URL(url));
            }
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(result.getKey().getBytes(StandardCharsets.UTF_8), "ISO8859-1")
                    + "." + type);
            OutputStream out = response.getOutputStream();
            out.write(result.getValue().getBytes(StandardCharsets.UTF_8));
            out.flush();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/downloadCategory", method = RequestMethod.GET)
    public HttpServletResponse downloadCategory(@RequestParam(name = "url") String url,
                                                HttpServletResponse response) {
        // https://blog.csdn.net/qq_37771475/category_8584136.html

        String[] uri = url.split("/");
        String author = uri[3];
        List<File> zipFiles = null;
        try {
            zipFiles = common2mdService.downloadZipFiles(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //打包凭证.zip与EXCEL一起打包
        try {
            File fileA = new File(author+".zip");
            if (!fileA.exists()) {
                fileA.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fousa = new FileOutputStream(fileA);
            ZipOutputStream zipOutA = new ZipOutputStream(fousa);
            ZipUtils.zipFile(zipFiles, zipOutA);
            zipOutA.close();
            fousa.close();
            return ZipUtils.downloadZip(fileA, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
