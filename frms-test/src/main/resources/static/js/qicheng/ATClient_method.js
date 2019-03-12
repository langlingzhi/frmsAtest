//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//2009-05-27 升级到V400，使用ATClient控件
//2011-09-20 整理
//           记录通信数据日志：手工创建目录 C:\ATOCX\LOG_ULTRA_ECMA
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
var isConnect_CTI = 0;
var nTimer_id;
var qicheng_url,
    login_uid,
    login_pwd,
    login_ext;
var IsCallDisconnect = 0;
var connectCTIResult = "";
var isPhoneConnected = 0;//全局  判断当前是否有通话  0 ：否、1：是
window.onunload = function() {
    $("#Prompt").val("");
};

// 控件处理函数
function Connect_CTI() {
    //var strIP = $("#txtIP").val();
    //注意：应用程序 需要将strIP 赋给 ATClient_proxy.ashx 中的变量 strCTI_url = strIP;
    //      本demo 因为未用后台代码，需要手工改写。
    var strCTI_addr = ATGetCTIInfo("IPADDR", "", 0);
    // strCTI_addr = '192.168.35.161:18000';
    strCTI_addr = qicheng_url;

    var strRet = ATConnect(strCTI_addr);
    setTimeout('Connect_CTI_result()', 100);  //异步函数,延时100s,再取结果
}

function Connect_CTI_result() {
    var strRet = ATCommandResult();
    if (strRet == "OK") {
        connectCTIResult = strRet;
        // rem("连接CTI服务器成功！");
        console.log("连接CTI服务器成功！");
        client_login();
    }
    else {
        // alert("与UltraCTI连接失败，请检查，原因：\n  1.网络连接是否正常.\n  2.UltraCTI工作是否正常.");
        alert("获取呼叫功能失败！请重新点击查看按钮或手动拨打电话！");
        // var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        // parent.layer.close(index);
    }
}

function DisConnect_CTI() {
    //document.ut_atocx.ATDisconnect();
    var strRet = ATDisconnect();
    return false;
}

//座席登录
function client_login() {
    var validity = false; // assume valid

    if (login_pwd == "") login_pwd = "";

    if (!check_empty(login_uid)) {
        alert('对不起，您的工号不正确！请联系管理员。');
        return validity;
    }
    if (!check_empty(login_ext)) {
        alert('对不起，您的座席号码不正确！请联系管理员。');
        return validity;
    }

    var strLoginGroup = "0";
    //var strLoginInfo = "COMPRESS=0;REG_GROUP=0;NOT_READY=0;";
    var strLoginInfo = "COMPRESS=0;REG_GROUP=ALL;NOT_READY=0;";
    //var strLoginInfo = "PROTOCOL=1;COMPRESS=0;REG_GROUP=0;NOT_READY=0;";
    // COMPRESS=0，1-采用压缩传输数据，0-不压缩。
    // REG_GROUP=ALL，0，G1|G2|G3；  注册ACD组的消息，包括：座席、分机、中继、队列等。
    //        G1|G2|G3-表示监控指定组。
    //        ALL-监控所有消息
    //        0或空-和本座席相关的消息
    // NOT_READY=1，1-登入后不能接听电话，需要签入ATSetReady(uid,1),签出ATSetReady(uid,0)   0-登入后可以接听电话，不要要签入ATSetReady()

    //var Result = ATLogin(login_uid, login_pwd, login_ext, "0");
    var strRet = ATLogin_ex(login_uid, login_pwd, login_ext, strLoginGroup, strLoginInfo);
    setTimeout('client_login_result()', 2000);  //异步函数,延时100s,再取结果
    nRetry_times = 0;
}

