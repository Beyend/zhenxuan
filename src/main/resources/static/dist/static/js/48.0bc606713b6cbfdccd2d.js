webpackJsonp([48],{"8Hp1":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={name:"userService",data:function(){return{serviceList:[],userId:localStorage.getItem("userId")}},mounted:function(){this.getServiceList()},methods:{delService:function(t){var e=this,a={userid:e.userId,samecityid:t.samecityid},s=i("mw3O");e.$ajax.post("samecity/deletesamecity",s.stringify(a),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then(function(t){"200"==t.data.meta.code?e.getServiceList():e.$toast({message:t.data.meta.msg,position:"bottom"})}).catch(function(t){})},toServiceInfo:function(t){this.$store.commit("setServiceInfo",t),console.log(t),this.$router.push("serviceInfo")},toAddService:function(){this.$router.push("addService")},getServiceList:function(){var t=this,e={userid:t.userId,type:1},a=i("mw3O");t.$ajax.post("samecity/getsamecitylist",a.stringify(e),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then(function(e){"200"==e.data.meta.code&&(t.serviceList=e.data.data.data)}).catch(function(t){})}}},s={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{attrs:{id:"userService"}},[i("div",{staticClass:"header"},[i("div",{staticClass:"van-hairline--bottom van-nav-bar"},[i("div",{staticClass:"van-nav-bar__left"},[i("i",{staticClass:"van-icon van-icon-arrow van-nav-bar__arrow",staticStyle:{color:"#000000 !important"},on:{click:function(e){t.$router.back(-1)}}})]),t._v(" "),i("div",{staticClass:"van-ellipsis van-nav-bar__title"},[t._v("我的维修")])])]),t._v(" "),i("div",{staticClass:"login_box"},[t._l(t.serviceList,function(e,a){return i("div",{staticStyle:{"margin-top":"10px"}},[i("div",{staticClass:"text_center font_12 color_8b8b8b"},[i("span",[t._v(t._s(e.addtime))])]),t._v(" "),i("div",{staticClass:"background_color_ffffff font_12",staticStyle:{margin:"0 10px","border-radius":"5px"}},[i("div",{staticClass:"text_right",staticStyle:{"border-bottom":"1px solid #eeeeee",padding:"6px"}},[i("span",{staticStyle:{float:"left","margin-top":"4px"}},[t._v(t._s(e.title))]),t._v(" "),i("img",{attrs:{src:"static/img/icon_shanchu_wdwx.png",height:"16px"},on:{click:function(i){t.delService(e)}}})]),t._v(" "),i("div",{staticStyle:{padding:"6px"}},[i("span",{staticClass:"color_8b8b8b line_height_16"},[t._v(t._s(e.content))])]),t._v(" "),i("div",{staticStyle:{padding:"6px","text-align":"right"}},[i("span",{staticClass:"color_c10804",on:{click:function(i){t.toServiceInfo(e)}}},[t._v("查看详情")])])])])}),t._v(" "),i("div",{staticClass:"text_center background_color_ffffff",staticStyle:{position:"fixed",width:"100%",bottom:"0",padding:"5px",height:"30px","line-height":"30px"}},[i("img",{staticStyle:{"vertical-align":"middle"},attrs:{src:"static/img/icon_tj1.png",width:"15px"}}),t._v(" "),i("span",{staticClass:"font_12 color_c10804",on:{click:t.toAddService}},[t._v("添加发布")])])],2)])},staticRenderFns:[]};var n=i("VU/8")(a,s,!1,function(t){i("SRbg")},"data-v-47e2a107",null);e.default=n.exports},SRbg:function(t,e){}});
//# sourceMappingURL=48.0bc606713b6cbfdccd2d.js.map