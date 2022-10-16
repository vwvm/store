<template>

  <link href="/src/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/admin.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/demo.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/hmstyle.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/skin.css" rel="stylesheet" type="text/css"/>

  <!--顶部导航条 -->
  <el-header>
    <el-row>
      <el-col :span="6">

        <div class="menu-hd" v-if="isLogin">
          {{ username }},欢迎您
        </div>
        <div class="menu-hd" v-else>
          亲，请<a href="login.html" target="_top" class="h">登录</a>
          <a href="#" target="_top">免费注册</a>
        </div>
      </el-col>
      <el-col :span="12"></el-col>
      <el-col :span="6">

        <a href="index.html" target="_top" class="h">商城首页</a>

        <a href="user-center.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>

        <a id="mc-menu-hd" href="shopcart.html" target="_top">
          <i class="am-icon-shopping-cart  am-icon-fw"></i>

          <router-link to="/shopcart">购物车</router-link>

          <strong id="J_MiniCartNum" class="h"></strong>
        </a>

        <a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>

      </el-col>
    </el-row>
  </el-header>
  <!--悬浮搜索框-->
  <el-main>

    <div class="nav white">

      <div class="logoBig">
        <img src="../assets/images/logo.png" style="margin-left: 0" alt="logo">
      </div>

      <div class="search-bar pr">
        <a name="index_none_header_sysc" href="#"></a>
        <form action="search.html">
          <input id="searchInput" v-model="keyword" name="index_none_header_sysc" type="text" placeholder="搜索"
                 autocomplete="off">
          <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="button" @click="toSearch">
        </form>
      </div>
    </div>
    <div class="clear"></div>


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

    <el-container>
      <!--侧边导航 -->
      <el-cascader-panel :options="categoryBeanList"
                         :props="{expandTrigger:'hover' , label:'categoryName',children:'categoryBeanList', } "/>

      <el-main>
        <!--轮播 走马灯-->
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-if="indexImages.length>0" v-for="(img,index) in indexImages" :key="index+1">
            <img :src="'/src/assets/images/'+img.imgUrl" :alt="img.imgUrl" :class="'images' + index"
                 style="object-fit: cover;object-position: center"/>
          </el-carousel-item>
        </el-carousel>
      </el-main>
      <el-aside>
        <!--小导航 -->
        <div class="am-g am-g-fixed smallnav">
          <div class="am-u-sm-3">
            <a href="sort.html"><img src="static/images/navsmall.jpg"/>
              <div class="title">商品分类</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="static/images/huismall.jpg"/>
              <div class="title">大聚惠</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="static/images/mansmall.jpg"/>
              <div class="title">个人中心</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="static/images/moneysmall.jpg"/>
              <div class="title">投资理财</div>
            </a>
          </div>
        </div>
      </el-aside>
    </el-container>


    <div class="marqueen">
      <span class="marqueen-title">商城头条</span>
      <div class="demo">

        <ul>
          <li class="title-first"><a target="_blank" href="#">
            <img src="/src/assets/images/TJ2.jpg"/>
            <span>[特惠]</span>商城爆品1分秒
          </a></li>
          <li class="title-first"><a target="_blank" href="#">
            <span>[公告]</span>商城与广州市签署战略合作协议
            <img src="static/images/TJ.jpg"/>
            <p>XXXXXXXXXXXXXXXXXX</p>
          </a></li>

          <div class="mod-vip">
            <div class="m-baseinfo" v-if="isLogin">
              <img :src="userImg" alt="用户头像" style="height: 20px ; width: 20px"/>
              <em>
                Hi,<span class="s-name">{{ username }}</span>
                <a href="#"><p>点击更多优惠活动</p></a>
              </em>
            </div>
            <div class="member-logout" v-else>
              <a class="am-btn-warning btn" href="login.html">登录</a>
              <a class="am-btn-warning btn" href="register.html">注册</a>
            </div>
            <div class="member-login">
              <a href="#"><strong>0</strong>待收货</a>
              <a href="#"><strong>0</strong>待发货</a>
              <a href="#"><strong>0</strong>待付款</a>
              <a href="#"><strong>0</strong>待评价</a>
            </div>
            <div class="clear"></div>
          </div>

          <li><a target="_blank" href="search.html"><span>[特惠]</span>洋河年末大促，低至两件五折</a></li>
          <li><a target="_blank" href="search.html"><span>[公告]</span>华北、华中部分地区配送延迟</a></li>
          <li><a target="_blank" href="search.html"><span>[特惠]</span>家电狂欢千亿礼券 买1送1！</a></li>

        </ul>
        <div class="advTip"><img src="static/images/advTip.jpg"/></div>
      </div>
    </div>
    <div class="clear"></div>

    <div class="shopMain" id="shopmain">

      <!--新品推荐 -->
      <div class="am-g am-g-fixed recommendation">
        <div class="clock am-u-sm-3">
          <img src="/src/assets/images/2016.png" style="border-radius: 50%;"/>
          <p>新品<br>推荐</p>
        </div>
        <a v-for="rp in recommendProducts" :href="'introduction?pid='+rp.productId">
          <div class="am-u-sm-4 am-u-lg-3 ">
            <div class="info ">
              <h3>{{ rp.productName }}</h3>
              <h4>销量：{{ rp.soldNum }}</h4>
            </div>
            <div class="recommendationMain one">
              <img v-if="rp.imgs.length>0" :src="'/src/static/pimgs/'+rp.imgs[0].url"/>
            </div>
          </div>
        </a>
      </div>

      <div class="clear "></div>
      <!--热门活动 -->

      <div class="am-container activity ">
        <div class="shopTitle ">
          <h4>活动</h4>
          <h3>每期活动 优惠享不停 </h3>
          <span class="more ">
                              <a href="# ">全部活动<i class="am-icon-angle-right" style="padding-left:10px ;"></i></a>
          </span>
        </div>
        <div class="am-g am-g-fixed ">
          <div class="am-u-sm-3 ">
            <div class="icon-sale one "></div>
            <h4>秒杀</h4>
            <div class="activityMain ">
              <img src="static/images/activity1.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 ">
            <div class="icon-sale two "></div>
            <h4>特惠</h4>
            <div class="activityMain ">
              <img src="static/images/activity2.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 ">
            <div class="icon-sale three "></div>
            <h4>团购</h4>
            <div class="activityMain ">
              <img src="static/images/activity3.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 last ">
            <div class="icon-sale "></div>
            <h4>超值</h4>
            <div class="activityMain ">
              <img src="static/images/activity.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

        </div>
      </div>
      <div class="clear "></div>


      <div v-for="(rc,index) in recommendCategory">
        <div style="width: 300px;height: 300px">hhhhhh</div>

        <div v-if="rc.productBeanList.length > 0" :id="'f'+(index+1)">
          <!--甜点-->
          <div class="am-container ">
            <div class="shopTitle ">
              <h4>{{ rc.categoryName }}</h4>
              <h3>{{ rc.categorySlogan }}</h3>
              <!--<div class="today-brands ">
                <a href="# ">桂花糕</a>
                <a href="# ">奶皮酥</a>
                <a href="# ">栗子糕 </a>
                <a href="# ">马卡龙</a>
                <a href="# ">铜锣烧</a>
                <a href="# ">豌豆黄</a>
              </div>-->
              <span class="more ">
		                    <a href="# ">更多美味<i class="am-icon-angle-right" style="padding-left:10px ;"></i></a>
		                        </span>
            </div>
          </div>
          <div class="am-g am-g-fixed floodFour">
            <div class="am-u-sm-5 am-u-md-4 text-one list ">
              <div class="word">
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
              </div>
              <a href="# ">
                <div class="outer-con ">
                  <div class="title ">
                    开抢啦！
                  </div>
                  <div class="sub-title ">
                    零食大礼包
                  </div>
                </div>
                <img :src="'static/images/'+rc.categoryPic"/>
              </a>
              <div class="triangle-topright"></div>
            </div>

            <div class="am-u-sm-7 am-u-md-4 text-two sug" v-if="rc.productBeanLIst.length > 0">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[0].productName }}</div>
                <div class="sub-title ">
                  销量：{{ rc.productBeanLIst[0].soldNum }}
                </div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[0].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[0].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-7 am-u-md-4 text-two" v-if="rc.productBeanLIst.length > 1">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[1].productName }}</div>
                <div class="sub-title ">
                  销量：{{ rc.productBeanLIst[1].soldNum }}
                </div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[1].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[1].imgs[0].url"/></a>
            </div>


            <div class="am-u-sm-3 am-u-md-2 text-three big" v-if="rc.productBeanLIst.length > 2">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[2].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[2].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[2].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[2].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three sug" v-if="rc.productBeanLIst.length > 3">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[3].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[3].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[3].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[3].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three " v-if="rc.productBeanLIst.length > 4">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[4].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[4].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[4].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[4].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three last big " v-if="rc.productBeanLIst.length > 5">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[5].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[5].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[5].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[5].imgs[0].url"/></a>
            </div>

          </div>
          <div class="clear "></div>
        </div>
      </div>

    </div>
  </el-main>
  <el-footer>

    <div class="footer ">
      <div class="footer-hd ">
        <p>
          <a href="# ">商城</a>
          <b>|</b>
          <a href="# ">商城首页</a>
          <b>|</b>
          <a href="# ">支付宝</a>
          <b>|</b>
          <a href="# ">物流</a>
        </p>
      </div>
      <div class="footer-bd ">
        <p>
          <a href="# ">关于</a>
          <a href="# ">合作伙伴</a>
          <a href="# ">联系我们</a>
          <a href="# ">网站地图</a>
          <em>© .com 版权所有</em>
        </p>
      </div>
    </div>

  </el-footer>

  <!--引导 -->
  <div class="navCir">
    <li class="active"><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
    <li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
    <li>
      <router-link to="/shopcart">购物车</router-link>
    </li>
    <li><a href="person/index.html"><i class="am-icon-user"></i>我的</a></li>
  </div>

  <button style="font-size: 300px" @click="test()">test</button>
  <button style="font-size: 50px" @click="test()">新品推荐</button>
  <a v-for="rp in recommendProducts">
      <div class="info ">
        <button>{{rp}}</button>
        <h3>{{ rp.productName }}</h3>
        <h4>销量：{{ rp.soldNum }}</h4>
      </div>
  </a>


