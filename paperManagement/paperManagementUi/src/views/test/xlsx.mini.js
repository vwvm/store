<!DOCTYPE html>
<html class="" lang="zh-CN">
<head prefix="og: http://ogp.me/ns#">
<meta charset="utf-8">
<link as="style" href="https://gitcode.net/assets/application-fb47723a1b02f830eaf466e56b2ddb14ceeed33061468cd553d6ede28ef5c8f5.css" rel="preload">
<link as="style" href="https://gitcode.net/assets/highlight/themes/white-6a22b8b375794a1289df4622d79144821592090a8477236097a5e6dacb004e68.css" rel="preload">

<meta content="IE=edge" http-equiv="X-UA-Compatible">

<meta content="object" property="og:type">
<meta content=" GitCode" property="og:site_name">
<meta content="xlsx.mini.js · master · mirrors / SheetJS / js-xlsx" property="og:title">
<meta content="📗 SheetJS Spreadsheet Data Toolkit -- New home https://git.sheetjs.com/SheetJS/sheetjs 🚀 Github 镜像仓库 " property="og:description">
<meta content="https://gitcode.net/assets/gitlab_logo-ec88bcacc4284946371a7d3b529f3e6310ca4e3e5f41e98c6847eb2c7deff36b.png" property="og:image">
<meta content="64" property="og:image:width">
<meta content="64" property="og:image:height">
<meta content="https://gitcode.net/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js" property="og:url">
<meta content="summary" property="twitter:card">
<meta content="xlsx.mini.js · master · mirrors / SheetJS / js-xlsx" property="twitter:title">
<meta content="📗 SheetJS Spreadsheet Data Toolkit -- New home https://git.sheetjs.com/SheetJS/sheetjs 🚀 Github 镜像仓库 " property="twitter:description">
<meta content="https://gitcode.net/assets/gitlab_logo-ec88bcacc4284946371a7d3b529f3e6310ca4e3e5f41e98c6847eb2c7deff36b.png" property="twitter:image">

<title>xlsx.mini.js · master · mirrors / SheetJS / js-xlsx ·  GitCode</title>
<meta content="📗 SheetJS Spreadsheet Data Toolkit -- New home https://git.sheetjs.com/SheetJS/sheetjs 🚀 Github 镜像仓库 " name="description">
<link rel="shortcut icon" type="image/png" href="/uploads/-/system/appearance/favicon/1/icon.png" id="favicon" data-original-href="/uploads/-/system/appearance/favicon/1/icon.png" />

<link rel="stylesheet" media="all" href="/assets/application-fb47723a1b02f830eaf466e56b2ddb14ceeed33061468cd553d6ede28ef5c8f5.css" />

<link rel="stylesheet" media="all" href="/assets/application_utilities-aca0b81ce4340412b8407966eef30a4182b51178a2c547d30ad800a4fd84a6cb.css" />
<link rel="stylesheet" media="all" href="/assets/themes/theme_indigo-03d9edccaad40dfef1090b7e66f6232229610dc0e183c018f940e37ec37bd625.css" />

<link rel="stylesheet" media="all" href="/assets/highlight/themes/white-6a22b8b375794a1289df4622d79144821592090a8477236097a5e6dacb004e68.css" />


<script nonce="kxabjrOgHRzPhdbNbitMLA==">
//<![CDATA[
window.gon={};gon.api_version="v4";gon.default_avatar_url="https://gitcode.net/assets/no_avatar-849f9c04a3a0d0cea2424ae97b27447dc64a7dbfae83c036c45b403392f0e8ba.png";gon.max_file_size=200;gon.asset_host=null;gon.webpack_public_path="/assets/webpack/";gon.relative_url_root="";gon.shortcuts_path="/help/shortcuts";gon.user_color_scheme="white";gon.gitlab_url="https://gitcode.net";gon.revision="023988bc18d";gon.gitlab_logo="/assets/gitlab_logo-ec88bcacc4284946371a7d3b529f3e6310ca4e3e5f41e98c6847eb2c7deff36b.png";gon.sprite_icons="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg";gon.sprite_file_icons="/assets/file_icons-c13caf2f3ca00cc2c02b11d373ac288c200b9b4dcddbb52a5027dc07b3eece19.svg";gon.emoji_sprites_css_path="/assets/emoji_sprites-289eccffb1183c188b630297431be837765d9ff4aed6130cf738586fb307c170.css";gon.select2_css_path="/assets/lazy_bundles/select2-45efaa0a6ce731e5fb2b8774a4bc12280aa0422d300f858534446a0159db10e1.css";gon.test_env=false;gon.disable_animations=null;gon.suggested_label_colors={"#0033CC":"UA blue","#428BCA":"Moderate blue","#44AD8E":"Lime green","#A8D695":"Feijoa","#5CB85C":"Slightly desaturated green","#69D100":"Bright green","#004E00":"Very dark lime green","#34495E":"Very dark desaturated blue","#7F8C8D":"Dark grayish cyan","#A295D6":"Slightly desaturated blue","#5843AD":"Dark moderate blue","#8E44AD":"Dark moderate violet","#FFECDB":"Very pale orange","#AD4363":"Dark moderate pink","#D10069":"Strong pink","#CC0033":"Strong red","#FF0000":"Pure red","#D9534F":"Soft red","#D1D100":"Strong yellow","#F0AD4E":"Soft orange","#AD8D43":"Dark moderate orange"};gon.first_day_of_week=1;gon.ee=false;gon.current_user_id=1883815;gon.current_username="a1647010108";gon.current_user_fullname="a1647010108";gon.current_user_avatar_url="https://profile-avatar.csdnimg.cn/default.jpg!1";gon.features={"webperfExperiment":false,"snippetsBinaryBlob":false,"usageDataApi":true,"securityAutoFix":false,"startupCss":false};
//]]>
</script>
<script src="/assets/locale/zh_CN/app-1d4232faa33c2d70bf4fec306a251675eace768ca11dc68919a02f063990e088.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>


<script src="/assets/webpack/runtime.ac089684.bundle.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/webpack/main.8a1a41b0.chunk.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/webpack/commons-pages.projects-pages.projects.activity-pages.projects.alert_management.details-pages.project-03c2a89e.28bbde75.chunk.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/webpack/commons-pages.admin.application_settings-pages.admin.application_settings.general-pages.admin.applic-d549e6f7.15033b21.chunk.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/webpack/commons-pages.projects.blame.show-pages.projects.blob.show.ab6a3bac.chunk.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/webpack/pages.projects.blob.show.9d3a13c9.chunk.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>

<script nonce="kxabjrOgHRzPhdbNbitMLA==">
//<![CDATA[
window.uploads_path = "/mirrors/SheetJS/js-xlsx/uploads";



//]]>
</script>
<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="RZzxVBiH/U1Qle/feHZc97m9uTcyxDKFx4w40QYfrWAlBaum6NuS3zHV5gZRNj+OzjbCMn3Hs8nVosYU9lyYcw==" />
<meta name="csp-nonce" content="kxabjrOgHRzPhdbNbitMLA==" />
<meta name="action-cable-url" content="/-/cable" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
<meta content="#474D57" name="theme-color">
<meta content="{&quot;spm&quot;:&quot;1033.2243&quot;}" name="report">
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-iphone-5a9cee0e8a51212e70b90c87c12f382c428870c0ff67d1eb034d884b78d2dae7.png" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-ipad-a6eec6aeb9da138e507593b464fdac213047e49d3093fc30e90d9a995df83ba3.png" sizes="76x76" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-iphone-retina-72e2aadf86513a56e050e7f0f2355deaa19cc17ed97bbe5147847f2748e5a3e3.png" sizes="120x120" />
<link rel="apple-touch-icon" type="image/x-icon" href="/assets/touch-icon-ipad-retina-8ebe416f5313483d9c1bc772b5bbe03ecad52a54eba443e5215a22caed2a16a2.png" sizes="152x152" />
<meta content="/assets/favicon-ec88bcacc4284946371a7d3b529f3e6310ca4e3e5f41e98c6847eb2c7deff36b.png" name="msapplication-TileImage">




</head>

