package j.twitter_play;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OAuthSample {
	/**
	 * Usage: java  twitter4j.examples.oauth.GetAccessToken [consumer key] [consumer secret]
	 *
	 * @param args message
	 * @throws IOException 
	 * @throws TwitterException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] argsjar) throws IOException, TwitterException, URISyntaxException {
		File file = new File("twitter4j.properties");
		Properties prop = new Properties();
		prop.setProperty("oauth.consumerKey", "I5W9PhS6b0SHzv1KREw");
		prop.setProperty("oauth.consumerSecret", "dPbVfaanEeQdMD6XJRY7OmVxOr7BLTsQytEhLxdIDA");
		prop.store(new FileOutputStream(file), "twitter4j.properties");

		Twitter twitter = new TwitterFactory().getInstance();
		RequestToken requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			System.out.println("Open the following URL and grant access to your account:");
			System.out.println(requestToken.getAuthorizationURL());
			Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
			System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
			String pin = br.readLine();
			if (pin.length() > 0) {
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			} else {
				accessToken = twitter.getOAuthAccessToken(requestToken);
			}
		}
		System.out.println("Got access token.");

		prop.setProperty("oauth.accessToken", accessToken.getToken());
		prop.setProperty("oauth.accessTokenSecret", accessToken.getTokenSecret());
		prop.store(new FileOutputStream(file), "twitter4j.properties");
		System.out.println("Successfully stored access token to " + file.getAbsolutePath() + ".");
	}


}