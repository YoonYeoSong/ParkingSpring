<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.parking.member.model.vo.Member" %>
<%@ page import="com.parking.board.model.vo.QnaBoard" %>
    
<!-------------------------
'header.jsp' contains:
  * <meta> link
  * CSS link
  * <style> attribute
  * <header> <nav> elements
-------------------------->
<%
  Member loginMember = (Member)session.getAttribute("loginMember");

  String mypageUrl = request.getContextPath();

  if(loginMember != null){
    if(loginMember.getUserEmail().equals("admin@com"))
      mypageUrl += "/admin/memberList";
    //else
    //  mypageUrl += "/views/member";
  }
  else
      mypageUrl += "/views/member/loginView.jsp";


  String userCode = "";
  if(loginMember != null)
    userCode = loginMember.getUserCode();

  String dropdownTxt ="";
  if(loginMember != null)
    dropdownTxt = "Hi! " + loginMember.getUserName();
  else
    dropdownTxt = "MyPage";
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="parking web application">
  <meta name="google-signin-scope" content="profile email">
  <meta name="google-signin-client_id" content="847270498494-dj5o04t83q5uja6ccp6eekr3en7qcb8p.apps.googleusercontent.com">
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <title>Locate Parking Spaces</title>

  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
  <!-- favicon -->
  <link rel="icon" href="https://img.icons8.com/ios/50/000000/parking.png?v=1.1">

  <!-- CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/mainpage.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/button.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/translate.css">
	<!-- 폰트 -->
	<!-- <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet"> -->
  <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">

  <!-- API -->
  <!-- 카카오 -->
  <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
	
	<!-- import -->
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>

  <script>
  function googleTranslateElementInit() {
    new google.translate.TranslateElement(
      {
        pageLanguage: 'en',
        includedLanguages: 'en,es,ja,ko,pt,ru,zh-CN,zh-TW',
        layout: google.translate.TranslateElement.InlineLayout.SIMPLE,
        autoDisplay: false
      },'google_translate_element'
    );
  }
  </script>
  <script src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

  <style>
  .goog-te-gadget-simple  {
    border: 1px solid rgba(255, 255, 255, .0);
  }
  .nav-item{
    color: #B8C8D6 !important;
  }
	*{
		font-family: "Nanum Gothic";
	}
  </style>