<body class="ui-indigo tab-width-8  gl-browser-chrome gl-platform-windows" data-find-file="/mirrors/SheetJS/js-xlsx/-/find_file/master" data-group="SheetJS" data-namespace-id="9912" data-page="projects:blob:show" data-page-type-id="master/xlsx.mini.js" data-project="js-xlsx" data-project-id="3903">

<script nonce="kxabjrOgHRzPhdbNbitMLA==">
//<![CDATA[
gl = window.gl || {};
gl.client = {"isChrome":true,"isWindows":true};


//]]>
</script>


<link rel="stylesheet" type="text/css" href="https://g.csdnimg.cn/user-login/2.2.8/css/??index.css,toast.style.css">
<script src="https://g.csdnimg.cn/??lib/jquery/1.12.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="https://g.csdnimg.cn/user-login/2.1.5/user-login.js"></script>
<script type="text/javascript" src="https://g.csdnimg.cn/user-login/2.2.4/js/??toast.script.js"></script>
<script src="https://g.csdnimg.cn/common/csdn-report/report.js" type="text/javascript"></script>
<style>
  #js-peek {
    display: none;
  }
</style>
<header class="navbar navbar-gitlab navbar-expand-sm js-navbar" data-qa-selector="navbar">
<a class="sr-only gl-accessibility" href="#content-body" tabindex="1">Skip to content</a>
<div class="container-fluid">
<div class="header-content">
<div class="title-container">
<h1 class="title">
<a title="仪表板" id="logo" href="/dashboard/projects/home"><img class="brand-header-logo lazy" data-src="/uploads/-/system/appearance/header_logo/1/new.png" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" />
</a></h1>
<ul class="list-unstyled navbar-sub-nav">
<li class="d-md-block home dropdown header-groups qa-groups-dropdown" data-toggle="tooltip" data-placement="bottom" data-container="body" data-track-label="groups_dropdown" data-track-event="click_dropdown" data-track-value=""><a style="min-width:unset" title="首页" class="dashboard-shortcuts-issues abtn" aria-label="Issue" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/explore/welcome">首页
</a></li><li id="nav-projects-dropdown" class="mobile-hide home dropdown header-projects qa-projects-dropdown" data-track-label="projects_dropdown" data-track-event="click_dropdown" data-track-value=""><button class="btn" data-toggle="dropdown" type="button">
项目
<svg class="s16 caret-down" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg>
</button>
<div class="dropdown-menu frequent-items-dropdown-menu">
<div class="frequent-items-dropdown-container">
<div class="frequent-items-dropdown-sidebar qa-projects-dropdown-sidebar">
<ul>
<li class=""><a class="qa-your-projects-link" href="/users/a1647010108/projects">你的项目
</a></li><li class=""><a href="/users/a1647010108/starred?show_starred_projects=true">Star项目
</a></li><li class=""><a href="/explore/projects/starred">浏览项目
</a></li></ul>
</div>
<div class="frequent-items-dropdown-content">
<div data-project-id="3903" data-project-name="js-xlsx" data-project-namespace="mirrors / SheetJS / js-xlsx" data-project-web-url="/mirrors/SheetJS/js-xlsx" data-user-name="a1647010108" id="js-projects-dropdown"></div>
</div>
</div>

</div>
</li><li id="nav-groups-dropdown" class="mobile-hide home dropdown header-groups qa-groups-dropdown" data-track-label="groups_dropdown" data-track-event="click_dropdown" data-track-value=""><button class="btn" data-toggle="dropdown" type="button">
组织
<svg class="s16 caret-down" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg>
</button>
<div class="dropdown-menu frequent-items-dropdown-menu">
<div class="frequent-items-dropdown-container">
<div class="frequent-items-dropdown-sidebar qa-groups-dropdown-sidebar">
<ul>
<li class=""><a class="qa-your-groups-link" href="/users/a1647010108/groups">你的组织
</a></li><li class=""><a data-track-label="groups_dropdown_explore_groups" data-track-event="click_link" href="/explore/groups">浏览组织
</a></li></ul>
</div>
<div class="frequent-items-dropdown-content">
<div data-user-name="a1647010108" id="js-groups-dropdown"></div>
</div>
</div>

</div>
</li><li class="d-none d-md-block home dropdown header-groups qa-groups-dropdown" data-toggle="tooltip" data-placement="bottom" data-container="body" data-track-label="groups_dropdown" data-track-event="click_dropdown" data-track-value=""><a title="Issue" class="dashboard-shortcuts-issues abtn" aria-label="Issue" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/dashboard/issues?assignee_username=a1647010108">Issue
<span class="badge badge-pill green-badge hidden">
0
</span>
</a></li><li class="d-none d-md-block home dropdown header-groups qa-groups-dropdown" data-toggle="tooltip" data-placement="bottom" data-container="body" data-track-label="groups_dropdown" data-track-event="click_dropdown" data-track-value=""><a title="合并请求" class="dashboard-shortcuts-merge_requests abtn" aria-label="合并请求" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/dashboard/merge_requests?assignee_username=a1647010108">合并请求
<span class="badge badge-pill hidden merge-requests-count">
0
</span>
</a></li><li class="d-none d-md-block home dropdown header-groups qa-groups-dropdown" data-toggle="tooltip" data-placement="bottom" data-container="body" data-track-label="groups_dropdown" data-track-event="click_dropdown" data-track-value=""><a title="代码片段" class="dashboard-shortcuts-snippets abtn" aria-label="合并请求" data-toggle="tooltip" data-placement="bottom" data-container="body" href="/-/snippets">代码片段
</a></li><li class="hidden">
<a class="dashboard-shortcuts-projects" href="/dashboard/projects">项目
</a></li>

<li class="nav-item d-none d-lg-block m-auto">
<div class="search search-form" data-track-event="activate_form_input" data-track-label="navbar_search" data-track-value="">
<form class="form-inline" action="/search" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><div class="search-input-container">
<div class="search-input-wrap">
<div class="dropdown" data-url="/search/autocomplete">
<input type="search" name="search" id="search" placeholder="搜索或转到..." class="search-input dropdown-menu-toggle no-outline js-search-dashboard-options" spellcheck="false" autocomplete="off" data-issues-path="/dashboard/issues" data-mr-path="/dashboard/merge_requests" data-qa-selector="search_term_field" aria-label="搜索或转到..." />
<button class="hidden js-dropdown-search-toggle" data-toggle="dropdown" type="button"></button>
<div class="dropdown-menu dropdown-select" data-testid="dashboard-search-options">
<div class="dropdown-content"><ul>
<li class="dropdown-menu-empty-item">
<a>
正在加载...
</a>
</li>
</ul>
</div><div class="dropdown-loading"><div class="gl-spinner-container"><span class="gl-spinner gl-spinner-orange gl-spinner-md gl-mt-7" aria-label="加载中"></span></div></div>
</div>
<svg class="s16 search-icon" data-testid="search-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#search"></use></svg>
<svg class="s16 clear-icon js-clear-input" data-testid="close-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#close"></use></svg>
</div>
</div>
</div>
<input type="hidden" name="group_id" id="group_id" value="9912" class="js-search-group-options" data-group-path="SheetJS" data-name="SheetJS" data-issues-path="/groups/mirrors/SheetJS/-/issues" data-mr-path="/groups/mirrors/SheetJS/-/merge_requests" />
<input type="hidden" name="project_id" id="search_project_id" value="3903" class="js-search-project-options" data-project-path="js-xlsx" data-name="js-xlsx" data-issues-path="/mirrors/SheetJS/js-xlsx/-/issues" data-mr-path="/mirrors/SheetJS/js-xlsx/-/merge_requests" data-issues-disabled="false" />
<input type="hidden" name="scope" id="scope" />
<input type="hidden" name="search_code" id="search_code" value="true" />
<input type="hidden" name="snippets" id="snippets" value="false" />
<input type="hidden" name="repository_ref" id="repository_ref" value="master" />
<input type="hidden" name="nav_source" id="nav_source" value="navbar" />
<div class="search-autocomplete-opts hide" data-autocomplete-path="/search/autocomplete" data-autocomplete-project-id="3903" data-autocomplete-project-ref="master"></div>
</form></div>

</li>
</ul>

