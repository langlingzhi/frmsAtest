//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//2011.03.09 ��ʾ������Http�ؼ���Ϣ
//           ���峣�ú�������������JS����
//2012.07.12 ���Ӻ��� ATGetGroupName() ���ݹ��ţ���ţ�ȡ������    nType=0��ACD��  1��ҵ����
//           ����  is_hold,is_conf,is_queue ״̬����
//2015.01.15 ���� ATGetUidName(strUid)  peng
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//��ϯ��¼-�ǳ�

var cti_url;

function ATLogin(strUid, strPwd, strExt, strGroup, strLoginInfo) {
    var data = { cmd: "ATLogin", uid: strUid, pwd: strPwd, ext: strExt, group: strGroup, info: strLoginInfo };
    var strJson = ATClient_http(data);
    //alert(Result);
    var strReturn = strJson.ret;
    if (strReturn != "OK") strReturn = strJson.data;
    return strReturn;
}

function ATLogout(strUid) {
    var data = { cmd: "ATLogout", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
//�绰��ز���-��绰����
function ATAnswer(strUid, strCallid) {
    var data = { cmd: "ATAnswer", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATHangup(strUid, strCallid) {
    var data = { cmd: "ATHangup", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATPickCall(strUid, strExt, strCallid) {
    var data = { cmd: "ATPickCall", uid: strUid, ext: strExt, called: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATPlaceCall(strUid, strCalled) {
    var data = { cmd: "ATPlaceCall", uid: strUid, called: strCalled };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATHoldCall(strUid) {
    var data = { cmd: "ATHoldCall", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATRetriveCall(strUid, strCallid) {
    var data = { cmd: "ATRetriveCall", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATTranCall(strExt, strExt_dest) {
    var data = { cmd: "ATTranCall", ext: strExt, ext_dest: strExt_dest };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATConsTrans(strUid, strExt_dest) {
    var data = { cmd: "ATConsTrans", uid: strUid, ext_dest: strExt_dest };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATTranOver(strUid, strCallid) {
    var data = { cmd: "ATTranOver", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATConsConf(strUid, strExt_dest) {
    var data = { cmd: "ATConsConf", uid: strUid, ext_dest: strExt_dest };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATConfOver(strUid, strCallid) {
    var data = { cmd: "ATConfOver", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
//����A��ֻ�B����ͨ��ʱ��ִ�д˺���������A���ֻ�B��strExt�����ֻ�C��strExt_dest����һ�������顣
function ATConf_est(strExt, strExt_dest) {
    var data = { cmd: "ATConf_est", ext: strExt, ext_dest: strExt_dest };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATInsert(strUid, strExt, nType) {
    var data = { cmd: "ATInsert", uid: strUid, ext: strExt, type: nType };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATDiscCall(strUid, strExt, strCallid) {
    var data = { cmd: "ATDiscCall", uid: strUid, ext: strExt, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATCallSwap(strUid, strCallid) {
    var data = { cmd: "ATCallSwap", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATCallReconnect(strUid, strCallid) {
    var data = { cmd: "ATCallReconnect", uid: strUid, callid: strCallid };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATSendDtmf(strUid, strDTMF) {
    var data = { cmd: "ATSendDtmf", uid: strUid, callid: strDTMF };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATDialDigit(strUid, strDigit) {
    var data = { cmd: "ATDialDigit", uid: strUid, callid: strDigit };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATTranCall_toIVR(strUid, strCh, strExt, strInfo) {
    var data = { cmd: "ATTranCall_toIVR", uid: strUid, ch: strCh, ext: strExt, info: strInfo };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}
function ATJoinIVR_toCnf(strUid, strCh, strExt, strInfo) {
    var data = { cmd: "ATJoinIVR_toCnf", uid: strUid, ch: strCh, ext: strExt, info: strInfo };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

//��ϯԱ״̬��ز���
function ATSetBusy(strUid, nBusy_state, strCause) {
    var data = { cmd: "ATSetBusy", uid: strUid, busy_state: nBusy_state, cause: strCause };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATSetLeaveSeat(strUid, nState, strCause) {
    var data = { cmd: "ATSetLeaveSeat", uid: strUid, state: nState, cause: strCause };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATSetAfterWorking(strUid, nState, strCause) {
    var data = { cmd: "ATSetAfterWorking", uid: strUid, state: nState, cause: strCause };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATSetReady(strUid, nState) {
    var data = { cmd: "ATSetReady", uid: strUid, state: nState };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}


function ATSendMsg(strUid, strUid_to, strMsg) {
    var data = { cmd: "ATSendMsg", uid: strUid, to: strUid_to, info: strMsg };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATSendOEMCommand(strUid, strUid_to, strSubKey, strMsg) {
    var data = { cmd: "ATSendOEMCommand", uid: strUid, to: strUid_to, key: strSubKey, info: strMsg };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

//��ȡ�����Ϣ����
function ATGetPopup(strUid) {
    var data = { cmd: "ATGetPopup", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

function ATGetOEMMessage(strUid) {
    var data = { cmd: "ATGetOEMMessage", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//add by gaoww 20150116 ������·��������
//����˵����strCallid-������·���ݵĺ��б�ţ�Ϊ��ʱ����Ϊ��ǰ���ŵĺ���
//          strKey-������·���ݵ�Key������֧�ֶ��Key1,key2...�����Key�ö��ŷָ�
//          strValue-������·����Key����Ӧ��Valueֵ�����Value1|Value2...�����Value�����߷ָ�
function ATSetUserInfo(strUid, strCallid, strKey, strValue) {
    var data = { cmd: "ATSetUserInfo", uid: strUid, callid: strCallid, key: strKey, value: strValue };
    var strJson = ATClient_http(data);
    var strReturn = strJson.ret;
    return strReturn;
}

function ATGetTrunk_list(strUid) {
    var data = { cmd: "ATGetTrunk_list", uid: strUid };
    var strJson = ATClient_http(data);
    //var strReturn = new typeUidInfo();
    var strReturn = "";  //2015-04-29 peng �޸�bug
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//UID={0};NAME={1};EXT={2};STATUS={3};CALLSTATUS={4};", myAgent.Uid, myAgent.Name, myAgent.Ext, myAgent.Status, myAgent.CallStatus);
function ATGetUidInfo(strUid) {
    var data = { cmd: "ATGetUidInfo", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = new typeUidInfo();
    var strRet = strJson.ret;
    if (strRet == "OK") {
        var strData = strJson.data;
        strReturn.m_uid = GetItemValue(1, "UID", strData);
        strReturn.m_name = GetItemValue(1, "NAME", strData);
        strReturn.m_ext = GetItemValue(1, "EXT", strData);
        strReturn.m_status = GetItemValue(1, "STATUS", strData);
        strReturn.m_call_status = GetItemValue(1, "CALLS", strData);
        strReturn.m_groups = GetItemValue(1, "GROUPS", strData);
        strReturn.is_hold = GetItemValue(1, "ISHOLD", strData);
        strReturn.is_conf = GetItemValue(1, "ISCONF", strData);
        strReturn.is_queue = GetItemValue(1, "ISQUEUE", strData);
        strReturn.n_wtime = GetItemValue(1, "NWTIME", strData);
    }
    return strReturn;
}

function ATGetUidInfo_byExt(strExt) {
    var data = { cmd: "ATGetUidInfo_byExt", ext: strExt };
    var strJson = ATClient_http(data);
    var strReturn = new typeUidInfo();
    var strRet = strJson.ret;
    if (strRet == "OK") {
        var strData = strJson.data;
        strReturn.m_uid = GetItemValue(1, "UID", strData);
        strReturn.m_name = GetItemValue(1, "NAME", strData);
        strReturn.m_ext = GetItemValue(1, "EXT", strData);
        strReturn.m_status = GetItemValue(1, "STATUS", strData);
        strReturn.m_call_status = GetItemValue(1, "CALLS", strData);
        strReturn.m_groups = GetItemValue(1, "GROUPS", strData);
        strReturn.is_hold = GetItemValue(1, "ISHOLD", strData);
        strReturn.is_conf = GetItemValue(1, "ISCONF", strData);
        strReturn.is_queue = GetItemValue(1, "ISQUEUE", strData);
    }
    return strReturn;
}

//��ȡ������Ϣ,strParm�ĺ��壺nType=0-���� 1-��ϯ 2-�м�
//CALLID={0};CALLER={1};CALLED={2};DIR={3};STATUS={4};EXT={5};TRUNK={6};INFO={7};UID={8};TIMER={9};
function ATGetCallInfo(strUid, strParm, strKey, nType) {
    var data = { cmd: "ATGetCallInfo", uid: strUid, ext: strParm, key: strKey, ntype: nType };
    var strJson = ATClient_http(data);

    var strReturn = new typeCallInfo();
    var strRet = strJson.ret;
    if (strRet == "OK") {
        var strData = strJson.data;
        strReturn.m_callid = GetItemValue(1, "CALLID", strData);
        strReturn.m_caller = GetItemValue(1, "CALLER", strData);
        strReturn.m_called = GetItemValue(1, "CALLED", strData);
        strReturn.m_direction = parseInt(GetItemValue(1, "DIR", strData));
        strReturn.m_status = GetItemValue(1, "STATUS", strData);
        strReturn.m_ext = GetItemValue(1, "EXT", strData);
        strReturn.m_trunk = GetItemValue(1, "TRUNK", strData);
        strReturn.m_userinfo = GetItemValue(1, "INFO", strData);
        strReturn.m_uid = GetItemValue(1, "UID", strData);
        strReturn.m_stime = GetItemValue(1, "TIME", strData);
        strReturn.is_hold = GetItemValue(1, "ISHOLD", strData);
        strReturn.is_conf = GetItemValue(1, "ISCONF", strData);
        strReturn.is_queue = GetItemValue(1, "ISQUEUE", strData);
        strReturn.m_trunkcalled = GetItemValue(1, "TRUNKCALLED", strData); //20150121 add by gaoww 20150309 ���ӻ�ȡtrunkcalled������
    }
    return strReturn;
}

// ���ݹ��ţ���ţ�ȡ������
// strUid����-����strGroupȡ������  ��Ϊ��-ȡ��ϯ����������
// strGroup ������� | ����
//nType=0��ACD��  1��ҵ���� 2��chat�飬ȡ�����ټ��ҵ����
function ATGetGroupName(strUid, strGroup, nType) {
    var data = { cmd: "ATGetGroupName", uid: strUid, group: strGroup, ntype: nType };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//���ݹ��ţ�ȡ��ϯԱ����
function ATGetUidName(strUid) {
    var data = { cmd: "ATGetUidName", uid: strUid };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//��ȡCTI��Ϣ,strKey�ĺ��壺strKey="IPADDR",CTI �����ַ,����:IP:PORT,strParm,nType=δ��
function ATGetCTIInfo(strKey, strParm, nType) {
    var data = { cmd: "ATGetCTIInfo", key: strKey, ext: strParm, ntype: nType };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//��ȡ ָ��ACD�� ������ϯ����
//strGroup��ָ��ҵ���飬��-��ȡ�����飻GP1-ָ���飻GP1|GP2|GP5-�����   
//���أ�UID1|UID2|uid3|UID4...
function ATGetUid_Online(strUid, strGroup) {
    var data = { cmd: "ATGetUid_Online", uid: strUid, group: strGroup };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}


// ϵͳʵʱ��Ϣ-һ��,ͨ�����ݿ⣬��CDR��ȡ 
// <param name="strDest">Ŀ�꣺��-���У� UID1,UID2,UID3-ָ�����ţ�Gxx1,Gxx2-ָ��ACD��ţ�</param>
// <param name="strKey">A-A��ָ��  B-A��ָ��  ��-����</param>
// <param name="strSdate">��ѯ���ڣ�Ĭ�ϵ���</param>
// <returns>OK;CALLS={0};CALLS_IN={1};CALLS_OUT={2};QUEUES={3};QFAIL={4};ANS={5};OP_ANS={6};PC_LEN={7};CALL_LEN={8};CLEN_IN={9};CLEN_OUT={10};IDLE_LEN={11};LEAVE_LEN={12};BUSY_LEN={13};AFTER_LEN={14};</returns>
// ָ��˵��
//    A��0-����������1-����������2-����������3-�ŶӴ�����4-�Ŷ�ʧ������5-����ͨ������,6-������ϯ��������7-ƽ��ͨ��ʱ��  - ϵͳ������strDest ��������
//    B��10-��ͨ��ʱ����11-����ͨ��ʱ����12-����ͨ��ʱ����13-��¼ʱ����14-��ϯʱ����15-��æʱ����16-�º���ʱ��        - ���˲�����strDest ������
function RPTGet_CDRInfo(strDest, strKey, strDate) {
    var data = { cmd: "RPTGet_CDRInfo", dest: strDest, key: strKey, sdate: strDate };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

// ϵͳʵʱ��Ϣ-ʵʱ,ͨ��CTI ���
// <param name="strDest">Ŀ�꣺��-���У� UID1,UID2,UID3-ָ�����ţ�Gxx1,Gxx2-ָ��ACD��ţ�</param>
//    strKey = RPT_BASE
//      ����ϵͳָ�꣺FAIL;��OK;TRUNKS={0};IVRS={1};UIDS={2};QUEUES={3};ONLINE={4};IDLE={5};LEAVE={6};BUSY={7};AFTER={8};
//                    C������ռ������IVRռ��������ϯռ�������Ŷ��� 
//                    D��������ϯ������������ϯ������ϯ��ϯ������æ��ϯ�����º�����ϯ
//    strKey = RPT_UID
//      ������ϯԱָ�꣺FAIL;��
//                      OK; ����=|��¼ʱ��|��ϯʱ��|��æʱ��|�º���ʱ��|;   --��ȡCTI���ߵģ�����ͨ��CDR��ȡ
//                      �磺OK;8600=|1|2|3|4|;8601=|12|13|14|15|;
function RPTGet_RTInfo(strDest, strKey) {
    var data = { cmd: "RPTGet_RTInfo", dest: strDest, key: strKey };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

// ϵͳ��ϯԱ״̬-ʵʱ,ͨ��CTI ���
// <param name="strDest">Ŀ�꣺��-���У� UID1,UID2,UID3-ָ�����ţ�Gxx1,Gxx2-ָ��ACD��ţ�</param>
// ���أ�[{����1:UIDInfo,����2:UIDInfo,����3:UIDInfo,����.}]
//  UIDInfo ��ʽ��UID={0};NAME={1};EXT={2};STATUS={3};CALLS={4};GROUPS={5};REG_GROUP={6};LTIME={7};CALLID={0};CALLER={1};CALLED={2};DIR={3};CALL_STATUS={4};TRUNK={5};INFO={6};
function RPTGet_UIDInfo(strDest, strKey) {
    var data = { cmd: "RPTGet_UIDInfo", dest: strDest, key: strKey };
    var strJson = ATClient_http(data);
    var strReturn = "";
    var strRet = strJson.ret;
    if (strRet == "OK") strReturn = strJson.data;
    return strReturn;
}

//通过Http 协议，调用ATClient接口
function ATClient_http(data) {
    var strResult = "";
    // var cti_url = "http://192.168.35.162:7096/cc_softcall/";//"http://222.73.156.49:7096/cc_softcall/";
    var strUrl = cti_url + "ATClient.ashx?jsoncallback=?";
    $.ajax({
        async :false,
        type: "get",
        url:strUrl,
        data: data,
        dataType: "jsonp",
        success: function(data){
            strResult = data;
        }
    });
    return strResult;
}

//������רΪ Json
function toJson(obj) {
    var strReturn = "";
    for (i in obj) {
        if (strReturn.length > 0) strReturn += ",";
        strReturn +=  '"' + i.replace(/\"/g, "\\\"") + '":"' + obj[i].replace(/\"/g, "\\\"") + '"';
    }
   strReturn = "{" + strReturn + "}";;
   return strReturn ;

}
//************************************
//���нṹ
//************************************
function typeCallInfo() {
    this.m_callid = "";
    this.m_caller = "";
    this.m_called = "";
    this.m_direction = 0;  //����  0-δ֪ 1-���� 2-����  
    this.m_status = "01";    //״̬��0��ʾ״̬δ�����仯
    //����״̬��Ϊ���¼��֣�
    //        01������״̬
    //        02��ժ��״̬
    //        03������״̬
    //        04������״̬
    //        05��ͨ��״̬
    //        06���Ͽ�״̬
    //        07��ռ��״̬
    this.m_stime = "";       //���п�ʼʱ��
    this.m_ext = "";
    this.m_trunk = "";
    this.m_trunkcalled = ""; //add by gaoww 20150121 ����trunkcalled���Ի�ȡ��ע����Ҫ��Ͻ���֮���cti����
    this.m_userinfo = "";    //��ʾCTI����������������·��Ϣ��������IVR��Ϣ�����û�����Ķ����ŵȡ�
    this.m_uid = "";         //����ͨ����Uid
    this.m_timer = "";
    //2012-07-16  ����  is_hold,is_conf,is_queue ״̬����
    this.is_hold = "00";     //��ʾ�Ƿ��ڱ���״̬
    this.is_conf = "00";     //��ʾ�Ƿ��ڻ���״̬
    this.is_queue = "00";    //��ʾ�Ƿ����Ŷ�״̬
}

//��ϯԱ�ṹ
function typeUidInfo() {
    this.m_uid = "";
    this.m_name = "";
    this.m_ext = "";
    this.m_roles = 0;
    this.m_status = "00";    //״̬��0��ʾ״̬δ�����仯
    //00����ע��
    //01���ɹ���
    //02����æ
    //03���º���
    //04����ϯ
    //05��Ԥ��ռ�ã�������״̬δ�䣬��ԭ
    this.m_call_status = "00";   //״̬��0��ʾ״̬δ�����仯
    this.m_groups = "";          //ACD���
    this.is_hold = "00";         //��ʾ�Ƿ��ڱ���״̬
    this.is_conf = "00";         //��ʾ�Ƿ��ڻ���״̬
    this.is_queue = "00";         //��ʾ�Ƿ����Ŷ�״̬
}

//���ú���
function GetItemValue(nItem, strItemName, strLine) {
    var nPos;
    var temp_char;
    var strReturn = "";
    var strBody;
    var strData;

    strData = strLine;
    strLine = strLine.toUpperCase();
    strItemName = strItemName.toUpperCase();

    if (nItem == 0) {
        nPos = strLine.indexOf(";");
        if (nPos > 0) {
            strReturn = strLine.substring(0, nPos);
            //strReturn.Trim();
            strReturn = strReturn.toUpperCase();
        }
    }
    if (nItem == 1) {
        nPos = strLine.indexOf(";");
        if (nPos >= 0) {
            strBody = strLine.substring(0, nPos);
            nPos = strBody.indexOf("=");
            if (nPos >= 0)  //����XXX=XXX; ȡ�����ַ�
            {
                strBody = strLine;
            }
            else //������XXX=XXX; ȡ;֮���ַ�
            {
                nPos = strLine.indexOf(";");
                strBody = strLine.substr(nPos + 1);
            }
        }
        else {
            strBody = strLine;
        }
        nPos = -1;
        while (true) {
            nPos = strBody.indexOf(strItemName, nPos + 1);
            if (nPos >= 0) {
                /*
                if(nPos>0) //ǰһ���ַ�����A-Z,a-z
                {
                temp_char = strBody[nPos-1];  
                if(Char.IsLetter(temp_char)) continue;
                }//��һ���ַ�����A-Z,a-z
                temp_char = strBody[nPos+strItemName.Length-1];  
                if(Char.IsLetter(temp_char)) 
                {
                temp_char = strBody[nPos+strItemName.Length];  
                if(Char.IsLetter(temp_char)) continue;
                }
                */
                strBody = strBody.substr(nPos);
                nPos = strBody.indexOf(";");
                if (nPos > 1) {
                    strBody = strBody.substring(0, nPos);
                    nPos = strBody.indexOf("=");
                    if (nPos > 0) {
                        strReturn = strBody.substr(nPos + 1);
                        //strReturn.Trim();
                        nPos = strLine.indexOf(strReturn);
                        if (nPos > 1) {
                            strReturn = strData.substr(nPos, strReturn.length);
                        }
                    }
                }
            }
            break;
        } //end while 
    }
    if (nItem == 2) {
        nPos = strLine.indexOf(";");
        if (nPos >= 0) {
            strBody = strLine.substring(0, nPos);
            nPos = strBody.indexOf("=");
            if (nPos >= 0)  //����XXX=XXX; ȡ�����ַ�
            {
                strBody = strLine;
            }
            else //������XXX=XXX; ȡ;֮���ַ�
            {
                nPos = strLine.indexOf(";");
                strBody = strLine.substr(nPos + 1);
            }
        }
        else {
            strBody = strLine;
        }
        nPos = -1;
        while (true) {
            nPos = strBody.indexOf(strItemName, nPos + 1);
            if (nPos >= 0) {
                strBody = strBody.substr(nPos);
                nPos = strBody.indexOf("=");
                if (nPos > 1) {
                    strReturn = strBody.substr(nPos + 1);
                    //strReturn.Trim();
                    //	strReturn.MakeUpper();
                    var nLen = strReturn.length;
                    nPos = strLine.indexOf(strReturn);
                    if (nPos > 1) {
                        strReturn = strData.substr(nPos, nLen);
                        if (strReturn.lastIndexOf(";") == (nLen - 1)) //ȥ������ ';'
                            strReturn = strReturn.substr(0, (nLen - 1));
                    }
                }
            }
            break;
        } //end while
    }
    if (nItem == 3) {
        nPos = strLine.indexOf(";");
        if (nPos >= 0) {
            strBody = strLine.substring(0, nPos);
            nPos = strBody.indexOf("=");
            if (nPos >= 0)  //����XXX=XXX; ȡ�����ַ�
            {
                strBody = strLine;
            }
            else //������XXX=XXX; ȡ;֮���ַ�
            {
                nPos = strLine.indexOf(";");
                strBody = strLine.substr(nPos + 1);
            }
        }
        else {
            strBody = strLine;
        }
        nPos = -1;
        while (true) {
            nPos = strBody.indexOf(strItemName, nPos + 1);
            if (nPos >= 0) {
                strReturn = strBody.substr(nPos + 1);
                //strReturn.trim();
            }
            break;
        }
    } //end while
    return strReturn;
}