</head>
<body>
  <header>
    <!-- following icon images are from https://icons8.com/ -->
      <style>
        #icons8{ display:none; }
      </style>
      <a id='icons8' href="https://icons8.com/icon/15182/parking">Parking icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/36738/fiat-500">Fiat 500 icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/8800/list">List icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/20193/skyscrapers">Skyscrapers icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/10726/parking">Parking icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/32722/tesla-model-x">Tesla Model X icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/9192/dog-house">Dog House icon by Icons8</a>
      <a id='icons8' href="https://icons8.com/icon/5471/faq">FAQ icon by Icons8</a>
   

    <a id="back-to-top" href="#" class="btn btn-info border-light back-to-top py-1 px-2" role="button">
      <i class="fa fa-chevron-up">&nbsp;&nbsp;Top</i>
    </a>

    <!-- Navigation bar -->
    <nav class="site-header navbar navbar-expand-md navbar-dark fixed-top py-0 justify-content-center">
      <!-- d-flex container -->
      <div class="container d-flex justify-content-center row">
        <!-- Logo -->
        <div class='py-0 mr-auto inline px-0' id="navbar-logo">
          <a class="navbar-brand" href="<%=request.getContextPath() %>" >
            <img src="<%=request.getContextPath() %>/resources/images/logo_white.png">
          </a>
        </div>
        <!-- Nav search bar -->
        <div id="nav-searchbar" class="col-lg-6 py-0 inline-block px-0 ml-0 mr-1">
          <form action="<%=request.getContextPath()%>/map/mapListView" method="POST" class='w-100'>
            <input type="hidden" name="userCode" value="<%=userCode %>">
            <div class="input-group">
              <input type="search" placeholder="   Where do you need parking?" aria-describedby="button-addon5" class="form-control" name="search" id="nav-search">
              <div class="input-group-append">
                <button id="button-addon5" type="submit" class="btn btn-primary" id="nav-searchbar-btn"><i class="fa fa-search"></i></button>
              </div>
            </div>
          </form>
        </div>
        <!-- toggle button -->
        <button class="navbar-toggler ml-auto inline my-1" type="button" data-toggle="collapse" data-target="#navbarCollapsible" aria-controls="navbarCollapsible" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <!-- collapsible elements -->
        <div class="collapse navbar-collapse" id="navbarCollapsible">
          <ul class="navbar-nav ml-auto" id='collapseItems'>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle menu-item mt-1 mr-0 text-white" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-globe"></i></a>

              <div class="dropdown-menu mt-1" aria-labelledby="dropdown01">
                <div id="google_translate_element" class="dropdown-item fa fa-globe"></div>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle menu-item mt-1 mr-0 text-white" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Help</a>

              <div class="dropdown-menu mt-1" aria-labelledby="dropdown01">
                <a class="dropdown-item" href="<%=request.getContextPath() %>/board/helpBoardList" ><i class="fa fa-info-circle">&nbsp;&nbsp;</i>Help</a>
                <a class="dropdown-item" href="<%=request.getContextPath() %>/board/qnaBoardList" ><i class="fa fa-question-circle-o">&nbsp;&nbsp;</i>Q&amp;A Board</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle menu-item mt-1 mr-2 text-white" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=dropdownTxt %></a>

              <div class="dropdown-menu mt-1" aria-labelledby="dropdown01">

              <% if(loginMember == null) { %>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-cog">&nbsp;&nbsp;</i>Account Settings</a>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-bookmark">&nbsp;&nbsp;</i>Bookmark</a>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-edit">&nbsp;&nbsp;</i>My Reviews</a>
              <!-- <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-calendar">&nbsp;&nbsp;</i>My Reservations</a>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-credit-card">&nbsp;&nbsp;</i>Payment Methods</a>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-won">&nbsp;&nbsp;</i>Credit Balance</a>
              <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-car">&nbsp;&nbsp;</i>My Vehicle</a> -->

              <% } else { %>
                <a class="dropdown-item" href="<%=request.getContextPath() %>/views/member/memberView.jsp"><i class="fa fa-cog">&nbsp;&nbsp;</i>Settings</a>
                <a class="dropdown-item" href="<%=request.getContextPath() %>/bookmark/bookmarkView"><i class="fa fa-bookmark">&nbsp;&nbsp;</i>Bookmark</a>
                <a class="dropdown-item" href="<%=request.getContextPath() %>/board/reviewList"><i class="fa fa-edit">&nbsp;&nbsp;</i>My Reviews</a>

                <% if(loginMember.getUserEmail().equals("admin@com")) { %>
                  <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-list">&nbsp;&nbsp;</i>Member List</a>

                <% } else { %>
                  <!-- <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-calendar">&nbsp;&nbsp;</i>My Reservations</a>
                  <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-credit-card">&nbsp;&nbsp;</i>Payment Methods</a>
                  <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-won">&nbsp;&nbsp;</i>Credit Balance</a>
                  <a class="dropdown-item" href="<%=mypageUrl %>"><i class="fa fa-car">&nbsp;&nbsp;</i>My Vehicle</a> -->
                <% } %>
              <% } %>

              <style>
                /* .navbar-collapse { margin-top: 0; }
                @media (min-width:768px) {
                  .navbar-collapse { margin-top: 60px; }
                } */
              </style>
              <script>

                /**
                * sends a request to the specified url from a form. this will change the window location.
                * @param {string} urlMapping the path to send the post request to
                * @param {object} params the paramiters to add to the url
                * @param {string} [method=post] the method to use on the form
                */
                function mypageLoad(urlMapping, params){ var form = $("<form>");
                  form.attr({"method": "POST",
                             "action" : urlMapping,
                  });
                  $.each(params, function(key, value){
                    var input = $("<input>");
                    input.attr({"type": "hidden",
                                "name": key,
                                "value": value,
                    });
                    form.append(input);
                  });

                  form.submit();
                }
              </script>

              </div>
            </li>

            <% if(loginMember != null) {%>
              <li class="nav-item">
                <form action="<%=request.getContextPath() %>/logout" method="post">
                  <button type="submit" class="btn btn-sm btn-outline-light mt-2 mr-1" onclick="return logoutSnsAccount();" style="width: 71px;">Log Out</button>
                </form>
              </li>

              <script>
                function logoutSnsAccount(){
                  googleLogout();
                  return true;
                }
              </script>

            <%} else{%>
              <li class="nav-item">
                <form action="<%=request.getContextPath() %>/views/member/loginView.jsp" method="post">
                  <button type="submit" class="btn btn-sm btn-outline-light mt-2 mr-1" style="width:67px;">Log In</button>
                </form>
              </li>
              <li class="nav-item">
                <button class="btn btn-sm btn-outline-light mt-2" onclick='location.href="${pageContext.request.contextPath}/memberEnroll"' style="width:69px;">Sign Up</button>
              </li>
            <%} %>
          </ul>
        </div>
      </div>
    </nav>
  </header>