</div>
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav">
<li class="user-counter"><a title="待办事项列表" aria-label="待办事项列表" class="shortcuts-todos" data-qa-selector="todos_shortcut_button" data-toggle="tooltip" data-placement="bottom" data-track-label="main_navigation" data-track-event="click_to_do_link" data-track-property="navigation" data-container="body" href="/dashboard/todos"><svg class="s16" data-testid="todo-done-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#todo-done"></use></svg>
<span class="badge badge-pill hidden js-todos-count todos-count">
0
</span>
</a></li><li class="header-new dropdown" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5860&quot;}" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5860&quot;}" data-track-event="click_dropdown" data-track-label="new_dropdown" data-track-value="">
<a class="header-new-dropdown-toggle has-tooltip qa-new-menu-toggle" id="js-onboarding-new-project-link" title="新建..." ref="tooltip" aria-label="新建..." data-toggle="dropdown" data-placement="bottom" data-container="body" data-display="static" href="/projects/new"><svg class="s16" data-testid="plus-square-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#plus-square"></use></svg>
<svg class="s16 caret-down" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg>
</a><div class="dropdown-menu dropdown-menu-right addgroup">
<ul>
<li class="dropdown-bold-header">
当前项目
</li>
<li><a href="/mirrors/SheetJS/js-xlsx/-/issues/new">新建issue</a></li>
<li class="divider"></li>
<li class="dropdown-bold-header">GitCode</li>
<li><a class="qa-global-new-project-link" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5861&quot;}" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5861&quot;}" data-report-query="spm=1033.2243.3001.5861" href="/projects/new">新建项目</a></li>
<li><a data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5862&quot;}" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5862&quot;}" data-report-query="spm=1033.2243.3001.5862" href="/groups/new">新建组织</a></li>
<li><a class="qa-global-new-snippet-link" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5863&quot;}" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5863&quot;}" data-report-query="spm=1033.2243.3001.5863" href="/-/snippets/new">新建代码片段</a></li>
</ul>
</div>
</li>

<li class="dropdown header-user js-nav-user-dropdown nav-item" data-qa-selector="user_menu" data-track-event="click_dropdown" data-track-label="profile_dropdown" data-track-value="">
<a class="header-user-dropdown-toggle" data-toggle="dropdown" href="/a1647010108"><img width="23" height="23" class="header-user-avatar qa-user-avatar lazy" alt="a1647010108" data-src="https://profile-avatar.csdnimg.cn/default.jpg!1" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" />

<svg class="s16 caret-down" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg>
</a><div class="dropdown-menu dropdown-menu-right addgroup">
<ul>
<li class="current-user">
<div class="user-name gl-font-weight-bold">
a1647010108
</div>
@a1647010108
</li>
<li>
<button class="btn menu-item js-set-status-modal-trigger" type="button">
设置状态
</button>
</li>
<li>
<a class="profile-link" data-user="a1647010108" href="/a1647010108">我的主页</a>
</li>
<li>
<a data-qa-selector="settings_link" href="/-/profile">设置</a>
</li>


<li class="divider d-md-none"></li>
<li>
<a class="help-link" href="/gitcode/help-docs/-/wikis/">帮助</a>
</li>
<li>
<a class="keyboard-shortcuts-link js-shortcuts-modal-trigger" href="javascript:;">快捷键</a>
</li>
<li>
<a class="sign-out-link" data-qa-selector="sign_out_link" rel="nofollow" data-method="post" href="/users/sign_out">退出</a>
</li>
</ul>

</div>
</li>
</ul>
</div>
<button class="navbar-toggler d-block d-sm-none" type="button">
<span class="sr-only">切换导航</span>
<svg class="s12 more-icon js-navbar-toggle-right" data-testid="ellipsis_h-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#ellipsis_h"></use></svg>
<svg class="s12 close-icon js-navbar-toggle-left" data-testid="close-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#close"></use></svg>
</button>
</div>
</div>
</header>
<div class="js-set-status-modal-wrapper" data-current-emoji="" data-current-message="" data-default-emoji="speech_balloon"></div>
<script src="/assets/drag_sort/sortable.min-b2de54b4d3ef84fe9656f624cc02d4b9b1d9754fb038148ff1fe06422b5f0f46.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>
<script src="/assets/side_toolbar-d302f567974818ad159162cb05e273dbf0ac2a382fa364a403de4b801761fed3.js" defer="defer" nonce="kxabjrOgHRzPhdbNbitMLA=="></script>

<div class="layout-page page-with-contextual-sidebar">
<nav class="breadcrumbs container-fluid container-limited mobile_breadcrumbs" role="navigation">
<div class="breadcrumbs-container">
<button name="button" type="button" class="toggle-mobile-nav"><span class="sr-only">打开侧边栏</span>
<svg class="s16" data-testid="hamburger-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#hamburger"></use></svg>
</button><div class="breadcrumbs-links js-title-container" data-qa-selector="breadcrumb_links_content">
<ul class="list-unstyled breadcrumbs-list js-breadcrumbs-list">
<li><a class="group-path breadcrumb-item-text js-breadcrumb-item-text " href="/mirrors">mirrors</a><svg class="s8 breadcrumbs-list-angle" data-testid="angle-right-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#angle-right"></use></svg></li><li><a class="group-path breadcrumb-item-text js-breadcrumb-item-text " href="/mirrors/SheetJS">SheetJS</a><svg class="s8 breadcrumbs-list-angle" data-testid="angle-right-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#angle-right"></use></svg></li> <li><a href="/mirrors/SheetJS/js-xlsx"><span class="breadcrumb-item-text js-breadcrumb-item-text">js-xlsx</span></a><svg class="s8 breadcrumbs-list-angle" data-testid="angle-right-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#angle-right"></use></svg></li>

<li>
<h2 class="breadcrumbs-sub-title">
<a href="/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js">Repository</a>
</h2>
</li>
</ul>
</div>
<script type="application/ld+json">
{"@context":"https://schema.org","@type":"BreadcrumbList","itemListElement":[{"@type":"ListItem","position":1,"name":"mirrors","item":"https://gitcode.net/mirrors"},{"@type":"ListItem","position":2,"name":"SheetJS","item":"https://gitcode.net/mirrors/SheetJS"},{"@type":"ListItem","position":3,"name":"js-xlsx","item":"https://gitcode.net/mirrors/SheetJS/js-xlsx"},{"@type":"ListItem","position":4,"name":"Repository","item":"https://gitcode.net/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js"}]}

</script>

</div>
</nav>

<div class="nav-sidebar">
<div class="nav-sidebar-inner-scroll">
<div class="context-header">
<a title="js-xlsx" href="/mirrors/SheetJS/js-xlsx"><div class="avatar-container rect-avatar s40 project-avatar">
<div class="avatar s40 avatar-tile identicon bg5">J</div>
</div>
<div class="sidebar-context-title">
js-xlsx
</div>
</a></div>
<ul class="sidebar-top-level-items qa-project-sidebar">
<li class="home"><a class="shortcuts-project rspec-project-link" data-qa-selector="project_link" href="/mirrors/SheetJS/js-xlsx"><div class="nav-icon-container">
<svg class="s16" data-testid="home-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#home"></use></svg>
</div>
<span class="nav-item-name">
项目概览
</span>
</a></li></ul>
</div>
</div>
<div class="js-show-on-project-root project-home-panel">
<div class="row gl-mb-3">
<div class="home-panel-title-row col-md-12 col-lg-8 d-flex">
<div class="d-flex flex-column flex-wrap align-items-baseline">
<div class="d-inline-flex align-items-baseline">
<h1 class="home-panel-title gl-mt-3 gl-mb-2" data-qa-selector="project_name_content">
<a href="/mirrors">mirrors</a> / <a href="/mirrors/SheetJS">SheetJS</a> / <a href="/mirrors/SheetJS/js-xlsx">js-xlsx</a>
<div class="import_status">
<a class="success">
<svg class="s16 icon gl-mr-2" data-testid="timer-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#timer"></use></svg>
2 天 前同步成功
</a>
</div>
<span class="access-request-links gl-ml-3">
<a class=".gl-pl-3.gl-border-l-1.gl-border-l-solid.gl-border-l-gray-500 applt-power" rel="nofollow" data-method="post" href="/mirrors/SheetJS/js-xlsx/-/project_members/request_access">申请权限</a>

