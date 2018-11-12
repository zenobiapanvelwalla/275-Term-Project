package com.backend.netflix.controllers;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.Encryption;

@RestController
public class UserController {
	@Autowired
	private UserService userService ;
	
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		//return "Hello";
		
		return userService.getAllUsers();
		
		
	}
	
	//@RequestMapping(method=RequestMethod.POST,value="/users/store",)
	@PostMapping(path="/users/store",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
		
		//encrypting user password
		Encryption enc = new Encryption();
		String encrypted=enc.encrypt(user.getPassword());
		user.setPassword(encrypted);
		
		//setting the user role
		user.setRole("ADMIN");
		
		//setting the verification code for the user
		String random = getSaltString();
		user.setVerificationCode(random);
		
		//Saving user
		userService.addUser(user);
		
		//add code here to send an email to user for verification (email will contain the verification code
		sendmail(random);
		
		return new ResponseEntity(null,HttpStatus.CREATED);
	}
	/**
	 * Generate random string for user verification
	 * @return
	 */
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	//sending email
	private void sendmail(String random) throws AddressException, MessagingException, IOException {
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

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zenobiapanvelwalla@gmail.com"));
		   msg.setSubject("Verification Code");
		   msg.setContent("Your verification code is: "+random, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

//		   Multipart multipart = new MimeMultipart();
//		   multipart.addBodyPart(messageBodyPart);
//		   MimeBodyPart attachPart = new MimeBodyPart();
//
//		   attachPart.attachFile("/var/tmp/image19.png");
//		   multipart.addBodyPart(attachPart);
//		   msg.setContent(multipart);
		   Transport.send(msg);   
		}

	@PostMapping(path="/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity login(@RequestBody User user, HttpSession session){
		List<User> userList ;
		try {
			Encryption enc = new Encryption();
			String encrypted=enc.encrypt(user.getPassword());
			user.setPassword(encrypted);
			System.out.println(user.getPassword());
			userList = userService.login(user);
			
			HashMap<String,Object> response =new HashMap<>();
			if(userList.size()>0) {
				System.out.println(userList);
				session.setAttribute("userId",userList.get(0).getId());
				System.out.println(userList.get(0).getId());
				System.out.println(session.getAttribute("userId"));
				response.put("message", userList);
				response.put("success", true);
				response.put("statusCode", 200);
			} else {
				response.put("message","User Not Found");
				response.put("success false",false);
				response.put("statusCode", 200);
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
	
	//logout
		@RequestMapping(value = "/logout")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public ResponseEntity logout(HttpSession session) {
	        System.out.println(session.getAttribute("userId"));
	        session.invalidate();
	        HashMap<String,Object> response =new HashMap<>();
	        response.put("success", true);
	        return  new ResponseEntity(response,HttpStatus.OK);
	    }
	//get user profile
		@RequestMapping("/users/profile/{id}")
		public ResponseEntity getUser(@PathVariable int id) {
			HashMap<String,Object> response = new HashMap<>();
			Optional<User> user = userService.getUser(id);
			if(user!=null) {
				response.put("message", user);
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
