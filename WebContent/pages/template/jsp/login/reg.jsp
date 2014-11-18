<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<title>会员注册</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/reg.js" charset="UTF-8"></script>
</head>
<body>
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="doreg.do" id="registerForm">
    <input type="hidden" id="sign" value="" name="sign">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>会员注册</span></li> 
                <li style="float:right;"><span style="line-height: 30px;"><a  class="title_link" href="#">勤蜂易缴是什么?</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a   class="title_link" href="#">会员享有哪些服务？</a>&nbsp;&nbsp;&nbsp;&nbsp;<a  class="title_link" href="#">会员隐私与资金安全</a>&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td height="40" width="100" align="right">登录名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" maxlength="40" value="" name="accountNumber" class="on-show" autocomplete="off" id="accountNumber"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="accountNumberTip" style="width:250px"></div></td>
                    </tr>

                   


                    <tr>
                        <td height="40" align="right">真实姓名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="realName" class="on-show" autocomplete="off" maxlength="50" id="realName"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="realNameTip" style="width:250px"></div></td>
                    </tr>
					<tr>
                        <td height="40" align="right">注册人类型：</td>
                        <td align="left" colspan="2">
                        <select class="zc_city" default="" id="registType" name="registType">
                                
                                    <option value="0">
                                        家庭用户
                                    </option>
									  <option value="1">
                                        单位用户
                                    </option>
									  <option value="2">
                                        个体工商户
                                    </option>
                                
                        </select></td>
                    </tr>
                   
                    <tr>
                        <td height="40" align="right">所在城市：</td>
                        <td align="left" colspan="2">
                        <select class="zc_city"  id="areaId" name="areaId">
                                
                                    <option value="310000">
                                        内蒙古
                                    </option>
                                
                                    <option value="710000">
                                        台湾
                                    </option>
                                
                                    <option value="510000">
                                        四川
                                    </option>
                                
                                    <option value="520000">
                                        贵州
                                    </option>
                                
                                    <option value="530000">
                                        云南
                                    </option>
                                
                                    <option value="540000">
                                        西藏
                                    </option>
                                
                                    <option value="110000">
                                        北京
                                    </option>
                                
                                    <option value="120000">
                                        天津
                                    </option>
                                
                                    <option value="130000">
                                        河北
                                    </option>
                                
                                    <option value="140000">
                                        山西
                                    </option>
                                
                                    <option value="150000">
                                        内蒙古
                                    </option>
                                
                                    <option value="210000">
                                        辽宁
                                    </option>
                                
                                    <option value="220000">
                                        吉林
                                    </option>
                                
                                    <option value="230000">
                                        黑龙江
                                    </option>
                                
                                    <option value="460000">
                                        海南
                                    </option>
                                
                                    <option value="320000">
                                        江苏
                                    </option>
                                
                                    <option value="330000">
                                        浙江
                                    </option>
                                
                                    <option value="340000">
                                        安徽
                                    </option>
                                
                                    <option value="350000">
                                        福建
                                    </option>
                                
                                    <option value="360000">
                                        江西
                                    </option>
                                
                                    <option value="370000">
                                        山东
                                    </option>
                                
                                    <option value="410000">
                                        河南
                                    </option>
                                
                                    <option value="420000">
                                        湖北
                                    </option>
                                
                                    <option value="430000">
                                        湖南
                                    </option>
                                
                                    <option value="440000">
                                        广东
                                    </option>
                                
                                    <option value="450000">
                                        广西
                                    </option>
                                
                                    <option value="610000">
                                        陕西
                                    </option>
                                
                                    <option value="620000">
                                        甘肃
                                    </option>
                                
                                    <option value="630000">
                                        青海
                                    </option>
                                
                                    <option value="640000">
                                        宁夏
                                    </option>
                                
                                    <option value="650000">
                                        新疆
                                    </option>
                                
                                    <option value="810000">
                                        香港
                                    </option>
                                
                                    <option value="820000">
                                        澳门
                                    </option>
                                
                                    <option value="500000">
                                        重庆
                                    </option>
                                
                        </select></td>
                    </tr>


                    <tr>
                        <td height="40" align="right">密码：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="password" maxlength="32" name="passwd" class="on-show" autocomplete="off" id="passwd"> 
                                       <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="passwdTip" style="width:250px"></div></td>
                    </tr>
                   
                    <tr>
                        <td height="40" align="right">确认密码：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="password" class="on-show" maxlength="32" name="confirmPassword" autocomplete="off" id="confirmPassword">
                                            <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="confirmPasswordTip" style="width:250px"></div></td>
                    </tr>

                   
                    <tr>
                        <td height="40" colspan="3">
                            <div id="tishixinxi">为方便您找回密码及获取交易凭证，建议选填以下信息:</div>
                        </td>
                    </tr>
                    <tr>
                        <td height="40" align="right">E-mail：</td>
                        <td align="left" colspan="2">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="email" class="on-show" autocomplete="off" maxlength="50" id="email"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                   
                    <tr>
                        <td height="40" align="right">手机：</td>
                        <td align="left" colspan="2">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text"  name="mobilePhone" class="on-show" autocomplete="off" maxlength="11" id="mobilePhone"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                  
                    <tr>
                        <td align="right">验证码:</td>
                        <td>
                            <table>
                                <tbody><tr>
                                    <td width="94" align="left">
                                        <input type="text" style="padding-left: 3px;" maxlength="4" size="10" class="loginform" name="verify" id="verify">
                                    </td>
                                    <td width="70" align="left">
                                    <a href="javascript:changeVerifyFP();">
                                     <img src="getVerify.do" name="loginValidateImg" id="loginValidateImg" title="看不清？点击图片刷新验证码" />                                        </a>                                    </td>
                                    </a>
                                   
                                </tr>
                            </tbody></table>
                        </td>
                        <td><div id="verifyTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3"><input id="btnreg" name="btnreg"  type="submit" class="dk_pay1" value="同意以下协议并提交"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3">
                        <div class="onError">${message}</div>
                       </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="fm-item1">
            <div class="fm-license">
              
                    <div class="xieyi">  <h3>勤蜂易缴会员服务协议：</h3></div>
                
                <textarea style="font-size: 12px" readonly="readonly" id="xy">第一条 总则
