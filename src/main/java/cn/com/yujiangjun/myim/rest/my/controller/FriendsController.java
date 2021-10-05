package cn.com.yujiangjun.myim.rest.my.controller;

import cn.com.yujiangjun.myim.rest.BaseController;
import cn.com.yujiangjun.myim.rest.bean.Response;
import cn.com.yujiangjun.myim.rest.my.dto.ChatReqDto;
import cn.com.yujiangjun.myim.rest.my.service.MyService;
import cn.com.yujiangjun.myim.rest.my.service.UserChatRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendsController extends BaseController {

    final MyService myService;
    final UserChatRecordService userChatRecordService;

    public FriendsController(MyService myService,UserChatRecordService userChatRecordService) {
        this.myService = myService;
        this.userChatRecordService=userChatRecordService;
    }

    @GetMapping("/getMyFriends")
    public Response getMyFriends(@RequestParam Integer myUserId){
        return success(myService.getMyFriends(myUserId));
    }

    @GetMapping("/getDetail")
    public Response getDetail(Integer userId){
        return success(myService.getDetail(userId));
    }

    @GetMapping("/getMyChatList")
    public Response getMyChatList(Integer myUserId){
        return success(userChatRecordService.getMyChatRecord(myUserId));
    }

    @PostMapping("/deleteMyChat")
    public Response deleteMyRecord(@RequestBody ChatReqDto reqDto){
        userChatRecordService.deleteMyRecord(reqDto);
        return success();
    }
}
