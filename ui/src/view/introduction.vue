<template>
  <link href="/src/assets/css/admin.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/demo.css" rel="stylesheet" type="text/css"/>
  <link type="text/css" href="/src/assets/css/optstyle.css" rel="stylesheet"/>
  <link type="text/css" href="/src/assets/css/style.css" rel="stylesheet"/>
  <!-- 引入 layui.css -->
  <link rel="stylesheet" href="//unpkg.com/layui@2.6.5/dist/css/layui.css">

  <!--顶部导航条 -->
  <div class="am-container header">
    <ul class="message-l">
      <div class="topMessage">
        <div v-if="isLogin" class="menu-hd">
          {{ username }},欢迎您！
        </div>
        <div v-else class="menu-hd">
          <a href="#" target="_top" class="h">亲，请登录</a>
          <a href="#" target="_top">免费注册</a>
        </div>
      </div>
    </ul>
    <ul class="message-r">
      <div class="topMessage home">
        <div class="menu-hd"><a href="index.html" target="_top" class="h">商城首页</a></div>
      </div>
      <div class="topMessage my-shangcheng">
        <div class="menu-hd MyShangcheng">
          <a href="user-center.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
        </div>
      </div>
      <div class="topMessage mini-cart">
        <div class="menu-hd">
          <a id="mc-menu-hd" href="shopcart.html" target="_top">
            <i class="am-icon-shopping-cart  am-icon-fw"></i>
            <span>购物车</span>
            <strong id="J_MiniCartNum" class="h"></strong>
          </a>
        </div>
      </div>
      <div class="topMessage favorite">
        <div class="menu-hd">
          <!--<a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>-->
        </div>
      </div>
    </ul>
  </div>
  <!--悬浮搜索框-->
  <div class="nav white">
    <div class="logo"><img src="/src/assets/images/logo.png"/></div>
    <div class="logoBig">
      <li><img src="src/assets/images/logo.png"/></li>
    </div>
    <div class="search-bar pr">
      <a name="index_none_header_sysc" href="#"></a>
      <form>
        <input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
        <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
      </form>
    </div>
  </div>
  <div class="clear"></div>
  <b class="line"></b>

  <div class="listMain">
    <!--分类-->
    <div class="nav-table">
      <div class="long-title"><span class="all-goods">全部分类</span></div>
      <div class="nav-cont">
        <ul>
          <li class="index"><a href="#">首页</a></li>
          <li class="qc"><a href="#">闪购</a></li>
          <li class="qc"><a href="#">限时抢</a></li>
          <li class="qc"><a href="#">团购</a></li>
          <li class="qc last"><a href="#">大包装</a></li>
        </ul>
        <div class="nav-extra">
          <i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利
          <i class="am-icon-angle-right" style="padding-left: 10px;"></i>
        </div>
      </div>
    </div>
    <ol class="am-breadcrumb am-breadcrumb-slash">
      <li><a href="#">首页</a></li>
      <li><a href="#">分类</a></li>
      <li class="am-active">内容</li>
    </ol>
    <div class="scoll">
      <section class="slider">
        <div class="flexslider">
          <ul class="slides">
            <li>
              <img src="/src/assets/images/01.jpg" title="pic"/>
            </li>
            <li>
              <img src="/src/assets/images/02.jpg"/>
            </li>
            <li>
              <img src="/src/assets/images/03.jpg"/>
            </li>
          </ul>
        </div>
      </section>
    </div>

    <div class="item-inform">
      <!--放大镜-->
      <div class="clearfixLeft" id="clearcontent">
        <div class="box">
          <template v-if="productImgs.length>0">
            <div class="tb-booth tb-pic tb-s310">
              <a href="/src/assets/images/01.jpg">
                <img style="width: 300px" :src="'/src/assets/pimgs/'+productImgs[0].url" alt="细节展示放大镜特效"
                     :rel="'/src/assets/pimgs/'+productImgs[0].url" class="jqzoom"/>
              </a>
            </div>
          </template>
          <ul class="tb-thumb" id="thumblist">
            <template v-for="(img,index) in productImgs">
              <li :class="{'tb-selected':index===0}">
                <div class="tb-pic tb-s40">
                  <a href="#"><img :src="'/src/assets/pimgs/'+img.url" :mid="'static/pimgs/'+img.url"
                                   :big="'/src/assets/pimgs/'+img.url"></a>
                </div>
              </li>
            </template>
          </ul>
        </div>
        <div class="clear"></div>

      </div>
      <!--规格属性-->
      <!--名称-->
      <div class="tb-detail-hd">
        <h1>{{ products.productName }}</h1>
      </div>
      <div class="tb-detail-list">
        <!--价格-->
        <div class="tb-detail-price">
          <dl class="price iteminfo_price">
            <dt>促销价</dt>
            <dd><em>¥</em><b class="sys_item_price">{{ productSkus[currentSkuIndex].sellPrice }}</b></dd>
          </dl>
          <dl class="price iteminfo_mktprice">
            <dt>原价</dt>
            <dd><em>¥</em><b class="sys_item_mktprice">{{ productSkus[currentSkuIndex].originalPrice }}</b></dd>
          </dl>
          <div class="clear"></div>
        </div>

        <!--地址-->
        <dl class="iteminfo_parameter freight">
          <dt>配送至</dt>
          <div class="iteminfo_freprice">
            <div class="am-form-content address">
              <select data-am-selected>
                <option value="a">浙江省</option>
                <option value="b">湖北省</option>
              </select>
              <select data-am-selected>
                <option value="a">温州市</option>
                <option value="b">武汉市</option>
              </select>
              <select data-am-selected>
                <option value="a">瑞安区</option>
                <option value="b">洪山区</option>
              </select>
            </div>
            <div class="pay-logis">
              快递<b class="sys_item_freprice">10</b>元
            </div>
          </div>
        </dl>
        <div class="clear"></div>

        <!--销量-->
        <dl class="tm-ind-panel">
          <!--<li class="tm-ind-item tm-ind-sellCount canClick">
            <div class="tm-indcon"><span class="tm-label">月销量</span><span class="tm-count">1015</span></div>
          </li>-->
          <dt class="tm-ind-item tm-ind-sumCount canClick">
            <div class="tm-indcon"><span class="tm-label">累计销量</span><span
                class="tm-count">{{ products.soldNum }}</span></div>
          </dt>
          <dt class="tm-ind-item tm-ind-reviewCount canClick tm-line3">
            <div class="tm-indcon"><span class="tm-label">累计评价</span><span class="tm-count">640</span></div>
          </dt>
        </dl>
        <div class="clear"></div>

        <!--各种规格-->
        <div class="iteminfo_parameter sys_item_specpara">
          <dt class="theme-login">
            <div class="cart-title">可选规格<span class="am-icon-angle-right"></span></div>
          </dt>
          <dd>
            <!--操作页面-->

            <div class="theme-popover-mask"></div>

            <div class="theme-popover">
              <div class="theme-span"></div>
              <div class="theme-poptit">
                <a href="javascript:;" title="关闭" class="close">×</a>
              </div>
              <div class="theme-popbod dform">
                <form class="theme-signin" name="loginform" action="" method="post">

                  <div class="theme-signin-left">
                    <!--显示商品套餐名选项（当套餐数量>1时显示）-->
                    <div v-if="productSkus.length > 1" class="theme-options">
                      <div class="cart-title">套餐</div>
                      <ul>
                        <template v-for="(sku,index) in productSkus">
                          <!--如果当前套餐是正在显示的套餐，则添加选中效果-->
                          <li v-if="index===currentSkuIndex" class="sku-line selected" :data-index="index"
                              @click="changeSku(index)">{{ sku.skuName }}<i></i></li>
                          <!--否则不添加选中效果-->
                          <li v-else class="sku-line" :data-index="index"
                              @click="changeSku(index)">{{ sku.skuName }}<i></i></li>
                        </template>
                      </ul>
                    </div>

                    <!--显示当前套餐下的属性  skuProps  对象/Map-->
                    <div id="props">
                      <div v-for="(value,key) in skuProps" class="theme-options">
                        <div class="cart-title">{{ key }}</div>
                        <ul>
                          <template v-for="(v,index) in value">
                            <li :class="{'sku-line':true,'selected':index===0}" :name="key" @click="changeProp"
                                :data-key="key" :data-v="v">{{ v }}
                            </li>
                          </template>
                        </ul>
                      </div>
                    </div>
                    <div class="theme-options">
                      <div class="cart-title number">数量</div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </dd>
          <dd>
            <input id="min" class="am-btn am-btn-default" type="button" value="-" @click="changeNum(-1)"/>
            <input id="text_box" type="text" v-model="num" style="width:30px;"/>
            <input id="add" class="am-btn am-btn-default" type="button" value="+" @click="changeNum(1)"/>
            <span id="stock1" class="tb-hidden">库存<span
                class="stock">{{ productSkus[0].stock }}</span>件</span>
          </dd>
        </div>

      </div>
      <div class="clear"></div>

      <!--活动	-->
      <div class="shopPromotion gold">
        <div class="hot">
          <dt class="tb-metatit">折扣</dt>
          <div class="gold-list">
            <p>当前套餐享{{ productSkus[0].discounts * 10 }}折优惠
              <!--<span>点击领券<i class="am-icon-sort-down"></i></span>--></p>
          </div>
        </div>
        <div class="clear"></div>
        <div class="coupon">
          <dt class="tb-metatit">优惠券</dt>
          <div class="gold-list">
            <ul>
              <li>125减5</li>
              <li>198减10</li>
              <li>298减20</li>
            </ul>
          </div>
        </div>
      </div>

      <!-- 购买按钮 -->
      <div class="pay">
        <div class="pay-opt">
          <a href="home.html"><span class="am-icon-home am-icon-fw">首页</span></a>
          <a><span class="am-icon-heart am-icon-fw">收藏</span></a>

        </div>
        <li>
          <div class="clearfix tb-btn tb-btn-buy theme-login">
            <a id="LikBuy" title="点此按钮到下一步确认购买信息" href="#">立即购买</a>
          </div>
        </li>
        <li>
          <div class="clearfix tb-btn tb-btn-basket theme-login">
            <a id="LikBasket" title="加入购物车" @click="addShopCart"><i></i>加入购物车</a>
          </div>
        </li>
      </div>
      <div class="clear"></div>
    </div>


  </div>


  <!--菜单 -->
  <div class=tip>
    <div id="sidebar">
      <div id="wrap">
        <div id="prof" class="item">
          <a href="#">
            <span class="setting"></span>
          </a>
          <div class="ibar_login_box status_login">
            <div class="avatar_box">
              <p class="avatar_imgbox"><img src="/src/assets/images/no-img_mid_.jpg"/></p>
              <ul class="user_info">
                <li>用户名：sl1903</li>
                <li>级&nbsp;别：普通会员</li>
              </ul>
            </div>
            <div class="login_btnbox">
              <a href="#" class="login_order">我的订单</a>
              <a href="#" class="login_favorite">我的收藏</a>
            </div>
            <i class="icon_arrow_white"></i>
          </div>

        </div>
        <div id="shopCart" class="item">
          <a href="shopcart.html">
            <span class="message"><p>购物车</p></span>
          </a>
          <p class="cart_num">0</p>
        </div>
        <div id="asset" class="item">
          <a href="#">
            <span class="view"></span>
          </a>
          <div class="mp_tooltip">
            我的资产
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="foot" class="item">
          <a href="#">
            <span class="zuji"></span>
          </a>
          <div class="mp_tooltip">
            我的足迹
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="brand" class="item">
          <a href="#">
            <span class="wdsc"><img src="/src/assets/images/wdsc.png"/></span>
          </a>
          <div class="mp_tooltip">
            我的收藏
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div id="broadcast" class="item">
          <a href="#">
            <span class="chongzhi"><img src="/src/assets/images/chongzhi.png"/></span>
          </a>
          <div class="mp_tooltip">
            我要充值
            <i class="icon_arrow_right_black"></i>
          </div>
        </div>

        <div class="quick_toggle">
          <li class="qtitem">
            <a href="#"><span class="kfzx"></span></a>
            <div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
          </li>
          <!--二维码 -->
          <li class="qtitem">
            <a href="#none"><span class="mpbtn_qrcode"></span></a>
            <div class="mp_qrcode" style="display:none;"><img src="/src/assets/images/weixin_code_145.png"/><i
                class="icon_arrow_white"></i></div>
          </li>
          <li class="qtitem">
            <a href="#top" class="return_top"><span class="top"></span></a>
          </li>
        </div>

        <!--回到顶部 -->
        <div id="quick_links_pop" class="quick_links_pop hide"></div>

      </div>

    </div>
    <div id="prof-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        我
      </div>
    </div>
    <div id="shopCart-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        购物车
      </div>
    </div>
    <div id="asset-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        资产
      </div>

      <div class="ia-head-list">
        <a href="#" target="_blank" class="pl">
          <div class="num">0</div>
          <div class="text">优惠券</div>
        </a>
        <a href="#" target="_blank" class="pl">
          <div class="num">0</div>
          <div class="text">红包</div>
        </a>
        <a href="#" target="_blank" class="pl money">
          <div class="num">￥0</div>
          <div class="text">余额</div>
        </a>
      </div>

    </div>
    <div id="foot-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        足迹
      </div>
    </div>
    <div id="brand-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        收藏
      </div>
    </div>
    <div id="broadcast-content" class="nav-content">
      <div class="nav-con-close">
        <i class="am-icon-angle-right am-icon-fw"></i>
      </div>
      <div>
        充值
      </div>
    </div>
  </div>

