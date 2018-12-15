package com.backend.netflix.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import com.backend.netflix.services.BillingService;
import com.backend.netflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.backend.netflix.services.UserService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.vo.Encryption;

@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private UserSubscriptionService usService;

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private BillingService billingService;

	@RequestMapping("/users")
	public ResponseEntity<?> getAllUsers(HttpSession session) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(session.getAttribute("role").toString().compareTo("ADMIN")==0) {
			List<User> users = userService.getAllUsers();
			response.put("success", true);
			response.put("statusCode",200);
			response.put("users",users);
		} else {
			response.put("success", false);
			response.put("statusCode",401);
			response.put("message","You are not authorized to views all users");
		}
		return new ResponseEntity(response, HttpStatus.OK);
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/users/{userId}")
	public ResponseEntity<?> getUser(@PathVariable int userId, HttpSession session) {
		HashMap<String,Object> response = new HashMap<String,Object>();
		/*temp set role*/
		session.setAttribute("role", "ADMIN");
		if(session.getAttribute("role").toString().compareTo("ADMIN")==0) {
			User user = userService.getUser(userId);
			List<UserSubscription> subscriptionList = usService.findLatestSubscriptionByUserId(userId);
			UserSubscription subscription = subscriptionList.get(0);

			response.put("statusCode", 200);
			response.put("user",user);
			response.put("SubscriptionOnly", subscriptionList);
			response.put("success",true);
		} else  {
			response.put("statusCode", 401);
			response.put("message","You are not authorized to get user details");
			response.put("success",false);
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	//@RequestMapping(method=RequestMethod.POST,value="/users/store",)
	@PostMapping(path="/users/store",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
		
		HashMap<String,Object> response =new HashMap<>();
//		Check if user already present
		User existingUser = new User();
		existingUser = userService.findUserByEmail(user.getEmail());
		if(existingUser!=null) {
			response.put("success", false);
			response.put("message", "User Already Exists");
			response.put("statusCode",400);
			
			
			return new ResponseEntity(response,HttpStatus.IM_USED);
		}
//		End - Check if user already present
		//encrypting user password
		Encryption enc = new Encryption();
		String encrypted=enc.encrypt(user.getPassword());
		user.setPassword(encrypted);
		
		//setting the user role
		String domain = user.getEmail().substring(user.getEmail().indexOf("@") + 1);
		if(domain.compareTo("sjsu.edu")==0) {
			user.setRole("ADMIN");
		} else {
			user.setRole("CUSTOMER");
		}
		
		//initially user is not verified
		user.setVerified(false);
		
		//setting the verification code for the user
		Random rnd = new Random();
		String random = String.format("%04d", rnd.nextInt(10000));
		user.setVerificationCode(random);
		user.setRegisteredAt(LocalDateTime.now());

		//Saving user
		userService.addUser(user);
		
		
		//add code here to send an email to user for verification (email will contain the verification code)
		sendmail(random,user.getEmail());
		
		//getting id of the stored user
		List<User> ul = userService.findByEmailAndPassword(user.getEmail(),user.getPassword());
		response.put("success", true);
		response.put("message", "User Signed Up");
		response.put("statusCode",200);
		response.put("user_id",ul.get(0).getId());
		
		return new ResponseEntity(response,HttpStatus.CREATED);
	}
	
	//sending email
	private void sendmail(String random,String email) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("babymoviecentral@gmail.com", "movie@123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("babymoviecentral@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("Verification Code");
		   msg.setContent("Your verification code is: "+random, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Transport.send(msg);   
		}

	@PostMapping(path="/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity login(@RequestBody User user, HttpSession session){
		
		List<User> userList ;
		boolean isSubscribed = false;

		try {
			Encryption enc = new Encryption();
			String encrypted=enc.encrypt(user.getPassword());
			user.setPassword(encrypted);

			System.out.println(user.getPassword());
			userList = userService.login(user);
			
			HashMap<String,Object> response =new HashMap<>();
			if(userList.size()>0) {
				
				user = userList.get(0);
				if(user.isVerified()) {
					isSubscribed = usService.checkIfSubscriptionIsActive(user.getId());
					List<Integer> moviesPaidForList =  billingService.getListOfMoviesUserHasPaidFor(user.getId());
					session.setAttribute("userId",userList.get(0).getId());
					session.setAttribute("role",user.getRole());
					response.put("userId",userList.get(0).getId());
					response.put("role", user.getRole());
					response.put("verified",true);
					response.put("message", userList.get(0));
					response.put("isSubscribed", isSubscribed);
					response.put("moviesPaidForList",moviesPaidForList);
					response.put("success", true);
					response.put("statusCode", 200);
				}else {
					response.put("userId",userList.get(0).getId());
					response.put("verified", false);
					response.put("message", "User is not verified!"	);
					response.put("isSubscribed", isSubscribed);
					response.put("moviesPaidForList",null);
					response.put("success", true);
					response.put("statusCode", 400);
					
				}
			} else {
				response.put("message","User Not Found");
				response.put("isSubscribed", isSubscribed);
				response.put("moviesPaidForList",null);
				response.put("success",false);
				response.put("statusCode", 400);
				response.put("verified",false);
			}
			return  new ResponseEntity(response,HttpStatus.OK);
		} catch (Exception e) {
			HashMap<String,Object> response =new HashMap<>();
			response.put("message","There was some internal error");
			response.put("success",false);
			System.out.println("Exception"+ e.getMessage());
			return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@PostMapping(path="/users/verify/{user_id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity verify(@PathVariable int user_id,@RequestBody HashMap<String,String> data, HttpSession session){
		HashMap<String, Object> response = new HashMap<String,Object>();
		User user = userService.getUser(user_id);
		
		
		if(user!=null) {
			System.out.println(user.getVerificationCode());
			System.out.println(data.get("verification_code"));
			if(user.getVerificationCode().compareTo(data.get("verification_code"))==0) {
				userService.setVerifiedToTrue(user_id);
				//Sending verification confirmation email to user	
				try {
					sendVerificationConfirmationMail(user.getEmail());
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.put("success", true);
				response.put("statusCode",200);
			} else {
				response.put("success",false);
				response.put("statusCode",400);
				response.put("message","Verification code does not match");
			}
		}else {
			response.put("success",false);
			response.put("statusCode",400);
			response.put("message","User not found!");
		}
		return  new ResponseEntity(response,HttpStatus.OK);
	}
	
	//sending verification confirmation email
	private void sendVerificationConfirmationMail(String email) throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("babymoviecentral@gmail.com", "movie@123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("babymoviecentral@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject("Account Confirmation");
		   msg.setContent("<h2> Congratulations! Your account is verified.</h2> <p style='color: blue'>You can now start using our services.</p>", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Transport.send(msg);   
		}
	
	//logout
		@RequestMapping(value = "/logout")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity logout(HttpSession session) {

			System.out.println("------------------LOGOUT:"+ session.getAttribute("userId"));
			System.out.println("------------------LOGOUT:"+ session.getAttribute("role"));
	        System.out.println(session.getAttribute("userId"));
	        session.removeAttribute("role");
	        session.removeAttribute("userId");
	        ServletContext context = session.getServletContext();
	        context.removeAttribute("role");
	        context.removeAttribute("userId");       
	        session.invalidate();

	        HashMap<String,Object> response =new HashMap<>();
	        response.put("success", true);
	        return  new ResponseEntity(response,HttpStatus.OK);
	    }
		
		
	//get user profile
		@RequestMapping("/users/profile")
		public ResponseEntity getUser(HttpSession session) {
			//session.setAttribute("userId", 2);
			HashMap<String,Object> response = new HashMap<>();
			int userId = (int)session.getAttribute("userId");
			UserSubscription subscription = null;
			List<UserSubscription> subscriptionList = usService.findLatestSubscriptionByUserId(userId);
			if(subscriptionList.size()>0) {
			    subscription = subscriptionList.get(0);
			} 

			User user = userService.getUser(userId);
			if(user!=null) {
				response.put("user", user);
				response.put("subscription",subscription);
				response.put("success", true);
				response.put("statusCode",200);
			} else {
				response.put("message", "User Not Found");
				response.put("success", false);
				response.put("statusCode",200);
			}
			return new ResponseEntity(response,HttpStatus.OK);
		}
	

}