</span>

</h1>
</div>

</div>
</div>
<div class="project-repo-buttons col-md-12 col-lg-4 d-inline-flex justify-content-lg-end">
<div class="d-inline-flex">
<div class="js-notification-dropdown notification-dropdown home-panel-action-button gl-mt-3 gl-mr-3 dropdown inline" style="margin:0">
<form class="inline notification-form no-label" id="edit_notification_setting_3452533" action="/-/notification_settings/3452533" accept-charset="UTF-8" data-remote="true" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="patch" /><input type="hidden" name="project_id" id="project_id" value="3903" />
<input type="hidden" name="hide_label" id="hide_label" value="true" />
<input class="notification_setting_level" type="hidden" value="global" name="notification_setting[level]" id="notification_setting_level" />
<div class="d-inline-flex notification-btns" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5856&quot;}" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5856&quot;}">
<div class="js-notification-toggle-btns">
<div class="">
<button aria-label="通知设置 - 全局" class="btn btn-default btn-xs dropdown-new has-tooltip new-down-btn notifications-btn" data-container="body" data-flip="false" data-placement="top" data-target="dropdown-1883815-3903-Project" data-toggle="dropdown" id="notifications-button" title="通知设置 - 全局" type="button">
<span class="new-down-left">
<svg class="s16 icon notifications-icon js-notifications-icon" data-testid="notifications-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#notifications"></use></svg>
</span>
<span class="js-notification-loading fa hidden"></span>
<span class="new-down-icon">
<svg class="s16 icon" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg>
</span>
</button>
<ul class="dropdown-1883815-3903-Project dropdown-menu dropdown-menu-large dropdown-menu-no-wrap dropdown-menu-selectable" role="menu">
<li role="menuitem"><a class="update-notification is-active" data-notification-level="global" data-notification-title="全局" href="#"><strong class="dropdown-menu-inner-title">全局</strong><span class="dropdown-menu-inner-content">使用全局通知设置</span></a></li>
<li role="menuitem"><a class="update-notification " data-notification-level="watch" data-notification-title="关注" href="#"><strong class="dropdown-menu-inner-title">关注</strong><span class="dropdown-menu-inner-content">接收所有活动的通知</span></a></li>
<li role="menuitem"><a class="update-notification " data-notification-level="participating" data-notification-title="参与" href="#"><strong class="dropdown-menu-inner-title">参与</strong><span class="dropdown-menu-inner-content">只接收参与的讨论的通知</span></a></li>
<li role="menuitem"><a class="update-notification " data-notification-level="mention" data-notification-title="提及" href="#"><strong class="dropdown-menu-inner-title">提及</strong><span class="dropdown-menu-inner-content">只接收评论中提及(@)你的通知</span></a></li>
<li role="menuitem"><a class="update-notification " data-notification-level="disabled" data-notification-title="停用" href="#"><strong class="dropdown-menu-inner-title">停用</strong><span class="dropdown-menu-inner-content">不会收到任何通知邮件</span></a></li>
<li class="divider"></li>
<li>
<a class="update-notification" data-notification-level="custom" data-notification-title="Custom" data-target="#modal-1883815-3903-Project" data-toggle="modal" href="#" role="button">
<strong class="dropdown-menu-inner-title">自定义</strong>
<span class="dropdown-menu-inner-content">只接收选择的事件通知</span>
</a>
</li>
</ul>

</div>
</div>
<span class="notification-count count-badge-count d-flex align-items-center">
<a title="关注用户" class="count" href="/mirrors/SheetJS/js-xlsx/-/notificationers">1927
</a></span>
</div>
</form></div>

</div>
<div class="count-buttons d-inline-flex">
<div class="count-badge d-inline-flex align-item-stretch gl-mr-3" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5869&quot;}" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5869&quot;}">
<button class="count-badge-button btn btn-default btn-xs d-flex align-items-center star-btn toggle-star" data-endpoint="/mirrors/SheetJS/js-xlsx/toggle_star.json" type="button">
<svg class="s16 icon" data-testid="star-o-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#star-o"></use></svg>
<span>Star</span>
</button>
<span class="star-count count-badge-count d-flex align-items-center">
<a title="Star用户" class="count" href="/mirrors/SheetJS/js-xlsx/-/starrers">32894
</a></span>
</div>

<div class="count-badge d-inline-flex align-item-stretch gl-mr-3" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5870&quot;}" data-report-query="spm=1033.2243.3001.5870" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5870&quot;}">
<a class="btn btn-default btn-xs has-tooltip count-badge-button d-flex align-items-center fork-btn " href="/mirrors/SheetJS/js-xlsx/-/forks/new"><svg class="s16 icon" data-testid="fork-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#fork"></use></svg>
<span>Fork</span>
</a><span class="fork-count count-badge-count d-flex align-items-center">
<a title="Fork" class="count" href="/mirrors/SheetJS/js-xlsx/-/forks">8029
</a></span>
</div>

</div>

</div>
</div>
</div>

<style>
  .wiki-icon {
    display: none; }
</style>
<div class="head-nav-box">
<ul class="head-nav">
<li class="home active"><a class="shortcuts-project rspec-project-link" data-qa-selector="project_link" href="/mirrors/SheetJS/js-xlsx"><span>
代码
</span>
</a><ul class="subnav">
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/tree/master">文件
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/commits/master">提交
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/branches">分支
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/tags">Tags
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/graphs/master">贡献者
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/network/master">分支图
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/compare?from=master&amp;to=master">Diff
</a></li>
</ul>
</li><li class=""><a title="Issue" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5874&quot;}" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5874&quot;}" data-report-query="spm=1033.2243.3001.5874" href="/mirrors/SheetJS/js-xlsx/-/issues"><span>
Issue
<span class="project-num">
0
</span>
</span>
</a><ul class="subnav">
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/issues">列表
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/boards">看板
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/labels">标记
</a></li>
<li>
<a href="/mirrors/SheetJS/js-xlsx/-/milestones">里程碑
</a></li>
</ul>
</li><li class=""><a class="shortcuts-merge_requests" data-qa-selector="merge_requests_link" href="/mirrors/SheetJS/js-xlsx/-/merge_requests"><span>
合并请求
<span class="project-num">
0
</span>
</span>
</a></li><li class=""><a class="shortcuts-wiki" data-qa-selector="wiki_link" href="/mirrors/SheetJS/js-xlsx/-/wikis/home"><div class="nav-icon-container wiki-icon">
<svg class="s16" data-testid="book-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#book"></use></svg>
</div>
<span class="nav-item-name">
Wiki
<span class="project-num">
0
</span>
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/wikis/home"><strong class="fly-out-top-item-name">
Wiki
</strong>
</a></li></ul>
</li>
<li class=""><a href="/mirrors/SheetJS/js-xlsx/-/graphs/master/charts"><span>
分析
</span>
</a><ul class="subnav">
<li>
<a title="仓库" class="shortcuts-pipelines" href="/mirrors/SheetJS/js-xlsx/-/graphs/master/charts">仓库
</a></li>
<li>
<a title="流水线" class="shortcuts-builds" href="/mirrors/SheetJS/js-xlsx/-/pipelines/charts">DevOps
</a></li>
</ul>
</li><li class=""><a title="成员" class="qa-members-link" id="js-onboarding-members-link" href="/mirrors/SheetJS/js-xlsx/-/project_members"><span>
项目成员
</span>
</a></li><li class=""><a title="Pages" class="qa-pages-link" id="js-onboarding-pages-link" href="/mirrors/SheetJS/js-xlsx/-/common_pages"><span>
Pages
</span>
</a></li></ul>
</div>

