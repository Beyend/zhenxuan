webpackJsonp([44],{"/nqF":function(a,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s={name:"addAddress",data:function(){return{checked:!0,userId:localStorage.getItem("userId"),area:"",detailaddress:"",username:"",telephone:""}},methods:{addAddress:function(){var a=1;this.checked||(a=0);var e=this,s={userid:e.userId,type:a,area:e.area,detailaddress:e.detailaddress,username:e.username,telephone:e.telephone},l=t("mw3O");e.$ajax.post("address/addaddress",l.stringify(s),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then(function(a){"200"==a.data.meta.code?(e.$toast({message:"添加收货地址成功!",position:"bottom"}),e.$router.back(-1)):e.$toast({message:a.data.meta.msg,position:"bottom"})}).catch(function(a){})}}},l={render:function(){var a=this,e=a.$createElement,t=a._self._c||e;return t("div",{attrs:{id:"addAddress"}},[t("div",{staticClass:"header"},[t("div",{staticClass:"van-hairline--bottom van-nav-bar"},[t("div",{staticClass:"van-nav-bar__left"},[t("i",{staticClass:"van-icon van-icon-arrow van-nav-bar__arrow",staticStyle:{color:"#000 !important"},on:{click:function(e){a.$router.back(-1)}}})]),a._v(" "),t("div",{staticClass:"van-ellipsis van-nav-bar__title"},[a._v("编辑收货地址")]),a._v(" "),t("div",{staticClass:"van-nav-bar__right",on:{click:a.addAddress}},[a._v("\n          确定\n        ")])])]),a._v(" "),t("div",{staticClass:"login_box"},[t("div",{staticClass:"van-cell-group van-hairline--top-bottom"},[t("div",{staticClass:"van-cell van-hairline van-field"},[t("div",{staticClass:"van-cell__value"},[t("div",{staticClass:"van-field__body"},[t("input",{directives:[{name:"model",rawName:"v-model",value:a.username,expression:"username"}],staticClass:"van-field__control",attrs:{type:"text",maxlength:"15",placeholder:"收货人"},domProps:{value:a.username},on:{input:function(e){e.target.composing||(a.username=e.target.value)}}})])])]),a._v(" "),t("div",{staticClass:"van-cell van-hairline van-field"},[t("div",{staticClass:"van-cell__value"},[t("div",{staticClass:"van-field__body"},[t("input",{directives:[{name:"model",rawName:"v-model",value:a.telephone,expression:"telephone"}],staticClass:"van-field__control",attrs:{type:"tel",placeholder:"手机号码"},domProps:{value:a.telephone},on:{input:function(e){e.target.composing||(a.telephone=e.target.value)}}})])])]),a._v(" "),t("div",{staticClass:"van-cell van-hairline van-field"},[t("div",{staticClass:"van-cell__value"},[t("div",{staticClass:"van-field__body"},[t("input",{directives:[{name:"model",rawName:"v-model",value:a.area,expression:"area"}],staticClass:"van-field__control",attrs:{type:"text",placeholder:"地区"},domProps:{value:a.area},on:{input:function(e){e.target.composing||(a.area=e.target.value)}}})])])]),a._v(" "),t("div",{staticClass:"van-cell van-hairline van-address-edit-detail"},[t("div",{staticClass:"van-cell__value van-cell__value--alone"},[t("div",{staticClass:"van-cell van-hairline van-field"},[t("div",{staticClass:"van-cell__value"},[t("div",{staticClass:"van-field__body"},[t("textarea",{directives:[{name:"model",rawName:"v-model",value:a.detailaddress,expression:"detailaddress"}],staticClass:"van-field__control",staticStyle:{height:"24px"},attrs:{rows:"1",maxlength:"200",placeholder:"详细地址"},domProps:{value:a.detailaddress},on:{input:function(e){e.target.composing||(a.detailaddress=e.target.value)}}})])])])])]),a._v(" "),t("div",{staticClass:"van-cell van-cell--center van-switch-cell"},[a._m(0),a._v(" "),t("div",{staticClass:"van-cell__value"},[t("van-checkbox",{model:{value:a.checked,callback:function(e){a.checked=e},expression:"checked"}})],1)])])])])},staticRenderFns:[function(){var a=this.$createElement,e=this._self._c||a;return e("div",{staticClass:"van-cell__title"},[e("span",[this._v("设为默认收货地址")])])}]};var n=t("VU/8")(s,l,!1,function(a){t("9xrN")},"data-v-4b84ec8d",null);e.default=n.exports},"9xrN":function(a,e){}});
//# sourceMappingURL=44.d40c8467163123db8ab0.js.map