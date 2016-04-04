package controller;

public class User {
	
	

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.message="";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String email;
	String password;
	
	String message;

	
	public boolean validate()
	{
		if(email==null)
		{
			message = "Please enter email";
			return false;
		}
		
			
		if(!email.matches("\\w+@\\w+\\.\\w+"))
		{
			message = "invalid email address";
			return false;
		}
		if(password==null)
		{
			message = "Please enter password";
			return false;
		}
		
		if(password.length()<8)
		{
			message = "password needs to be atleast 8 characters long";
			return false;
		}
		
		if (password.matches("\\w*+\\s+\\w*"))
		{
			message = "password cannot have blank spaces";
			return false;
		}
		
		return true;
	}
}
