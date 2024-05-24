package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Tweet;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/tweet_list")
public class TweetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TweetDAO tweetDAO = new TweetDAO();
        try {
            // ツイート一覧の取得
            List<Tweet> tweets = tweetDAO.getAllTweets();

            // リクエスト属性にツイート一覧を設定
            request.setAttribute("tweets", tweets);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 新規投稿時のメッセージを取得
        String message = (String) request.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
        }

        // ツイート一覧ページにフォワード
        request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームから送信された投稿内容と投稿者を取得
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        // 投稿内容の長さをチェック
        if (content.length() > 255) {
            // メッセージを設定してリダイレクト
            request.setAttribute("message", "投稿内容は255文字以内で入力してください");
        } else {
            // TweetDAOを使って投稿を実行
            TweetDAO tweetDAO = new TweetDAO();
            try {
                tweetDAO.addTweet(content, author);
                // メッセージを設定してリダイレクト
                request.setAttribute("message", "投稿が成功しました");
            } catch (Exception e) {
                e.printStackTrace();
                // メッセージを設定してリダイレクト
                request.setAttribute("message", "投稿に失敗しました");
            }
        }

        // ツイート一覧ページにリダイレクト
        response.sendRedirect(request.getContextPath() + "/tweet_list");
    }
}
