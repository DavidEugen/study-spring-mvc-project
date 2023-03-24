package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MappingController {
    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-go/
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE */
    @RequestMapping({"/hello-basic","/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/hello-go-get", method = RequestMethod.GET)
    public String helloGoGet() {
        log.info("helloBasic");
        return "ok";
    }

}
