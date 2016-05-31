package com.xnjr.app.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.customer.ao.ICustomerAO;
import com.xnjr.app.enums.EUserKind;
import com.xnjr.app.security.ao.IUserAO;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController {
    @Autowired
    ICustomerAO customerAO;

    @Autowired
    IUserAO userAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(
            @RequestParam("mobile") String mobile,
            @RequestParam(value = "idKind", required = false) String idKind,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam("realName") String realName,
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "remark", required = false) String remark) {
        return userAO.addUser(mobile, idKind, idNo, realName, userReferee, this
            .getSessionUser().getUserName(), remark, EUserKind.F1.getCode());
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCustomerPage(
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "idKind", required = false) String idKind,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "limit", required = false) String limit) {
        return userAO.queryUserPage(loginName, EUserKind.F1.getCode(), level,
            userReferee, mobile, idKind, idNo, realName, null, status, null,
            start, limit);
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCustomerList(
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "idKind", required = false) String idKind,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "level", required = false) String level) {
        return userAO.queryUserList(loginName, EUserKind.F1.getCode(), level,
            userReferee, mobile, idKind, idNo, realName, null, status, null);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object customerDetail(@RequestParam("userId") String userId) {
        return userAO.getUser(userId);
    }

}