var nRetry_times = 0;
function client_login_result() {
    var validity = false; // assume valid
    var strRet = ATCommandResult();

    if (strRet == "") {
        nRetry_times += 1;
        if (nRetry_times < 20) {
            setTimeout('client_login_result()', 100);
            return;
        }
    }
    if (strRet == "OK") {
        // console.log("client_login_result：" + strRet);
        validity = true;
        isConnect_CTI = 1;
        // rem("登录CTI成功！");
        console.log("登录CTI成功！");
        nTimer_id = setTimeout('delay_1ms_timer()', 1000);
    }
    else if (strRet == "ERROR_UID")
        alert("Local:登录失败，请检查工号是否正确，请联系管理员核查！");
    else if (strRet == "ERROR_PWD")
        alert("Local:登录失败，请检查密码是否正确，请联系管理员核查！");
    else if (strRet == "ERROR_REP") {
        console.log("Local:对不起，该帐号正在使用，请联系管理员核查！");
        // alert("Local:对不起，该帐号正在使用！");
    }
    else if (strRet == "FAIL")
        alert("Local:登录失败，工号或密码不正确，请联系管理员核查！");
    else {
        // var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        // parent.layer.close(index);
        // alert("Local:登录失败(" + strRet + ")，请检查 CTI Server 是否运行正常！");
        alert("CTI :获取呼叫功能失败！请重新点击查看按钮或手动拨打电话！");
    }
    return validity;
}

//座席登出
function client_logout() {
    var validity = true; // assume valid
    if (isConnect_CTI > 0) {
        //ATLogout(login_uid);
        var Result = ATLogout_ex(login_uid);
        isConnect_CTI = 0;
        // rem("座席成功退出");
        console.log("座席成功退出");
    }
    setTimeout('ATDisconnect()', 500);
    return validity;
}

function check_empty(text) {
    return (text.length > 0); // 如果为空则返回错误
}

function delay_1ms_timer() {
    if (isConnect_CTI == -1) {
        client_login();
        return 1;  //只提示一次
    }
    nTimer_id = setTimeout('delay_1ms_timer()', 1000);
    return 1;
}

//Flex 调用JS的接口函数，函数名称必须为字母数字
function onReceivePopupInfo(EventCode, EventData,EventInfo) {
    myATClient_onReceiveCTI_EventChanged(EventCode, EventData);
    //var strDisp = "EventCode=" + EventCode + ",EventData=" + EventData+ ",EventInfo=" + EventInfo;
    //rem(strDisp);
}