<div class="nav-sidebar">
<div class="nav-sidebar-inner-scroll">
<div class="context-header">
<a title="js-xlsx" href="/mirrors/SheetJS/js-xlsx"><div class="avatar-container rect-avatar s40 project-avatar">
<div class="avatar s40 avatar-tile identicon bg5">J</div>
</div>
<div class="sidebar-context-title">
js-xlsx
</div>
</a></div>
<ul class="sidebar-top-level-items qa-project-sidebar">
<li class="home"><a class="shortcuts-project rspec-project-link" data-qa-selector="project_link" href="/mirrors/SheetJS/js-xlsx"><div class="nav-icon-container">
<svg class="s16" data-testid="home-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#home"></use></svg>
</div>
<span class="nav-item-name">
项目概览
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx"><strong class="fly-out-top-item-name">
项目概览
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="项目详情" class="shortcuts-project" href="/mirrors/SheetJS/js-xlsx"><span>详情</span>
</a></li><li class=""><a title="发布" class="shortcuts-project-releases" href="/mirrors/SheetJS/js-xlsx/-/releases"><span>发布</span>
</a></li></ul>
</li><li class="active"><a class="shortcuts-tree" data-qa-selector="repository_link" href="/mirrors/SheetJS/js-xlsx/-/tree/master"><div class="nav-icon-container">
<svg class="s16" data-testid="doc-text-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#doc-text"></use></svg>
</div>
<span class="nav-item-name" id="js-onboarding-repo-link">
仓库
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item active"><a href="/mirrors/SheetJS/js-xlsx/-/tree/master"><strong class="fly-out-top-item-name">
仓库
</strong>
</a></li><li class="divider fly-out-top-item"></li>
<li class="active"><a href="/mirrors/SheetJS/js-xlsx/-/tree/master">文件
</a></li><li class=""><a id="js-onboarding-commits-link" href="/mirrors/SheetJS/js-xlsx/-/commits/master">提交
</a></li><li class=""><a data-qa-selector="branches_link" id="js-onboarding-branches-link" href="/mirrors/SheetJS/js-xlsx/-/branches">分支
</a></li><li class=""><a data-qa-selector="tags_link" href="/mirrors/SheetJS/js-xlsx/-/tags">标签
</a></li><li class=""><a href="/mirrors/SheetJS/js-xlsx/-/graphs/master">贡献者
</a></li><li class=""><a href="/mirrors/SheetJS/js-xlsx/-/network/master">分支图
</a></li><li class=""><a href="/mirrors/SheetJS/js-xlsx/-/compare?from=master&amp;to=master">比较
</a></li>
</ul>
</li><li class=""><a class="shortcuts-issues qa-issues-item" href="/mirrors/SheetJS/js-xlsx/-/issues"><div class="nav-icon-container">
<svg class="s16" data-testid="issues-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#issues"></use></svg>
</div>
<span class="nav-item-name" id="js-onboarding-issues-link">
Issue
</span>
<span class="badge badge-pill count issue_counter">
0
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/issues"><strong class="fly-out-top-item-name">
Issue
</strong>
<span class="badge badge-pill count issue_counter fly-out-badge">
0
</span>
</a></li><li class="divider fly-out-top-item"></li>
<li class=""><a title="Issue" href="/mirrors/SheetJS/js-xlsx/-/issues"><span>
列表
</span>
</a></li><li class=""><a title="看板" data-qa-selector="issue_boards_link" href="/mirrors/SheetJS/js-xlsx/-/boards"><span>
看板
</span>
</a></li><li class=""><a title="标记" class="qa-labels-link" href="/mirrors/SheetJS/js-xlsx/-/labels"><span>
标记
</span>
</a></li><li class=""><a title="里程碑" class="qa-milestones-link" href="/mirrors/SheetJS/js-xlsx/-/milestones"><span>
里程碑
</span>
</a></li></ul>
</li><li class=""><a class="shortcuts-merge_requests" data-qa-selector="merge_requests_link" href="/mirrors/SheetJS/js-xlsx/-/merge_requests"><div class="nav-icon-container">
<svg class="s16" data-testid="git-merge-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#git-merge"></use></svg>
</div>
<span class="nav-item-name" id="js-onboarding-mr-link">
合并请求
</span>
<span class="badge badge-pill count merge_counter js-merge-counter">
0
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/merge_requests"><strong class="fly-out-top-item-name">
合并请求
</strong>
<span class="badge badge-pill count merge_counter js-merge-counter fly-out-badge">
0
</span>
</a></li></ul>
</li>
<li class=""><a title="Pages" class="qa-pages-link" id="js-onboarding-pages-link" href="/mirrors/SheetJS/js-xlsx/-/common_pages"><div class="nav-icon-container">
<svg class="s16" data-testid="rocket-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#rocket"></use></svg>
</div>
<span class="nav-item-name" id="js-onboarding-pipelines-link">
Pages
</span>
</a></li><li class=""><a href="/mirrors/SheetJS/js-xlsx/-/graphs/master/charts"><div class="nav-icon-container">
<svg class="s16" data-testid="chart-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chart"></use></svg>
</div>
<span class="nav-item-name" data-qa-selector="analytics_link">
分析
</span>
</a><ul class="sidebar-sub-level-items">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/graphs/master/charts"><strong class="fly-out-top-item-name">
分析
</strong>
</a></li><li class="divider fly-out-top-item">
<li class=""><a href="/mirrors/SheetJS/js-xlsx/-/graphs/master/charts"><span>
仓库分析
</span>
</a></li></li>
<li class="divider fly-out-top-item">
<li class=""><a title="流水线" class="shortcuts-builds" href="/mirrors/SheetJS/js-xlsx/-/pipelines/charts"><span>
DevOps
</span>
</a></li></li>
</ul>
</li>
<li class=""><a class="shortcuts-wiki" data-qa-selector="wiki_link" href="/mirrors/SheetJS/js-xlsx/-/wikis/home"><div class="nav-icon-container wiki-icon">
<svg class="s16" data-testid="book-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#book"></use></svg>
</div>
<span class="nav-item-name">
Wiki
<span class="project-num">
0
</span>
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/wikis/home"><strong class="fly-out-top-item-name">
Wiki
</strong>
</a></li></ul>
</li>
<li class=""><a title="成员" class="qa-members-link" id="js-onboarding-members-link" href="/mirrors/SheetJS/js-xlsx/-/project_members"><div class="nav-icon-container">
<svg class="s16" data-testid="users-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#users"></use></svg>
</div>
<span class="nav-item-name">
成员
</span>
</a><ul class="sidebar-sub-level-items is-fly-out-only">
<li class="fly-out-top-item"><a href="/mirrors/SheetJS/js-xlsx/-/project_members"><strong class="fly-out-top-item-name">
成员
</strong>
</a></li></ul>
</li><a class="toggle-sidebar-button js-toggle-sidebar qa-toggle-sidebar rspec-toggle-sidebar" role="button" title="Toggle sidebar" type="button">
<svg class="s16 icon-chevron-double-lg-left" data-testid="chevron-double-lg-left-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-double-lg-left"></use></svg>
<svg class="s16 icon-chevron-double-lg-right" data-testid="chevron-double-lg-right-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-double-lg-right"></use></svg>
<span class="collapse-text">收起侧边栏</span>
</a>
<button name="button" type="button" class="close-nav-button"><svg class="s16" data-testid="close-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#close"></use></svg>
<span class="collapse-text">关闭侧边栏</span>
</button>
<li class="hidden">
<a title="动态" class="shortcuts-project-activity" href="/mirrors/SheetJS/js-xlsx/activity"><span>
动态
</span>
</a></li>
<li class="hidden">
<a title="网络" class="shortcuts-network" href="/mirrors/SheetJS/js-xlsx/-/network/master">分支图
</a></li>
<li class="hidden">
<a class="shortcuts-new-issue" href="/mirrors/SheetJS/js-xlsx/-/issues/new">创建新Issue
</a></li>
<li class="hidden">
<a title="提交" class="shortcuts-commits" href="/mirrors/SheetJS/js-xlsx/-/commits/master">提交
</a></li>
<li class="hidden">
<a title="Issue看板" class="shortcuts-issue-boards" href="/mirrors/SheetJS/js-xlsx/-/boards">Issue看板</a>
</li>
</ul>
</div>
</div>


<div class="content-wrapper">
<div class="mobile-overlay"></div>

<div class="alert-wrapper gl-force-block-formatting-context">












