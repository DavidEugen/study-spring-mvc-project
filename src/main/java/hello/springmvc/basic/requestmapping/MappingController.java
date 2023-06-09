package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기) * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

//    @GetMapping("/mapping/{userId}")
//    public String mappingPath(@PathVariable("userId") String data) {
//        log.info("mappingPath userId={}", data);
//        return "ok";
//    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {//PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode", // 404 : mode 포함하지 않는 모든 경우
     * params="!mode" // 404 : mode 포함하는 모든 경우
     * params="mode=debug" // 404 : mode=debug 포함하지 않는 모든 경우
     * params="mode!=debug" (! = ) // 404 : 없거나 mode 가 debug인 경우
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "qq")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     *특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }


    /**
     * Content-Type : 클라이언트가 보내는 Request의 데이터 타입
     * Accept : 클라이언트가 받아 들이는 Response의 데이터 타입
     * Content-Type, Accept 둘다 자동으로 생성 한다.
     *
     * 테스트 : POSTMAN으로 이용,
     * Content-Type 은 request Body 형식을 바꿔 본다.
     * Accept는 클라이언트가 자동으로 생성하므로 강제로 지정 해 본다. 보통 *\/* 이지 않을까?
     */

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * consumes={"text/plain", "application/*"}
     * consumes=MediaType.APPLICATION_JSON_VALUE
     * 만약 맞지 않으면 HTTP 415 상태코드(Unsupported Media Type)을 반환
     * Request 정보를 서버가 소비하니깐 consumes
     * header 정보를 보고 서버가 받아 들일 수 있는 타입만 핸들러가 처리한다.
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * produces = {"text/plain", "application/*"}
     * produces = MediaType.TEXT_PLAIN_VALUE
     * produces = "text/plain;charset=UTF-8"
     * 만약 맞지 않으면 HTTP 406 상태코드(Not Acceptable)을 반환
     * Response 정보를 서버가 생산하니깐 produces
     * header 정보를 보고 클라이언트가 해결 할 수 있는 타입의 응답만 핸들러가 처리한다..
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
