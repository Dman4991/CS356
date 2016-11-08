/*
 * Daniel Avetyan
 * CS 356 Assignment 2
 * Due November 8, 2016
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User View java swing window
 */
public class UserView {
	
	private TwitterUser user;

	private static HashMap<User, Thread> userThreadsActive= new HashMap<User, Thread>();
	private JFrame frame;
	private JTextField txtUserId;
	private JTextField txtTweetMessage;
	private JButton btnPostTweet;
	private JButton btnAddUser;
	private JList<TwitterUser> usersFollowedList;
	private JList<Tweet> feedList;
	private Thread t;
	private int originalFeedSize;
	private int xFrame=0;
	private int yFrame=0;

	/**
	 * Launch the application. 
	 */
	public static void newScreen(TwitterUser user, int originalFeedSize, double x, double y) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView window = new UserView(user, originalFeedSize, x, y);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserView(TwitterUser user, int originalFeedSize, double x, double y) {
		xFrame = (int)x;
		yFrame = (int)y;
		this.originalFeedSize = originalFeedSize;
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle(user.getName());
		frame.setBounds(xFrame, yFrame, 500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//TEXT FIELD USER ID
		txtUserId = new JTextField();
		txtUserId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserId.setBounds(15, 16, 197, 46);
		frame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);
		
		
		//BUTTON FOLLOW USER
		btnAddUser = new JButton("Follow User");
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean redraw = true;
				String userIdString = txtUserId.getText();
				if(userIdString!=null && (userIdString.length()>0)){	//text field valid
					TwitterUser followThisUser = (TwitterUser) TwitterUser.getUserById(userIdString);
					if(followThisUser!=null){	//if user exists
						followThisUser.setListener(user);	//follow user
						user.addToMyFollowList(followThisUser);
					}else{
						JOptionPane.showMessageDialog(null, "Cannot add user that does not exist.");
						redraw = false;
					}
				}else{
					JOptionPane.showMessageDialog(null, "User ID field invalid.");
					redraw = false;
				}
				if(redraw){
					reDraw(originalFeedSize);
				}
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddUser.setBounds(266, 15, 197, 46);
		frame.getContentPane().add(btnAddUser);
		
		
		//LIST OF USERS FOLLOWED
		DefaultListModel<TwitterUser> usersFollowedListModel = new DefaultListModel<TwitterUser>();
		ArrayList<User> followedUsers = user.getUsersIFollow();
		for(User user: followedUsers){
			usersFollowedListModel.addElement((TwitterUser) user);
		}
		usersFollowedList = new JList<TwitterUser>(usersFollowedListModel);
		usersFollowedList.setBounds(15, 91, 448, 126);
		frame.getContentPane().add(usersFollowedList);
		
		
		//LIST OF FEED TWEETS
		DefaultListModel<Tweet> feedListModel = new DefaultListModel<Tweet>();
		ArrayList<Tweet> followedTweets = user.getFeed();
		for(Tweet tweet: followedTweets){
			feedListModel.addElement(tweet);
		}
		feedList = new JList<Tweet>(feedListModel);
		feedList.setBounds(15, 302, 448, 126);
		frame.getContentPane().add(feedList);
		
		
		//TWEET TEXT BOX
		txtTweetMessage = new JTextField();
		txtTweetMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTweetMessage.setBounds(25, 233, 187, 46);
		frame.getContentPane().add(txtTweetMessage);
		txtTweetMessage.setColumns(10);
		
		
		//POST TWEET BUTTON
		btnPostTweet = new JButton("Post Tweet");
		btnPostTweet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String tweetMessage = txtTweetMessage.getText();
				
				//if a valid tweet
				if(tweetMessage!=null && tweetMessage.length()>0){
					tweetMessage = "@"+user.getName()+" "+tweetMessage;
					user.postTweet(new Tweet(tweetMessage));
				}
				txtTweetMessage.setText("");
			}
		});
		btnPostTweet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPostTweet.setBounds(266, 232, 197, 46);
		frame.getContentPane().add(btnPostTweet);
		
		
		//if a thread for this user exists, kill it and create a new one
		if(userThreadsActive.containsKey(user)){
			killThread(userThreadsActive.get(user));
		}
		
		//Starts a new tread to listen for feed updates
		t = new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					updateFeed();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		userThreadsActive.put(user, t);
	}
	
	//Instead of doing threads I later figured out I can update the screens
	//any time the post tweet button is pressed, though this is already implemented.
	/**Any time the feed changes the frame gets redrawn.*/
	public void updateFeed() throws InterruptedException{
		int currentFeedSize=0;
		while(true){
			Thread.sleep(500);
			currentFeedSize = user.getFeed().size();
			if(currentFeedSize != originalFeedSize){
				break;
			}
		}
		reDraw(currentFeedSize);
	}
	
	/**Improper but effective way of doing a screen refresh*/
	@SuppressWarnings("deprecation")
	public void reDraw(int size){
		frame.dispose();
		Point p = frame.location();
		newScreen(user, size, p.getX(), p.getY());
	}
	
	/**Kills any Thread that is passed as an argument*/
	@SuppressWarnings("deprecation")
	public void killThread(Thread t){
		t.suspend();
	}
}