//接收ATClient的消息
function myATClient_onReceiveCTI_EventChanged(EventCode, EventData) {
    var strDisp, strPrompt;
    var caller_code, ext_code, nDirection;
    var strStatus, lbl_name;
    var rc;
    switch (EventCode) {
        case 0: //显示信息
            break;

        case 1: //1：表示座席员状态发生变化，EventData 表示座席员工号
            strStatus = "";
            show_agent_status(document, EventData, strStatus);
            break;
        case 2: //表示座席员呼叫状态发生变化，EventData 表示座席员工号
            show_agent_status(document, EventData, strStatus);
            break;
        case 3: //3：表示外线状态发生变化，EventData 表示外线号码
            strStatus = "";
            show_trunk_status(document, EventData, strStatus);
            break;
        case 4: //4：表示分机状态发生变化，EventData 表示分机号
            strStatus = "";
            show_ext_status(document, EventData, strStatus);
            break;

        case 5: //5：表示其它座席传来消息，EventData 表示消息内容
            Popup_Callin(EventCode, EventData);
            break;

        case 6: //6：表示有用户呼入信息：应用程序依靠此消息弹出用户资料，EventData 表示分机号
            //if ((document.ut_atocx.ExtDirection == 2) || (document.ut_atocx.ExtDirection == 4)) break;
            Popup_Callin(EventCode, EventData);
            break;

        case 7: //7：表示有OEM呼入信息，可能是FAX、VMS、EMAIL、SMS、CHAT等
            Popup_Callin(EventCode, EventData);
            break;

        case 10: //10：表示呼叫进入排队，EventData 表示呼叫标识
            rem("呼叫进入排队" + EventData)
            break;
        case 11: //11：表示呼叫结束排队，EventData 表示呼叫标识
            rem("呼叫结束排队" + EventData)
            break;

        case 100: //100：表示与服务器的连接中断
            // strDisp = "本座席与 CTI Server 的连接出现故障，请重新登录！ErrCode=" + EventData;
            strDisp = "CTI Server: 获取呼叫功能失败！请重新点击查看按钮或手动拨打电话！";
            if (isConnect_CTI > 0) {
                isConnect_CTI = -1;
                alert(strDisp);
            }
            //Connect_CTI();
            //client_login();
            break;
    }
}

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//表示座席员状态发生变化，显示相应状态
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
function show_agent_status(frmDetails, strUid, strStatus) {

    var myUidInfo = ATGetUidInfo(strUid);
    var user_uid = myUidInfo.m_uid;
    var user_ext = myUidInfo.m_ext;
    var user_name = myUidInfo.m_name;
    strStatus = myUidInfo.m_call_status;

    if (user_name == "") user_name = user_uid;
    if (user_name == "") user_name = user_ext;

    var lbl_name = get_call_status(strStatus);
    if (strUid == login_uid) IsCallDisconnect = 1;

    //01：可工作 02：置忙 03：事后处理 04：离席
    var UidStatus = myUidInfo.m_status;
    if (UidStatus == "01") UidStatus = "可工作";
    else if (UidStatus == "02") UidStatus = "置忙";
    else if (UidStatus == "03") UidStatus = "事后处理";
    else if (UidStatus == "04") UidStatus = "离席";
    else if (UidStatus == "06") UidStatus = "未准备好";
    else if (UidStatus == "00") UidStatus = "注销";
    rem("座席状态：" + UidStatus + "，姓名：" + user_name + "，分机：" + user_ext + "，呼叫状态：" + lbl_name + "，isCHQ：" + myUidInfo.is_conf + myUidInfo.is_hold + myUidInfo.is_queue);
}

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//表示分机状态发生变化，显示相应状态
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
function show_ext_status(frmDetails, strExt, strStatus) {

    var myCallInfo = ATGetCallInfo(login_uid, strExt, "", 1);
    var strUid = myCallInfo.m_uid;  //ut_atocx.Ext_Uid;
    //不处理登录座席
    if (strUid != "") return;
    strStatus = myCallInfo.m_status;

    var lbl_name = get_call_status(strStatus);
    rem("分机状态：" + lbl_name + "，分机：" + strExt);
}

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//表示外线状态发生变化，显示相应状态
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
function show_trunk_status(frmDetails, strTrunk, strStatus) {
    var strDisp, strPrompt;
    var caller_code, ext_code, nDirection;

    var myCallInfo = ATGetCallInfo(login_uid, strTrunk, "", 2);
    strStatus = myCallInfo.m_status;

    var lbl_name = get_call_status(strStatus);
    caller_code = myCallInfo.m_caller;
    ext_code = myCallInfo.m_called;
    nDirection = myCallInfo.m_direction;
    rem("外线状态：" + lbl_name + "，主叫：" + caller_code + "，被叫：" + ext_code + "，方向：" + nDirection + "，isCHQ：" + myCallInfo.is_conf + myCallInfo.is_hold + myCallInfo.is_queue); // + "，时长：" + document.ut_atocx.TrunkTimer);
}

function get_call_status(strStatus) {
    var lbl_name;
    switch (strStatus) {
        case "01": //空闲
            lbl_name = "空闲";
            break;
        case "02": //应答
            lbl_name = "摘机";
            break;
        case "03": //振铃
            lbl_name = "振铃";
            break;
        case "04": //回铃
            lbl_name = "回铃";
            break;
        case "05": // 连接
            lbl_name = "通话";
            break;
        case "06": //断开
            lbl_name = "断开";
            break;
        case "07": //摘机
            lbl_name = "占用";
            break;
        case "08": //转移
            lbl_name = "转移";
            break;
        default: //未知
            lbl_name = "未知";
            break;
    }
    return lbl_name;
}