<div class="alert alert-warning hide" id="unCheckEmailAlert" role="alert" style="margin-left:16px;margin-right:16px">
你好, a1647010108
由于你当前未设置邮箱地址，将无法使用 GitCode服务，
<a class="js-open-email-box" href="javascript:;">点击补充邮箱信息，
</a><a class="js-logout-btn" data-qa-selector="sign_out_link" rel="nofollow" data-method="delete" href="/sign_out?requestUrl=%2Fsign_in">切换账号登录。
</a></div>
<div class="alert alert-primary alert-dismissible fade show" data-dismiss="alert" id="checkEmailAlert" role="alert" style="display:none;margin-left:16px;margin-right:16px">
你的邮箱已经设置成功。
<button aria-label="close" class="close" data-dismiss="alert" style="line-height:0.7" type="button">
<span aria-hidden="true">
&times;
</span>
</button>
</div>
<div aria-hidden="true" aria-labelledby="inputEmailDialogLabel" class="modal" id="inputEmailDialog" role="dialog" tabindex="-1">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h3 class="modal-title" id="inputEmailDialogLabel">
设置邮箱
</h3>
<button aria-label="关闭" class="close" data-dismiss="modal" type="button">
<span aria-hidden>&times;</span>
</button>
</div>
<div class="modal-body">
 GitCode服务需要使用你的邮箱，请设置你的常用邮箱
<form id="emailForm">
<div class="form-group">
<label for="useremail">
请填写你的常用邮箱
</label>
<input class="form-control" id="useremail" placeholder="email@example.com" type="email">
</div>
</form>
<div class="err-msg">
请输入正确邮箱地址
</div>
</div>
<div class="modal-footer">
<button class="btn btn-secondary" data-dismiss="modal" type="button">
关闭
</button>
<button class="btn btn-primary js-set-email" type="button">
保存
</button>
</div>
</div>
</div>
</div>

<div class="d-flex"></div>
</div>
<div class="container-fluid container-limited ">
<div class="content" id="content-body">
<div class="flash-container flash-container-page sticky" data-qa-selector="flash_container">
</div>

<div class="js-signature-container" data-signatures-path="/mirrors/SheetJS/js-xlsx/-/commits/515d1c6f2e1d3ca422ee9198b177cfd926434936/signatures?limit=1"></div>

<div class="tree-holder" id="tree-holder">
<div class="nav-block">
<div class="tree-ref-container">
<div class="tree-ref-holder">
<style>
  .qa-branches-select {
    height: 34px;
  }
</style>
<form class="project-refs-form" action="/mirrors/SheetJS/js-xlsx/-/refs/switch" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="destination" id="destination" value="blob" />
<input type="hidden" name="path" id="path" value="xlsx.mini.js" />
<div class="dropdown branches-dropdown">
<button class="dropdown-menu-toggle js-project-refs-dropdown qa-branches-select" type="button" data-report-view="{&quot;spm&quot;:&quot;1033.2243.3001.5864&quot;}" data-report-click="{&quot;spm&quot;:&quot;1033.2243.3001.5864&quot;}" data-toggle="dropdown" data-selected="master" data-ref="master" data-refs-url="/mirrors/SheetJS/js-xlsx/refs?sort=updated_desc" data-field-name="ref" data-submit-form-on-click="true" data-visit="true"><span class="dropdown-toggle-text ">master</span><svg class="s16 dropdown-menu-toggle-icon gl-top-3" data-testid="chevron-down-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#chevron-down"></use></svg></button>
<div class="dropdown-menu dropdown-menu-paging dropdown-menu-selectable git-revision-dropdown qa-branches-dropdown">
<div class="dropdown-page-one">
<div class="dropdown-title gl-display-flex"><span class="gl-ml-auto">切换分支/标签</span><button class="dropdown-title-button dropdown-menu-close gl-ml-auto" aria-label="Close" type="button"><svg class="s16 dropdown-menu-close-icon" data-testid="close-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#close"></use></svg></button></div>
<div class="dropdown-input"><input type="search" id="" class="dropdown-input-field qa-dropdown-input-field" placeholder="搜索分支和标签" autocomplete="off" /><svg class="s16 dropdown-input-search" data-testid="search-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#search"></use></svg><svg class="s16 dropdown-input-clear js-dropdown-input-clear" data-testid="close-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#close"></use></svg></div>
<div class="dropdown-content"></div>
<div class="dropdown-loading"><div class="gl-spinner-container"><span class="gl-spinner gl-spinner-orange gl-spinner-md gl-mt-7" aria-label="加载中"></span></div></div>
</div>
</div>
</div>
</form>
</div>
<ul class="breadcrumb repo-breadcrumb">
<li class="breadcrumb-item">
<a href="/mirrors/SheetJS/js-xlsx/-/tree/master">js-xlsx
</a></li>
<li class="breadcrumb-item">
<a href="/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js"><strong>xlsx.mini.js</strong>
</a></li>
</ul>
</div>
<div class="tree-controls gl-children-ml-sm-3"><a class="gl-button btn shortcuts-find-file" rel="nofollow" href="/mirrors/SheetJS/js-xlsx/-/find_file/master">查找文件
</a><a class="btn js-blob-blame-link" href="/mirrors/SheetJS/js-xlsx/-/blame/master/xlsx.mini.js">Blame</a><a class="btn" href="/mirrors/SheetJS/js-xlsx/-/commits/master/xlsx.mini.js">历史</a><a class="btn js-data-file-blob-permalink-url" href="/mirrors/SheetJS/js-xlsx/-/blob/515d1c6f2e1d3ca422ee9198b177cfd926434936/xlsx.mini.js">永久链接</a><a class="gl-button btn js-data-file-blob-permalink-url" href="/mirrors/SheetJS/js-xlsx/-/blob/515d1c6f2e1d3ca422ee9198b177cfd926434936/xlsx.mini.js">Permalink</a></div>
</div>

<div class="info-well d-none d-sm-block">
<div class="well-segment">
<ul class="blob-commit-info">
<li class="commit flex-row js-toggle-container" id="commit-515d1c6f">
<div class="avatar-cell d-none d-sm-block">
<a href="mailto:dev@sheetjs.com"><a href="javascript:void(0)" class="avatar s40 d-none d-sm-inline-block identicon bg3" style="text-decoration: none">S</a></a>
</div>
<div class="commit-detail flex-list">
<div class="commit-content" data-qa-selector="commit_content">
<a class="commit-row-message item-title js-onboarding-commit-item " href="/mirrors/SheetJS/js-xlsx/-/commit/515d1c6f2e1d3ca422ee9198b177cfd926434936">mini refresh [ci skip]</a>
<span class="commit-row-message d-inline d-sm-none">
&middot;
515d1c6f
</span>
<div class="committer">
由 <a class="commit-author-link" href="mailto:dev@sheetjs.com">SheetJS</a> 提交于 <time class="js-timeago" title="10月 5, 2022 4:50上午" datetime="2022-10-04T20:50:55Z" data-toggle="tooltip" data-placement="bottom" data-container="body">10月 04, 2022</time>
</div>

</div>
<div class="commit-actions flex-row">

<div class="commit-sha-group btn-group d-none d-sm-flex">
<div class="label label-monospace monospace">
515d1c6f
</div>
<button class="btn gl-button btn btn-default" data-toggle="tooltip" data-placement="bottom" data-container="body" data-title="复制提交SHA" data-class="gl-button btn btn-default" data-clipboard-text="515d1c6f2e1d3ca422ee9198b177cfd926434936" type="button" title="复制提交SHA" aria-label="复制提交SHA"><svg class="s16" data-testid="copy-to-clipboard-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#copy-to-clipboard"></use></svg></button>

</div>
</div>
</div>
</li>

</ul>
</div>


</div>
<div class="blob-content-holder" id="blob-content-holder">
<article class="file-holder">
<div class="js-file-title file-title-flex-parent">
<div class="file-header-content">
<svg class="s16" data-testid="doc-text-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#doc-text"></use></svg>
<strong class="file-title-name gl-word-break-all" data-qa-selector="file_name_content">
xlsx.mini.js
</strong>
<button class="btn btn-clipboard btn-transparent" data-toggle="tooltip" data-placement="bottom" data-container="body" data-class="btn-clipboard btn-transparent" data-title="复制文件路径" data-clipboard-text="{&quot;text&quot;:&quot;xlsx.mini.js&quot;,&quot;gfm&quot;:&quot;`xlsx.mini.js`&quot;}" type="button" title="复制文件路径" aria-label="复制文件路径"><svg class="s16" data-testid="copy-to-clipboard-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#copy-to-clipboard"></use></svg></button>
<small class="mr-1">
437.2 KB
</small>
</div>

