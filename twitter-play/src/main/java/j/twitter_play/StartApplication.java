package j.twitter_play;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * sample-executor
 * 
 * @author j
 */
public class StartApplication {

	/**
	 * メイン処理の実行
	 */
	public static void main(String[] args) {
		tweet("Hello Twitter by Java API!!");
	}

	/**
	 * exec tweet
	 */
	private static void tweet(String msg) {
        Twitter twitter = new TwitterFactory().getInstance();
		try {
			AccessToken accessToken = twitter.getOAuthAccessToken();
			twitter.setOAuthAccessToken(accessToken);
			Status status = twitter.updateStatus(msg);
			System.out.println("status=" + status);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 何か閉じた方が良いかも・・・
		}
	}

}
