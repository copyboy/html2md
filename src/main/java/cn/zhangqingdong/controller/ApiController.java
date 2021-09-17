package cn.zhangqingdong.controller;

import cn.zhangqingdong.service.Common2mdService;
import cn.zhangqingdong.dto.BaseResp;
import cn.zhangqingdong.dto.ParamVo;
import cn.zhangqingdong.enums.ResultStatus;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Api 入口
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-09-16 14:33
 */
@Controller
@RequestMapping("/tool")
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
}