“E缴365”是北京勤蜂易缴电子商务有限公司通过移动互联网为智能手机用户提供的生活缴费移动服务客户端，旨在为全网用户带来便捷、易用、实惠的缴费体验。为明确双方的权利和义务，甲方（个人用户）、乙方（勤蜂易缴）本着平等互利的原则，就“勤蜂易缴缴费”服务相关事宜达成本协议。
第二条 甲方权利、义务
一、权利
（一）甲方使用本业务即表示已经充分阅读、理解并接受本协议的全部内容，自愿申请使用乙方提供的生活缴费服务。
（二）经乙方审查通过并成为注册用户后，甲方有权使用乙方提供的相应服务。
（三）甲方有权办理应用服务取消手续。
（四）甲方对乙方生活缴费服务如有疑问、建议或意见时，可拨打乙方客服电话进行咨询和投诉。
二、义务
（一）甲方办理并使用乙方“XX缴费”提供的服务，需要通过电子方式签署并遵守本协议。
（二）甲方办理注册、变更、注销等手续应保证提供的资料真实、准确、完整、有效。对于因甲方提供的信息不真实或不完整所造成的损失由甲方承担。
（三）甲方办理应用申请、签约、注销、变更等手续，应保证提供的资料真实、准确、完整。对于因甲方提供的信息不真实或不完整所造成的损失由甲方承担。
（四）甲方必须妥善保管好账户信息，包括手机号码、登陆密码等，除在乙方办理业务使用之外，勿向其他应用、机构及人员等泄露个人信息。甲方应避免使用姓名、生日、身份证号、电话号码或有规律的信息作为登陆密码。对于因甲方保管不善造成的信息泄露或被他人使用所造成的损失由甲方承担。
（五）该应用支付服务由中国银联提供支持，甲方须遵守中国银联用户协议及相关管理规定。甲方因未按照银联的业务规定正确执行而造成支付失败，该笔交易将被终止，乙方不承担任何责任。
（六）甲方因乙方系统故障及其他原因造成差错或者获取不当利得的，甲方应当配合处理因上述原因导致的问题。
（七）甲方承诺自己在使用乙方提供的服务时，所有行为均遵守国家法律法规和乙方的相应规定以及各种社会公共利益或社会道德。甲方从事非法活动或不正当交易产生的一切后果和责任将由甲方独立承担。
第三条 乙方权利、义务
一、权利
（一）乙方有权根据甲方资信情况或交易行为，决定是否受理甲方的公共事业缴费业务。乙方可以根据甲方注册的信息等要素对甲方交易权限及身份进行核实。
（二）乙方有权根据自身业务发展调整缴费服务项目，增加、减少或撤销相关服务。乙方有权对公共事业缴费功能进行升级、优化或改造。
（三）由于甲方提供的账单逾期、信息有误或者由于公共事业单位系统问题导致无法缴费、产生逾期费用及其他损失的，乙方不承担任何责任。由于公共事业单位系统或其他原因造成缴费失败，乙方将告知甲方失败原因并在对账无误后向银联申请退款，款项具体到账时间由甲方使用的转出账户所在银行决定，由此产生的风险及损失与乙方无关。
（四）乙方有权在法律法规许可和甲方授权的范围内使用甲方的资料和相关数据。甲方存在不遵守乙方有关业务规定或者存在恶意操作、诋毁、损害乙方声誉等情况时，乙方有权单方面限制或终止甲方的账户（含代金券账户）及其他用户权限。
（五）乙方因以下情况没有正确执行甲方提出的缴费交易指令，不承担任何责任：
1、乙方接收到的指令信息不明、存在乱码、不完整等。
2、甲方使用银联支付失败。
3、甲方代金券账户不足或被冻结。
4、甲方未能按照乙方的有关业务规定正确操作。
5、客户的行为出于欺诈或其他非法目的。
6、不可抗力或其他不属乙方过失的情况。
（六）代金券为乙方在一定时期内奖励客户的营销活动，乙方有权对活动的时间、规则进行调整、修改或撤销，在规定时间内若甲方未消费完产生的代金券，乙方有权对代金券进行清空处理或转化成其他奖励方式。
（七）基于运行、交易安全等需要，乙方有权根据自己的判断在未经通知的情况下，暂时或永久拒绝甲方使用本协议约定服务，且无需承担任何责任。
二、义务
（一）乙方有义务在技术上确保客户端的安全、有效运行，保证甲方正常使用本协议约定的服务。在乙方系统正常运行情况下，乙方负责及时准确地处理甲方发送的缴费指令，并及时向甲方提供查询交易记录、代金券、账户管理等服务。但不承担通讯、停电、系统遭到人为恶意攻击、公共事业单位系统问题、银联及银行问题、不可抗力等非乙方原因引起的交易中断、错误等责任。
（二）乙方对甲方提供的注册信息及交易信息等有保密义务，同时有权根据法律法规向有权查询的法定机构或为了完成交易指令向其他第三方提供。
（三）乙方负责向甲方提供公共事业缴费业务咨询服务，并通过乙方的官方渠道公布功能介绍、热点解答、交易规则等内容。
（四）乙方承诺不会以任何形式使用、挪用、占有甲方用于缴纳公共事业费用的资金。
第四条 法律使用条款
（一）本协议的成立、生效、履行，均适用中华人民共和国法律；法律无明文规定的，可适用通行的金融惯例。
（二）本协议是乙方的其他既有协议和合约的补充而非替代文件，如本协议与其他既有协议和约定有冲突，涉及公共事业缴费业务内容的，应以本协议为准。
第五条 协议的效力和生效
本协议的任何条款如因任何原因而被确认无效，都不影响本协议其他条款的效力。本协议自乙方账户申请成功起生效。
第六条 协议的解释
本协议的解释权属于勤蜂易缴

</textarea>
            </div>
        </div>
    </div>
</form>

        <!--右侧主页面-->
    </div>

</div>   
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>