</template>

<script>

import {getCookieValue} from "/src/assets/js/cookie_utils.js";
import axios from "axios";
import {getCurrentInstance, reactive} from "vue";
// import getUrlParam from "/src/assets/js/url_utils.js"


export default {
  // setup(){
  //   const recommendCategory = reactive({recommendCategory: []})
  //   return {
  //     recommendCategory
  //   }
  // },
  name: "index",
  data() {
    return {
      username: "您未登录",
      userImg: "",
      isLogin: false,
      indexImages: [],
      categoryBeanList: [],
      a: {},
      b: {},
      recommendProducts: reactive(this.b),
      recommendCategory: reactive(this.a),
      keyword: "",
      number: 0,
    }
  },
  computed: {
    recommendProduct: {
      get: function () {
        return this.recommendProducts
      },
      set: function (value) {
        this.recommendProducts = value
      }
    }
  },
  //渲染html前调用
  created() {

    const vm = this
    // 登录信息
    const token = getCookieValue("token");
    console.log(token)
    if (token !== null && token !== "") {
      this.isLogin = true;
      this.username = getCookieValue("username");
      this.userImg = '/src/assets/images/' + getCookieValue("userImg");
    }

    let baseUrl = import.meta.env.VITE_API_DOMAIN
    //轮播图
    const url = baseUrl + "/index/img-list"
    axios.get(url).then((res) => {
      const vo = res.data;
      this.indexImages = vo.data;
    })
    //商品列表
    const categoryUrl = baseUrl + "/index/category-list";
    axios({
      method: "get",
      url: categoryUrl,
    }).then((res) => {
      const vo = res.data;
      this.categoryBeanList = vo.data
    })
    // 商品推荐
    const categoryRecommends = baseUrl + "/index/product-recommends"
    axios({
      method: "get",
      url: categoryRecommends,
    }).then((res) => {
      this.recommendProducts = res.data.data
    })
    //分类商品推荐
    const categoryProductsRecommend = baseUrl + "/index/category-recommends"
    axios({
      method: "get",
      url: categoryProductsRecommend,
    }).then((res) => {
      const vo = res.data;
      vm.recommendCategory = vo.data
    })
  },
  methods: {
    hallo: function () {
      console.log("hallo")
    },
    test: function () {

      console.log(this.recommendProducts)
      console.log(this.categoryBeanList)
      this.number++
    }
  }
}
</script>

<style scoped src="/src/assets/css/index.css"></style>
<style scoped src="/src/assets/css/admin.css"></style>
<style scoped src="/src/assets/css/demo.css"></style>
<style scoped src="/src/assets/css/hmstyle.css"></style>
<style scoped src="/src/assets/css/skin.css"></style>