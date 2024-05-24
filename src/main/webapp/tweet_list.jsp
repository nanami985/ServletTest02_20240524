<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツイート一覧</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>ツイート一覧</h1>
        <!-- 新規投稿 -->
        <p>
            <a href="new_tweet.jsp">新規投稿</a>
        </p>

        <!-- メッセージの表示 -->
        <c:if test="${not empty message}">
            <p><c:out value="${message}" /></p>
        </c:if>

        <!-- ツイート一覧の表示 -->
        <ul class="tweet-list">
            <!-- ツイートごとにループして表示 -->
            <c:forEach var="tweet" items="${tweets}">
                <li>
                    <div class="tweet-content">
                        <!-- ツイート内容の表示 -->
                        <p><c:out value="${tweet.content}" /></p>
                        <!-- 投稿者と投稿日時の表示 -->
                        <p class="tweet-info">投稿者: <c:out value="${tweet.author}" /> - 投稿日時: <c:out value="${tweet.posted_at}" /></p>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
