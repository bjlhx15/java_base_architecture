package ${package}.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试类，用于测试项目是否正常构建
 */
@RequestMapping("/test/")
@RestController
public class TestController {
    @GetMapping("/hello")
    public Object hello() {
        return "hello world";
    }

    @GetMapping("/testString")
    @ResponseBody
    public String testString(Long id) {
        return "22";
    }

    @GetMapping("/testStringNull")
    @ResponseBody
    public String testStringNull(Long id) {
        return null;
    }

    @GetMapping("/testBooleanNull")
    @ResponseBody
    public Boolean testBooleanNull(Long id) {
        return null;
    }

    @GetMapping("/testInteger")
    @ResponseBody
    public Integer testInteger(Long id) {
        return 22;
    }

    @GetMapping("/getById6")
    @ResponseBody
    public int testint(Long id) {
        return 22;
    }

    @GetMapping("/testvoid")
    @ResponseBody
    public void testvoid(Long id) {
        return;
    }

    @GetMapping("/testMapNull")
    @ResponseBody
    public Map testMapNull(Long id) {
        return null;
    }

    @GetMapping("/testObjectNull")
    @ResponseBody
    public Object testObjectNull(Long id) {
        return null;
    }
}