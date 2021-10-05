package cn.com.yujiangjun.myim.rest.releation.controller;

import cn.com.yujiangjun.myim.rest.BaseController;
import cn.com.yujiangjun.myim.rest.bean.Response;
import cn.com.yujiangjun.myim.rest.releation.service.RelationService;
import cn.com.yujiangjun.myim.rest.releation.UserGroupReqDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relation")
public class AddFriendController extends BaseController {

    final RelationService myService;

    public AddFriendController(RelationService myService) {
        this.myService = myService;
    }

    @GetMapping("/addUser")
    public Response checkFriendOrGroupExist(UserGroupReqDto reqDto){
        myService.addFriend(reqDto);
        return success();
    }

    @GetMapping("/getMyFriendApplyRecord")
    public Response getMyFriendApplyRecord(Integer myUserId){
        return success(myService.getMyFriendApplyRecord(myUserId));
    }


    @GetMapping("/passAddFriend")
    public Response passAddFriend(Integer recordId){
        myService.passAddFriend(recordId);
        return success();
    }

    @GetMapping("/rejectAddFriend")
    public Response rejectAddFriend(Integer recordId){
        myService.rejectAddFriend(recordId);
        return success();
    }

}