</template>

<script>


import {getUrlParam} from "/src/assets/js/url_utils.js"
import axios from "axios";
import $ from 'jquery'
import {getCookieValue} from "../assets/js/cookie_utils.js";

const baseUrl = import.meta.env.VITE_API_DOMAIN

export default {
  name: "introduction",
  data() {
    return {
      username: "您未登录",
      userImg: "",
      isLogin: false,

      productId: "",
      products: {
        productName: "",
        soldNum: 0,
      },
      productImgs: [],
      productSkus: [],
      currentSkuIndex:0, // 当前选择套餐id
    }
  },
  created() {
    // 登录信息
    const token = getCookieValue("token");
    if (token !== null && token !== "") {
      this.isLogin = true;
      this.username = getCookieValue("username");
      this.userImg = '/src/assets/images/' + getCookieValue("userImg");
    }

    // 获取url传递的id
    this.productId = getUrlParam("pid")

    //根据id获取详细信息
    const detailInfoUrl = baseUrl + "/index/detail-info/" + this.productId
    axios({
      method: "get",
      url: detailInfoUrl,
    }).then((res) => {
      const vo = res.data.data;
      console.log(vo);
      this.products = vo.products[0];
      this.productImgs = vo.productImgs;
      this.productSkus = vo.productSkus;
    })
  },
  updated() {
    //每次重新渲染之后执行
    //====>初始化放大镜效果
    $(".jqzoom").imagezoom();
    $("#thumblist li a").click(function () {
      $(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
      $(".jqzoom").attr('src', $(this).find("img").attr("mid"));
      $(".jqzoom").attr('rel', $(this).find("img").attr("big"));
    });
    //初始化套餐属性的选择效果
    //initChoose();
  },
  methods:{
    changeSku:function(index){

     this.currentSkuIndex =  index
     console.log(this.currentSkuIndex)
    }
  }
}


</script>

<style scoped>

</style>


