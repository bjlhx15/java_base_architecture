package ${package}.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类，用于测试项目是否正常构建
 */
@Api(value = "userController",description = "用户相关操作",tags = {"用户"})
@RequestMapping("/testapi")
@RestController
public class TestApiController {



    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户的详细信息")
    @GetMapping("/testMapNull")
    @ResponseBody
    public Map testMapNull(Long id) {
        return new HashMap();
    }
}