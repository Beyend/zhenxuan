webpackJsonp([36],{bjIU:function(t,s){},umby:function(t,s,o){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var a={name:"goodsList",data:function(){return{loading:!1,finished:!1,userId:localStorage.getItem("userId"),value:"",goodsList:[],pageNum:1}},mounted:function(){this.value=this.$route.query.value,this.getGoodsList()},methods:{getGoods:function(){this.pageNum=1,this.goodsList=[],this.loading=!1,this.finished=!1,this.getGoodsList()},toGoods:function(t){1==t.type?(this.$store.commit("setGoodsInfo",t),this.$router.push("goods")):(this.$store.commit("setGoodsInfo",t),this.$router.push("goodsKill"))},getGoodsList:function(){this.loading=!0;var t=this,s={goodsname:t.value,pageNum:this.pageNum},a=o("mw3O");t.$ajax.post("goods/getgoodslist",a.stringify(s),{headers:{"Content-Type":"application/x-www-form-urlencoded"}}).then(function(s){"200"==s.data.meta.code&&(s.data.data.data.length>0?(t.goodsList=t.goodsList.concat(s.data.data.data),t.pageNum++,t.loading=!1):0==s.data.data.data.length&&(t.finished=!0,t.loading=!1))}).catch(function(t){})}}},e={render:function(){var t=this,s=t.$createElement,o=t._self._c||s;return o("div",{attrs:{id:"goodsSearch"}},[o("div",{staticClass:"header"},[o("van-row",{staticStyle:{height:"44px",display:"flex","align-items":"center"},attrs:{gutter:"20"}},[o("van-col",{attrs:{span:"4"}},[o("van-icon",{attrs:{name:"arrow-left"},nativeOn:{click:function(s){t.$router.go("-1")}}})],1),t._v(" "),o("van-col",{staticClass:"search font_12",staticStyle:{background:"#ffffff"},attrs:{span:"16"}},[o("van-search",{attrs:{placeholder:"搜索"},on:{search:t.getGoods},model:{value:t.value,callback:function(s){t.value=s},expression:"value"}})],1)],1)],1),t._v(" "),o("div",{staticClass:"login_box"},[o("div",{staticStyle:{height:"5px",width:"100%"}}),t._v(" "),o("van-list",{attrs:{finished:t.finished},on:{load:t.getGoodsList},model:{value:t.loading,callback:function(s){t.loading=s},expression:"loading"}},t._l(t.goodsList,function(s,a){return o("van-card",{key:a,staticStyle:{margin:"10px"},nativeOn:{click:function(o){t.toGoods(s)}}},[o("div",{attrs:{slot:"thumb"},slot:"thumb"},[o("img",{directives:[{name:"lazy",rawName:"v-lazy",value:s.goodslogo.split(",")[0],expression:"item.goodslogo.split(',')[0]"}],staticStyle:{display:"block"},attrs:{src:s.goodslogo.split(",")[0],width:"100%"}})]),t._v(" "),o("div",{staticClass:"font_weight_bold padding_bottom_5",attrs:{slot:"tags"},slot:"tags"},[t._v("\n            "+t._s(s.goodsname)+"\n          ")]),t._v(" "),o("div",{staticClass:"font_12 color_8b8b8b",staticStyle:{height:"46px",overflow:"auto"},attrs:{slot:"tags"},slot:"tags"},[t._v("\n            "+t._s(s.introduce)+"\n          ")]),t._v(" "),o("div",{staticClass:"font_12",staticStyle:{"padding-top":"5px"},attrs:{slot:"tags"},slot:"tags"},[1==s.type?o("span",{staticClass:"color_ff3312"},[t._v("¥"+t._s(s.price))]):t._e(),t._v(" "),2==s.type?o("span",{staticClass:"color_ff3312"},[t._v("¥"+t._s(s.spike))]):t._e(),t._v(" "),o("span",{staticClass:"float_right color_8b8b8b"},[t._v(t._s(s.paynum)+"人付款")])])])}))],1)])},staticRenderFns:[]};var i=o("VU/8")(a,e,!1,function(t){o("bjIU")},"data-v-5e380e41",null);s.default=i.exports}});
//# sourceMappingURL=36.e25fb3fbf0e787b3868f.js.map