//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//表示弹屏态发生变化，显示相应状态
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
function Popup_Callin(EventCode, EventData) {
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //表示其它坐席传来消息
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    if (EventCode == 5) //表示其它坐席传来消息
    {
        var strShortMsg = EventData;
        var strItem = strShortMsg;
        var nPos = strShortMsg.indexOf(";");
        if (nPos > 0) {
            strItem = strShortMsg.substring(0, nPos);
            strItem = strItem.toUpperCase();
        }

        if (strItem == "SHORTMSG")//接收短消息
        {
            rem("接收公文：" + strShortMsg);
        }
        else if (strItem == "TRANSMSG")//转发工单
        {
            rem("转发工单：" + strShortMsg);
        }
        else if (strItem == "CALLBACK") //用户远程通过网络预设呼叫
        {
        }
        else {
            alert(strShortMsg);
        }
    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //表示有用户呼入信息
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    else if (EventCode == 6) //表示有用户呼入信息：应用程序依靠此消息弹出用户资料
    {
        var myCallInfo = ATGetCallInfo(login_uid, EventData, "", 1);

        var strCallid = myCallInfo.m_callid; //ut_atocx.ExtCaller;
        var strCaller = myCallInfo.m_caller; //ut_atocx.ExtCaller;
        var chnum = myCallInfo.m_ext; //ut_atocx.ExtCode;
        var strInfo = myCallInfo.m_userinfo; //ut_atocx.Ext_UserInfo; //"PINPOPUP;FILTER=xxx;"

        var strPopupUrl = "callid=" + strCallid + "&caller=" + strCaller + "&info=" + strInfo;
        rem("来电信息：" + strPopupUrl);
    }
    else if (EventCode == 7) //表示OEM传来消息
    {
        var strSubKey = EventData;
        var strMsgInfo = ATGetOEMMessage(EventData); //ut_atocx.ATGetOEMMessage(EventData); // EventData OEM消息应为 “IVRTOAGENT”
        var strInfo = "";
        var strItem = strMsgInfo;
        var nPos = strMsgInfo.indexOf(";");
        if (nPos > 0) {
            strItem = strMsgInfo.substring(0, nPos);
            strItem = strItem.toUpperCase();
        }

        //alert(strSubKey+strMsgInfo);
        if (strSubKey == "IVRTOAGENT") {
            if (strItem == "VMS_RECV")   //接收传真消息
            {
                strInfo = "您有一个新留言，单击此处可以查看";
            }
            if (strItem == "FAX_RECV")   //接收传真消息
            {
                strInfo = "您有一个新传真，单击此处可以查看";
            }
        }
        else if (strSubKey == "SMSTOAGENT") //即时消息信息
        {
            strInfo = "您有一个新短信，单击此处可以查看";
        }
        else if (strSubKey == "EMAILTOAGENT") //接收EMAIL消息
        {
            //var strCallid = GetItemValue(1, "CALLID", strMsgInfo);
            strInfo = "您有一个新邮件，单击此处可以查看," + strCallid;
        }
        else if (strSubKey == "CHATTOAGENT") //接收Webcall-chat消息
        {
            strInfo = "在线客服," + strMsgInfo;
        }
        if (strInfo.length > 0)
            rem("OEM传来消息：" + strInfo);
    }
    return false;
}


function AT_Command(strCmd,phone) {
    if (isConnect_CTI < 1) {
        alert("请先连接CTI服务器!");
        return false;
    }
    switch (strCmd) {
        case "Pickup":
            ATAnswer(login_uid, "");
            break;
        case "Hangup":
            ATHangup(login_uid, "");
            break;
        case "Pickup_dj":
            ATPickCall(login_uid,phone,"");
            break;
        case "PlaceCall": //呼叫电话
            ATPlaceCall(login_uid, phone);
            break;
        case "HoldCall":
            ATHoldCall(login_uid);
            break;
        case "RetriveCall":
            ATRetriveCall(login_uid, "");
            break;
        case "TransCall": //单步转接
            ATTranCall(login_ext, $("#txtTel_tran").val());
            break;
        case "ConsTrans": //协商转接
            ATConsTrans(login_uid, $("#txtTel_tran").val());
            break;
        case "TranOver": //协商转接完成
            ATTranOver(login_uid, "");
            break;
        case "TransIVR": //转接IVR
            var strIvr = "AC_SWITCHIVR;CALLID=;EXT=" + login_ext + ";IVRFILE=" + $("#txtIvrFile").val() + ";NODE=" + $("#txtIvrNode").val() + ";IVRMSG=自己定义;";
            ATTranCall_toIVR(login_uid,0,"",strIvr,strIvr);
            break;
        case "Conf_est": //单步建立会议
            ATConf_est(login_ext, $("#txtTel_conf").val());
            break;
        case "ConsConf": //协商会议
            ATConsConf(login_uid, $("#txtTel_conf").val());
            break;
        case "ConfOver": //协商会议完成
            ATConfOver(login_uid, "");
            break;

        case "Insert": //强插
            ATInsert(login_uid, $("#txtTel_insert").val(), 1);
            break;
        case "MoniExt":  //监听
            ATInsert(login_uid, $("#txtTel_insert").val(), 2);
            break;
        case "DisConnect":
            ATDiscCall(login_uid, $("#txtTel_insert").val(), "");
            break;

        case "SendNote":
            var strSend = "SHORTMSG;FROM=" + login_uid + ";TO=" + $("#txtDest").val() + ";CALLID=12345678;SUB=" + $("#txtMsg").val();
            ATSendMsg(login_uid, $("#txtDest").val(), strSend);
            break;
        case "SendEmail":
            var strSend = "EMAIL_SEND;GHID=" + login_uid + ";TO=" + $("#txtEmail").val() + ";SUBJ=测试邮件;MSG=" + $("#txtMsg").val();
            ATSendOEMCommand(login_uid,  "", "AGENTTOMCI", strSend);
            break;
        case "SendSms":
            var strSend = "SMS_SEND;UID=" + login_uid + ";TEL=" + $("#txtMobile").val() + ";SUBJ=测试短信;BODY=" + $("#txtMsg").val();
            ATSendOEMCommand(login_uid, "", "AGENTTOMCI", strSend);
            break;

        case "SetBusy":
            ATSetBusy(login_uid, 1, $("#txtCause").val());
            break;
        case "SetNoBusy":
            ATSetBusy(login_uid, 0, $("#txtCause").val());
            break;
        case "SetLeave": //离席
            ATSetLeaveSeat(login_uid, 1, $("#txtCause").val());
            break;
        case "SetNoLeave": //取消离席
            ATSetLeaveSeat(login_uid, 0, $("#txtCause").val());
            break;
        case "AfterWorking":  //进入事后处理
            ATSetAfterWorking(login_uid, 1, $("#txtCause").val());
            break;
        case "AfterNoWorking": //取消事后处理
            ATSetAfterWorking(login_uid, 0, $("#txtCause").val());
            break;

        default:
            alert("未处理命令：" + strCmd);
            break;
    }
    return true;
}

function btn_Command(strCmd) {
    switch (strCmd) {
        case "ClearDisp": //清除显示
            $("#Prompt").val("");
            break;
        default:
            alert("未处理命令：" + strCmd);
            break;
    }
    return true;
}

function rem(strMsg) {
    //alert(strMsg);
    var myPrompt = document.getElementById("Prompt");
    if (myPrompt != null)
        myPrompt.innerText = myPrompt.innerText + "\n" + strMsg;
}


//function $(itemID) {
//    if (document.getElementById) {
//        return document.getElementById(itemID);
//    } else {
//        return document.all(itemID);
//    }
//}