<div class="file-actions gl-display-flex gl-flex-fill-1 gl-align-self-start gl-md-justify-content-end"><button name="button" type="submit" class="btn btn-primary js-edit-blob js-critical-verification ml-2  js-edit-blob-link-fork-toggler" data-action="edit" data-fork-path="/mirrors/SheetJS/js-xlsx/-/forks?continue%5Bnotice%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E5%B7%B2%E7%BB%8F%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bnotice_now%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E6%AD%A3%E5%9C%A8%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bto%5D=javascript%3A%3B&amp;namespace_key=1925961">编辑</button><button name="button" type="submit" class="btn btn-primary ide-edit-button ml-2 gl-mr-3 btn-inverted js-edit-blob-link-fork-toggler" data-action="edit" data-fork-path="/mirrors/SheetJS/js-xlsx/-/forks?continue%5Bnotice%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E5%B7%B2%E7%BB%8F%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bnotice_now%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E6%AD%A3%E5%9C%A8%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bto%5D=%2F-%2Fide%2Fproject%2Fa1647010108%2Fjs-xlsx%2Fedit%2Fmaster%2F-%2Fxlsx.mini.js&amp;namespace_key=1925961">Web IDE</button><div class="btn-group ml-2" role="group">

<button name="button" type="submit" class="btn btn-default js-edit-blob-link-fork-toggler" data-action="replace" data-fork-path="/mirrors/SheetJS/js-xlsx/-/forks?continue%5Bnotice%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E5%B7%B2%E7%BB%8F%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82%E5%86%8D%E6%AC%A1%E5%B0%9D%E8%AF%95replace%E6%AD%A4%E6%96%87%E4%BB%B6%E3%80%82&amp;continue%5Bnotice_now%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E6%AD%A3%E5%9C%A8%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bto%5D=%2Fmirrors%2FSheetJS%2Fjs-xlsx%2F-%2Fblob%2Fmaster%2Fxlsx.mini.js&amp;namespace_key=1925961">替换</button>
<button name="button" type="submit" class="btn btn-default js-edit-blob-link-fork-toggler" data-action="delete" data-fork-path="/mirrors/SheetJS/js-xlsx/-/forks?continue%5Bnotice%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E5%B7%B2%E7%BB%8F%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82%E5%86%8D%E6%AC%A1%E5%B0%9D%E8%AF%95delete%E6%AD%A4%E6%96%87%E4%BB%B6%E3%80%82&amp;continue%5Bnotice_now%5D=%E4%BD%A0%E4%B8%8D%E8%83%BD%E7%9B%B4%E6%8E%A5%E5%AF%B9%E6%AD%A4%E9%A1%B9%E7%9B%AE%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%E3%80%82%E6%AD%A4%E9%A1%B9%E7%9B%AE%E7%9A%84Fork%E9%A1%B9%E7%9B%AE%E6%AD%A3%E5%9C%A8%E5%88%9B%E5%BB%BA%EF%BC%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E5%9C%A8Fork%E9%A1%B9%E7%9B%AE%E4%B8%AD%E8%BF%9B%E8%A1%8C%E6%9B%B4%E6%94%B9%EF%BC%8C%E4%BB%A5%E4%BE%BF%E6%8F%90%E4%BA%A4%E5%90%88%E5%B9%B6%E8%AF%B7%E6%B1%82%E3%80%82&amp;continue%5Bto%5D=%2Fmirrors%2FSheetJS%2Fjs-xlsx%2F-%2Fblob%2Fmaster%2Fxlsx.mini.js&amp;namespace_key=1925961">删除</button>
</div><div class="btn-group ml-2" role="group">
<button class="btn btn btn-sm js-copy-blob-source-btn" data-toggle="tooltip" data-placement="bottom" data-container="body" data-class="btn btn-sm js-copy-blob-source-btn" data-title="复制文件内容" data-clipboard-target=".blob-content[data-blob-id=&#39;55162b209d2ddce5ac12ffce0a4875dd81c20bf0&#39;] &gt; pre" type="button" title="复制文件内容" aria-label="复制文件内容"><svg class="s16" data-testid="copy-to-clipboard-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#copy-to-clipboard"></use></svg></button>
<a class="btn btn-sm has-tooltip" target="_blank" rel="noopener noreferrer" aria-label="打开原始文件" title="打开原始文件" data-container="body" href="/mirrors/SheetJS/js-xlsx/-/raw/master/xlsx.mini.js"><svg class="s16" data-testid="doc-code-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#doc-code"></use></svg></a>
<a download="xlsx.mini.js" class="btn btn-sm has-tooltip" target="_blank" rel="noopener noreferrer" aria-label="下载" title="下载" data-container="body" href="/mirrors/SheetJS/js-xlsx/-/raw/master/xlsx.mini.js?inline=false"><svg class="s16" data-testid="download-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#download"></use></svg></a>

</div></div>
</div>
<div class="js-file-fork-suggestion-section file-fork-suggestion hidden">
<span class="file-fork-suggestion-note">
你没有权限
<span class="js-file-fork-suggestion-section-action">
edit
</span>
files in this project directly. Please fork this project,
make your changes there, and submit a merge request.
</span>
<a class="js-fork-suggestion-button gl-button btn btn-grouped btn-inverted btn-success" rel="nofollow" data-method="post" href="/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js">Fork</a>
<button class="js-cancel-fork-suggestion-button gl-button btn btn-grouped" type="button">
Cancel
</button>
</div>



<div class="blob-viewer" data-path="xlsx.mini.js" data-type="simple" data-url="/mirrors/SheetJS/js-xlsx/-/blob/master/xlsx.mini.js?format=json&amp;viewer=simple">
<div class="text-center gl-mt-4 gl-mb-3">
<span class="gl-spinner gl-spinner-orange gl-spinner-md qa-spinner" aria-label="加载中"></span>
</div>

</div>


</article>
</div>

<div class="modal" id="modal-upload-blob">
<div class="modal-dialog modal-lg">
<div class="modal-content">
<div class="modal-header">
<h3 class="page-title">Replace xlsx.mini.js</h3>
<button aria-label="关闭" class="close" data-dismiss="modal" type="button">
<span aria-hidden>&times;</span>
</button>
</div>
<div class="modal-body">
<form class="js-quick-submit js-upload-blob-form" data-method="put" action="/mirrors/SheetJS/js-xlsx/-/update/master/xlsx.mini.js" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="put" /><input type="hidden" name="authenticity_token" value="tbzc2m/TPYrELc8mDIf/GJKsQK/5S8a1NZ09YTeUZIzVJYYon49SGKVtxv8lx5xh5Sc7qrZIR/kns8Okx9dRnw==" /><div class="dropzone">
<div class="dropzone-previews blob-upload-dropzone-previews">
<p class="dz-message light">
拖放文件到此处或<a class="markdown-selector" href="#">点击上传</a>
</p>
</div>
</div>
<br>
<div class="dropzone-alerts gl-alert gl-alert-danger gl-mb-5 data" style="display:none"></div>
<div class="form-group row commit_message-group">
<label class="col-form-label col-sm-2" for="commit_message-c7aa962777b09e78d6e1eb15beb1c83a">提交信息
</label><div class="col-sm-10">
<div class="commit-message-container">
<div class="max-width-marker"></div>
<textarea name="commit_message" id="commit_message-c7aa962777b09e78d6e1eb15beb1c83a" class="form-control js-commit-message" placeholder="Replace xlsx.mini.js" required="required" rows="3">
Replace xlsx.mini.js</textarea>
</div>
</div>
</div>

<input type="hidden" name="branch_name" id="branch_name" />
<input type="hidden" name="create_merge_request" id="create_merge_request" value="1" />
<input type="hidden" name="original_branch" id="original_branch" value="master" class="js-original-branch" />

