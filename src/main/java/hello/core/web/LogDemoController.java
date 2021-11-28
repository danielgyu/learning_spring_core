package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // private final Provider<MyLogger> loggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        //MyLogger myLogger = loggerProvider.get();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestUrl(requestUrl);
        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "Ok";
    }
}
