package me.loveshare.service.member.api;

/**
 * Created by Tony on 2017/6/23.
 */

import lombok.extern.slf4j.Slf4j;
import me.loveshare.util.common.NetworkUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Cloud 服务器层api通用日志
 */
@Slf4j
public class BasedApi {

    @ModelAttribute
    public void firstMC(HttpServletRequest request) {
        //打印请求日志
        printAccess(request);
    }

    private void printAccess(HttpServletRequest request) {
        StringBuilder su = new StringBuilder();
        su.append("\n[Service]User-Access-Args:").append("{");
        su.append("\"ip\":\"").append(NetworkUtils.getIpAddr(request)).append("\",");
        su.append("\"method\":\"").append(request.getMethod()).append("\",");
        su.append("\"url\":\"").append(NetworkUtils.getCurrentURL(request)).append("\",").append("\"}");
        log.info(su.toString());
    }
}