<div class="form-actions">
<button name="button" type="button" class="btn gl-button btn-success btn-upload-file" id="submit-all"><div class="spinner spinner-sm gl-mr-2 js-loading-icon hidden"></div>
Replace file
</button><a class="btn gl-button btn-cancel" data-dismiss="modal" href="#">取消</a>
<div class="inline gl-ml-3">
将在Fork(fork)项目中中创建一个新的分支, 并开启一个新的合并请求。
</div>

</div>
</form></div>
</div>
</div>
</div>

</div>


</div>
</div>
</div>
</div>
<footer class="csdn_footer">
<div class="limit-width content">
<div class="l">
<img class="logo" src="https://gitcode.net/gitcode/operation-work/uploads/e20ba4af1abc3fcb42154e15584104e5/footer.png">
<a class="powered" href="#" target="_blank">京ICP备19004658号-14</a>
<a class="powered" href="#" target="_blank">网络110报警服务</a>
<a class="powered" href="https://about.gitlab.com/releases/2020/12/22/gitlab-13-7-released/" target="_blank">Powered&nbsp;by&nbsp;GitLab&nbsp;CE&nbsp;v13.7</a>
</div>
<div class="m">
<h5 class="title">开源知识</h5>
<div class="link">
<a href="/courses/detail/1/l" target="_blank">Git 入门</a>
<a href="https://codechina_dev.gitcode.host/progit2" target="_blank">Pro Git 电子书</a>
<a href="https://codechina_dev.gitcode.host/learn-git-branching" target="_blank">在线学 Git</a>
<br>
<a href="/courses/detail/2/l" target="_blank">Markdown 基础入门</a>
<a href="https://dev-roadmap.gitcode.host" target="_blank">IT 技术知识开源图谱</a>
</div>
<h5 class="title">帮助</h5>
<div class="link">
<div class="line">
<a href="/gitcode/help-docs/-/wikis/home" target="_blank">使用手册</a>
<a href="/gitcode/help-docs/-/issues" target="_blank">反馈建议</a>
<a href="https://gitcode.blog.csdn.net/" target="_blank">博客</a>
</div>
<div class="line">
<a href="https://blog.csdn.net/csdn_codechina/article/details/124885497" target="_blank">《GitCode 隐私声明》</a>
<a href="https://blog.csdn.net/csdn_codechina/article/details/124608557" target="_blank">《GitCode 服务条款》</a>
<a href="https://about.gitcode.net/" target="_blank">关于GitCode</a>
</div>
</div>
</div>
<div class="r">
<img height="150" src="https://gitcode.net/gitcode/operation-work/uploads/7e1f373b8d6c3207e1ab5cc04f4e5ff2/qrcode.png">
<a class="powered mobile-show" href="https://about.gitlab.com/releases/2020/12/22/gitlab-13-7-released/" target="_blank">Powered&nbsp;by&nbsp;GitLab&nbsp;CE&nbsp;v13.7</a>
</div>
</div>
</footer>



<script nonce="kxabjrOgHRzPhdbNbitMLA==">
//<![CDATA[
if ('loading' in HTMLImageElement.prototype) {
  document.querySelectorAll('img.lazy').forEach(img => {
    img.loading = 'lazy';
    let imgUrl = img.dataset.src;
    // Only adding width + height for avatars for now
    if (imgUrl.indexOf('/avatar/') > -1 && imgUrl.indexOf('?') === -1) {
      const targetWidth = img.getAttribute('width') || img.width;
      imgUrl += `?width=${targetWidth}`;
    }
    img.src = imgUrl;
    img.removeAttribute('data-src');
    img.classList.remove('lazy');
    img.classList.add('js-lazy-loaded', 'qa-js-lazy-loaded');
  });
}

//]]>
</script>
<div aria-labelledby="custom-notifications-title" class="modal fade" id="modal-1883815-3903-Project" role="dialog" tabindex="-1">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title" id="custom-notifications-title">
自定义通知事件
</h4>
<button aria-label="关闭" class="close" data-dismiss="modal" type="button">
<span aria-hidden>&times;</span>
</button>
</div>
<div class="modal-body">
<div class="container-fluid">
<form class="custom-notifications-form" id="edit_notification_setting_3452533" action="/-/notification_settings/3452533" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="&#x2713;" /><input type="hidden" name="_method" value="patch" /><input type="hidden" name="authenticity_token" value="PXznr6rfYWSay4tsF8vp59w6DMtsa5Aj83Yo51+eHg9d5b1dWoMO9vuLgrU+i4qeq7F3ziNoEW/hWNYir90rHA==" /><input type="hidden" name="project_id" id="project_id" value="3903" />
<input type="hidden" name="hide_label" id="hide_label" value="true" />
<div class="row">
<div class="col-lg-4">
<h4 class="gl-mt-0">通知事件</h4>
<p>
自定义通知级别继承自参与级别。使用自定义通知级别，你会收到参与级别及选定事件的通知。想了解更多信息，请查看 <a target="_blank" href="/gitcode/help-docs/-/wikis/docs/user/account/email-notify">通知邮件</a>.
</p>
</div>
<div class="col-lg-8">
<div class="form-group">
<div class="form-check gl-mt-0">
<input name="notification_setting[new_release]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[new_release]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[new_release]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[new_release]">
<strong>
新发布
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[new_note]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[new_note]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[new_note]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[new_note]">
<strong>
新建评论
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[new_issue]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[new_issue]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[new_issue]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[new_issue]">
<strong>
新建Issue
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[reopen_issue]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[reopen_issue]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[reopen_issue]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[reopen_issue]">
<strong>
重启Issue
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[close_issue]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[close_issue]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[close_issue]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[close_issue]">
<strong>
关闭issue
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[reassign_issue]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[reassign_issue]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[reassign_issue]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[reassign_issue]">
<strong>
重新指派Issue
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[issue_due]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[issue_due]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[issue_due]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[issue_due]">
<strong>
Issue due
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[new_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[new_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[new_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[new_merge_request]">
<strong>
新建合并请求
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[push_to_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[push_to_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[push_to_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[push_to_merge_request]">
<strong>
Push to merge request
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[reopen_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[reopen_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[reopen_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[reopen_merge_request]">
<strong>
Reopen merge request
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[close_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[close_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[close_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[close_merge_request]">
<strong>
关闭合并请求
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[reassign_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[reassign_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[reassign_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[reassign_merge_request]">
<strong>
重新指派合并请求
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[change_reviewer_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[change_reviewer_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[change_reviewer_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[change_reviewer_merge_request]">
<strong>
Change reviewer merge request
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[merge_merge_request]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[merge_merge_request]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[merge_merge_request]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[merge_merge_request]">
<strong>
合并请求被合并
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[failed_pipeline]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[failed_pipeline]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" checked="checked" name="notification_setting[failed_pipeline]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[failed_pipeline]">
<strong>
流水线失败
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[fixed_pipeline]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[fixed_pipeline]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" checked="checked" name="notification_setting[fixed_pipeline]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[fixed_pipeline]">
<strong>
修复的流水线
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[success_pipeline]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[success_pipeline]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" name="notification_setting[success_pipeline]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[success_pipeline]">
<strong>
流水线成功完成
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
<div class="form-group">
<div class="form-check">
<input name="notification_setting[moved_project]" type="hidden" value="0" /><input id="modal-1883815-3903-Project_notification_setting[moved_project]" class="js-custom-notification-event form-check-input" type="checkbox" value="1" checked="checked" name="notification_setting[moved_project]" />
<label class="form-check-label" for="modal-1883815-3903-Project_notification_setting[moved_project]">
<strong>
Moved project
<span class="spinner is-loading gl-vertical-align-middle gl-display-none"></span>
<svg class="s16 is-done gl-display-none gl-vertical-align-middle gl-text-green-600" data-testid="check-icon"><use xlink:href="/assets/icons-15cbe21ccc2237b075efb0b0d170fc8d6716882dbe4fefad34c18b914dbcf811.svg#check"></use></svg>
</strong>
</label>
</div>
</div>
</div>
</div>
</form></div>
</div>
</div>
</div>
</div>


</body>
</html>

